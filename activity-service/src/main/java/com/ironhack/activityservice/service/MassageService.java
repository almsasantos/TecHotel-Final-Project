package com.ironhack.activityservice.service;

import com.ironhack.activityservice.client.InvoiceClient;
import com.ironhack.activityservice.client.RoomClient;
import com.ironhack.activityservice.client.UserClient;
import com.ironhack.activityservice.dto.UpdateMassageTypeDto;
import com.ironhack.activityservice.exception.DataNotFoundException;
import com.ironhack.activityservice.exception.NotEnoughBalanceException;
import com.ironhack.activityservice.exception.ReservationException;
import com.ironhack.activityservice.model.classes.Money;
import com.ironhack.activityservice.model.entities.Massage;
import com.ironhack.activityservice.model.enums.InvoiceType;
import com.ironhack.activityservice.model.enums.TypeOfUser;
import com.ironhack.activityservice.model.invoices.Invoice;
import com.ironhack.activityservice.model.users.Basic;
import com.ironhack.activityservice.model.users.Premium;
import com.ironhack.activityservice.model.viewModel.InvoiceViewModel;
import com.ironhack.activityservice.model.viewModel.MassageViewModel;
import com.ironhack.activityservice.repository.MassageRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MassageService {
    @Autowired
    private MassageRepository massageRepository;

    @Autowired
    private UserClient userClient;

    @Autowired
    private RoomClient roomClient;

    @Autowired
    private InvoiceClient invoiceClient;

    private static final Logger LOGGER = LogManager.getLogger(MassageService.class);

    public List<Massage> findAll(){
        LOGGER.info("Find all Massage Appointments");
        return massageRepository.findAll();
    }

    public Massage findMassageById(Long massageId){
        LOGGER.info("Find Massage Appointment with id " + massageId);
        return massageRepository.findById(massageId).orElseThrow(() -> new DataNotFoundException("Massage id not found"));
    }

    @HystrixCommand(fallbackMethod = "changeMassageTypeFail")
    public void changeMassageType(UpdateMassageTypeDto changeMassageTypeDto){
        LOGGER.info("[INIT] Change Massage type for " + changeMassageTypeDto.getMassageType() + " in appointment " + changeMassageTypeDto.getMassageId());
        Massage massage = findMassageById(changeMassageTypeDto.getMassageId());
        resetMoney(massage.getUserId(), massage.getMassageType().getPrice().setScale(2, RoundingMode.HALF_DOWN));
        confirmData(massage.getUserId(), massage.getRoomId(), new Money(changeMassageTypeDto.getMassageType().getPrice()));
        massage.setMassageType(changeMassageTypeDto.getMassageType());
        massage.setTotalPrice(new Money(massage.getMassageType().getPrice()));

        LOGGER.info("Create new invoice");
        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setUserId(massage.getUserId());
        invoiceViewModel.setRoomId(massage.getRoomId());
        invoiceViewModel.setPriceOwed(massage.getTotalPrice().getAmount());
        invoiceViewModel.setInvoiceType(InvoiceType.MASSAGE_APPOINTMENT);
        invoiceClient.createInvoiceActivity(invoiceViewModel);

        LOGGER.info("[END] Change Massage type for " + changeMassageTypeDto.getMassageType() + " in appointment " + changeMassageTypeDto.getMassageId());
        massageRepository.save(massage);
    }

    @HystrixCommand(fallbackMethod = "createMassageAppointmentFail")
    public Massage createMassageAppointment(MassageViewModel massageViewModel){
        LOGGER.info("[INIT] Create new Massage Appointment for user id " + massageViewModel.getUserId() + " with room " + massageViewModel.getRoomId());

        confirmData(massageViewModel.getUserId(), massageViewModel.getRoomId(), new Money(massageViewModel.getMassageType().getPrice()));
        Massage massage = new Massage();
        massage.setMassageType(massageViewModel.getMassageType());
        massage.setBeginOfActivity(massageViewModel.getBeginOfActivity());
        massage.setRoomId(massageViewModel.getRoomId());
        massage.setUserId(massageViewModel.getUserId());
        massage.setTotalPrice(new Money(massageViewModel.getMassageType().getPrice()));
        massage.setEndOfActivity(massage.getBeginOfActivity().plus(Duration.ofHours(LocalTime.of(1, 00).getHour())));
        massageRepository.save(massage);

        LOGGER.info("Create new invoice");
        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setUserId(massage.getUserId());
        invoiceViewModel.setRoomId(massage.getRoomId());
        invoiceViewModel.setPriceOwed(massage.getTotalPrice().getAmount());
        invoiceViewModel.setInvoiceType(InvoiceType.MASSAGE_APPOINTMENT);
        invoiceClient.createInvoiceActivity(invoiceViewModel);

        LOGGER.info("[END] Create new Massage Appointment for user id " + massageViewModel.getUserId() + " with room " + massageViewModel.getRoomId());
        return massage;
    }

    public void cancelMassageAppointment(Long massageId) throws ReservationException{
        LOGGER.info("[INIT] Cancel Massage Appointment with id " + massageId);
        Massage massage = findMassageById(massageId);
        if(massage.getBeginOfActivity().isBefore(LocalDateTime.now()))
            throw new ReservationException("It's not possible to cancel massage reservation");
        else {
            resetMoney(massage.getUserId(), massage.getMassageType().getPrice().setScale(2, RoundingMode.HALF_DOWN));
            massageRepository.delete(massage);
        }
        LOGGER.info("[END] Cancel Massage Appointment with id " + massageId);
    }

    @HystrixCommand(fallbackMethod = "confirmDataFail")
    public void confirmData(Long userId, Integer roomId, Money amount) throws ReservationException {
        LOGGER.info("[INIT] Confirm data from user id " + userId + " with room " + roomId);
        List<Basic> basicUsers = userClient.findAllBasicUser().stream().filter(user -> user.getId().equals(userId)).collect(Collectors.toList());
        List<Premium> premiumUsers = userClient.findAllPremiumUsers().stream().filter(user -> user.getId().equals(userId)).collect(Collectors.toList());
        Basic basic = null;
        Premium premium = null;

        if(basicUsers.size() == 0 && premiumUsers.size() == 0)
            throw new ReservationException("User id doesn't exist");
        else if (basicUsers.size() > 0){
            LOGGER.info("User with id " + userId + " is from type Basic");
            basic = userClient.findBasicUserById(userId);
            if(!basic.getRoomId().equals(roomId))
                throw new ReservationException("Basic User is not associated with that room");
            checkEnoughBalance(basic.getId(), TypeOfUser.BASIC, amount);
            userClient.updateBasicBalance(basic.getId(), basic.getBankAccount().getBalance().decreaseAmount(amount.getAmount()));
        }
        else if(premiumUsers.size()>0){
            LOGGER.info("User with id " + userId + " is from type Premium");
            premium = userClient.findPremiumUserById(userId);
            if(!premium.getRoomId().equals(roomId))
                throw new ReservationException("Premium User is not associated with that room");
            checkEnoughBalance(premium.getId(), TypeOfUser.PREMIUM, amount);
            userClient.updatePremiumBalance(premium.getId(), premium.getBankAccount().getBalance().decreaseAmount(amount.getAmount()));
        }
        LOGGER.info("[END] Confirm data from user id " + userId + " with room " + roomId);
    }

    @HystrixCommand(fallbackMethod = "checkEnoughBalanceFail")
    public void checkEnoughBalance(Long userId, TypeOfUser typeOfUser, Money price) {
        LOGGER.info("[INIT] Check if user " + userId + " has enough balance to make appointment");
        if (typeOfUser.equals(TypeOfUser.BASIC)) {
            LOGGER.info("User with id " + userId + " is from type Basic");
            Basic basic = userClient.findBasicUserById(userId);
            if (basic.getBankAccount().getBalance().getAmount().compareTo(price.getAmount()) == -1) {
                throw new NotEnoughBalanceException("User doesn't have enough balance to do reservation");
            }
        } else if (typeOfUser.equals(TypeOfUser.PREMIUM)) {
            LOGGER.info("User with id " + userId + " is from type Premium");
            Premium premium = userClient.findPremiumUserById(userId);
            if (premium.getBankAccount().getBalance().getAmount().compareTo(price.getAmount()) == -1) {
                throw new NotEnoughBalanceException("User doesn't have enough balance to do reservation");
            }
        }
        LOGGER.info("[END] Check if user " + userId + " has enough balance to make appointment");
    }

    @HystrixCommand(fallbackMethod = "resetMoneyFail")
    public void resetMoney(Long userId, BigDecimal amount){
        LOGGER.info("[INIT] Reset balance of user " + userId + " with a total amount of " + amount + " after canceling appointment");
        List<Basic> basicUsers = userClient.findAllBasicUser().stream().filter(user -> user.getId().equals(userId)).collect(Collectors.toList());
        List<Premium> premiumUsers = userClient.findAllPremiumUsers().stream().filter(user -> user.getId().equals(userId)).collect(Collectors.toList());
        Basic basic = null;
        Premium premium = null;

        if(basicUsers.size() == 0 && premiumUsers.size() == 0)
            throw new ReservationException("User id doesn't exist");
        else if (basicUsers.size() > 0){
            LOGGER.info("User with id " + userId + " is from type Basic");
            basic = userClient.findBasicUserById(userId);
            userClient.updateBasicBalance(basic.getId(), basic.getBankAccount().getBalance().increaseAmount(amount));
        }
        else if(premiumUsers.size()>0){
            LOGGER.info("User with id " + userId + " is from type Premium");
            premium = userClient.findPremiumUserById(userId);
            userClient.updatePremiumBalance(premium.getId(), premium.getBankAccount().getBalance().increaseAmount(amount));
        }
        LOGGER.info("[END] Reset balance of user " + userId + " with a total amount of " + amount + " after canceling appointment");
    }

    public void confirmDataFail(Long userId, Integer roomId, Money amount) {
        LOGGER.warn("[WARN] It wasn't possible to confirm the data");
    }

    public void resetMoneyFail(Long userId, BigDecimal amount) {
        LOGGER.warn("[WARN] It wasn't to give user's money back");
    }

    public void checkEnoughBalanceFail(Long userId, TypeOfUser typeOfUser, Money price) {
        LOGGER.warn("[WARN] It wasn't possible to check user's balance");
    }

    public Massage createMassageAppointmentFail(MassageViewModel massageViewModel){
        LOGGER.warn("[WARN] It wasn't possible to make a new Massage appointment");
        return null;
    }

    public void updateUserMassageFail(Massage massage, Invoice invoice, Long userId){
        LOGGER.warn("[WARN] It wasn't possible to update user with new Massage information");
    }

    public void changeMassageTypeFail(UpdateMassageTypeDto changeMassageTypeDto){
        LOGGER.warn("[WARN] It wasn't possible to change type of Massage");
    }

}

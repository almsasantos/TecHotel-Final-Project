package com.ironhack.activityservice.service;

import com.ironhack.activityservice.client.InvoiceClient;
import com.ironhack.activityservice.client.RoomClient;
import com.ironhack.activityservice.client.UserClient;
import com.ironhack.activityservice.dto.UpdateDrinkMenuDto;
import com.ironhack.activityservice.dto.UpdateRoomFoodMenuDto;
import com.ironhack.activityservice.exception.DataNotFoundException;
import com.ironhack.activityservice.exception.NotEnoughBalanceException;
import com.ironhack.activityservice.exception.ReservationException;
import com.ironhack.activityservice.model.classes.Money;
import com.ironhack.activityservice.model.entities.RoomFood;
import com.ironhack.activityservice.model.enums.InvoiceType;
import com.ironhack.activityservice.model.enums.TypeOfUser;
import com.ironhack.activityservice.model.users.Basic;
import com.ironhack.activityservice.model.users.Premium;
import com.ironhack.activityservice.model.viewModel.InvoiceViewModel;
import com.ironhack.activityservice.model.viewModel.RoomFoodViewModel;
import com.ironhack.activityservice.repository.RoomFoodRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomFoodService {
    @Autowired
    private RoomFoodRepository roomFoodRepository;

    @Autowired
    private UserClient userClient;

    @Autowired
    private RoomClient roomClient;

    @Autowired
    private InvoiceClient invoiceClient;

    private static final Logger LOGGER = LogManager.getLogger(RoomFoodService.class);

    public List<RoomFood> findAll(){
        LOGGER.info("Find all Room Food Requests");
        return roomFoodRepository.findAll();
    }

    public RoomFood findRoomFoodById(Long roomFoodId){
        LOGGER.info("Find Room Food Request with id " + roomFoodId);
        return roomFoodRepository.findById(roomFoodId).orElseThrow(() -> new DataNotFoundException("Room food request id not found"));
    }

    @HystrixCommand(fallbackMethod = "createRoomFoodRequestFail")
    public RoomFood createRoomFoodRequest(RoomFoodViewModel roomFoodViewModel){
        LOGGER.info("[INIT] Create new Room Food Request for user id " + roomFoodViewModel.getUserId() + " in room " + roomFoodViewModel.getRoomId());

        BigDecimal foodPrice = roomFoodViewModel.getFoodMenu().getPrice();
        BigDecimal drinkPrice = roomFoodViewModel.getDrinkMenu().getPrice();
        BigDecimal totalPrice = foodPrice.add(drinkPrice);

        confirmData(roomFoodViewModel.getUserId(), roomFoodViewModel.getRoomId(), new Money(totalPrice));

        RoomFood roomFood = new RoomFood();
        roomFood.setRoomId(roomFoodViewModel.getRoomId());
        roomFood.setUserId(roomFoodViewModel.getUserId());
        roomFood.setDrinkMenu(roomFoodViewModel.getDrinkMenu());
        roomFood.setFoodMenu(roomFoodViewModel.getFoodMenu());
        roomFood.setTotalPrice(new Money(totalPrice));
        roomFoodRepository.save(roomFood);

        LOGGER.info("Create new invoice");
        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setUserId(roomFoodViewModel.getUserId());
        invoiceViewModel.setRoomId(roomFoodViewModel.getRoomId());
        invoiceViewModel.setPriceOwed(roomFood.getTotalPrice().getAmount());
        invoiceViewModel.setInvoiceType(InvoiceType.ROOM_FOOD_SERVICE);
        invoiceClient.createInvoiceActivity(invoiceViewModel);

        LOGGER.info("[END] Create new Room Food Request for user id " + roomFoodViewModel.getUserId() + " in room " + roomFoodViewModel.getRoomId());
        return roomFood;
    }

    public void updateFoodMenu(UpdateRoomFoodMenuDto updateRoomFoodMenuDto){
        LOGGER.info("[INIT] Update Food Menu of Room Food Request with id " + updateRoomFoodMenuDto.getRoomFoodId());

        RoomFood roomFood = findRoomFoodById(updateRoomFoodMenuDto.getRoomFoodId());
        if(roomFood.getDelivered())
            throw new ReservationException("It's not possible to cancel room food request");

        resetMoney(roomFood.getUserId(), roomFood.getFoodMenu().getPrice().setScale(2, RoundingMode.HALF_DOWN));
        confirmData(roomFood.getUserId(), roomFood.getRoomId(), new Money(updateRoomFoodMenuDto.getFoodMenu().getPrice()));

        roomFood.setFoodMenu(updateRoomFoodMenuDto.getFoodMenu());
        roomFood.setTotalPrice(new Money(roomFood.getDrinkMenu().getPrice()));
        roomFoodRepository.save(roomFood);

        LOGGER.info("Create new invoice");
        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setUserId(roomFood.getUserId());
        invoiceViewModel.setRoomId(roomFood.getRoomId());
        invoiceViewModel.setPriceOwed(roomFood.getTotalPrice().getAmount());
        invoiceViewModel.setInvoiceType(InvoiceType.ROOM_FOOD_SERVICE);
        invoiceClient.createInvoiceActivity(invoiceViewModel);

        LOGGER.info("[END] Update Food Menu of Room Food Request with id " + updateRoomFoodMenuDto.getRoomFoodId());
    }

    public void updateDrinkMenu(UpdateDrinkMenuDto updateDrinkMenuDto){
        LOGGER.info("[INIT] Update Drink Menu of Room Food Request with id " + updateDrinkMenuDto.getRoomFoodId());

        RoomFood roomFood = findRoomFoodById(updateDrinkMenuDto.getRoomFoodId());
        if(roomFood.getDelivered())
            throw new ReservationException("It's not possible to cancel room food request");

        resetMoney(roomFood.getUserId(), roomFood.getDrinkMenu().getPrice().setScale(2, RoundingMode.HALF_DOWN));
        confirmData(roomFood.getUserId(), roomFood.getRoomId(), new Money(updateDrinkMenuDto.getDrinkMenu().getPrice()));

        roomFood.setDrinkMenu(updateDrinkMenuDto.getDrinkMenu());
        roomFood.setTotalPrice(new Money(roomFood.getDrinkMenu().getPrice()));
        roomFoodRepository.save(roomFood);

        LOGGER.info("Create new invoice");
        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setUserId(roomFood.getUserId());
        invoiceViewModel.setRoomId(roomFood.getRoomId());
        invoiceViewModel.setPriceOwed(roomFood.getTotalPrice().getAmount());
        invoiceViewModel.setInvoiceType(InvoiceType.ROOM_FOOD_SERVICE);
        invoiceClient.createInvoiceActivity(invoiceViewModel);

        LOGGER.info("[END] Update Drink Menu of Room Food Request with id " + updateDrinkMenuDto.getRoomFoodId());
    }

    public void deliverRoomFood(Long roomFoodId){
        LOGGER.info("Deliver Room Food Service to room " + roomFoodId);
        RoomFood roomFood = findRoomFoodById(roomFoodId);
        roomFood.setDelivered(true);
        roomFoodRepository.save(roomFood);
    }

    public void cancelRoomFoodRequest(Long roomFoodId) throws ReservationException {
        LOGGER.info("[INIT] Cancel Room Food Request with id " + roomFoodId);
        RoomFood roomFood = findRoomFoodById(roomFoodId);
        if(roomFood.getDelivered())
            throw new ReservationException("It's not possible to cancel room food request");
        else {
            resetMoney(roomFood.getUserId(), roomFood.getTotalPrice().getAmount());
            roomFoodRepository.delete(roomFood);
        }
        LOGGER.info("[END] Cancel Room Food Request with id " + roomFoodId);
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

    public void confirmDataFail(Long userId, Integer roomId, Money amount) throws ReservationException {
        LOGGER.warn("[WARN] It wasn't possible to confirm the data");
    }

    public void resetMoneyFail(Long userId, BigDecimal amount) {
        LOGGER.warn("[WARN] It wasn't to give user's money back");
    }

    public void checkEnoughBalanceFail(Long userId, TypeOfUser typeOfUser, Money price) {
        LOGGER.warn("[WARN] It wasn't possible to check user's balance");
    }

    public RoomFood createRoomFoodRequestFail(RoomFoodViewModel roomFoodViewModel){
        LOGGER.warn("[WARN] It wasn't possible to make a new Room Food Request");
        return null;
    }
}

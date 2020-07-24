package com.ironhack.activityservice.service;

import com.ironhack.activityservice.client.InvoiceClient;
import com.ironhack.activityservice.client.RoomClient;
import com.ironhack.activityservice.client.UserClient;
import com.ironhack.activityservice.dto.UpdateFloatiesNumDto;
import com.ironhack.activityservice.dto.UpdateTowelNumDto;
import com.ironhack.activityservice.exception.DataNotFoundException;
import com.ironhack.activityservice.exception.NotEnoughBalanceException;
import com.ironhack.activityservice.exception.ReservationException;
import com.ironhack.activityservice.model.classes.Money;
import com.ironhack.activityservice.model.entities.PoolRent;
import com.ironhack.activityservice.model.enums.InvoiceType;
import com.ironhack.activityservice.model.enums.TypeOfUser;
import com.ironhack.activityservice.model.users.Basic;
import com.ironhack.activityservice.model.users.Premium;
import com.ironhack.activityservice.model.viewModel.InvoiceViewModel;
import com.ironhack.activityservice.model.viewModel.PoolRentViewModel;
import com.ironhack.activityservice.repository.PoolRentRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Pool Rent's Service
 */
@Service
public class PoolRentService {

    /**
     * Autowired of Pool Rent Repository
     */
    @Autowired
    private PoolRentRepository poolRentRepository;

    /**
     * Autowired of User Client
     */
    @Autowired
    private UserClient userClient;

    /**
     * Autowired of Room Client
     */
    @Autowired
    private RoomClient roomClient;

    /**
     * Autowired of Invoice Client
     */
    @Autowired
    private InvoiceClient invoiceClient;

    /**
     * Final Variable of Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(PoolRentService.class);

    /**
     * Find All Pool Rent
     * @return a list of PoolRent
     */
    public List<PoolRent> findAll(){
        LOGGER.info("Find all Pool Rents");
        return poolRentRepository.findAll(); }

    /**
     * Find PoolRent By Id
     * @param poolRentId receives a Long with poolRentId
     * @return a PoolRent
     */
    public PoolRent findPoolRentById(Long poolRentId){
        LOGGER.info("Find Pool Rent with id " + poolRentId);
        return poolRentRepository.findById(poolRentId).orElseThrow(() -> new DataNotFoundException("Pool rent id was not found")); }

    /**
     * Create Pool Rent
     * @param poolRentViewModel receives a PoolRentViewModel
     * @return a PoolRent
     */
    @HystrixCommand(fallbackMethod = "createPoolRentFail")
    @Transactional
    public PoolRent createPoolRent(PoolRentViewModel poolRentViewModel){
        LOGGER.info("[INIT] Create new Pool Rent for user id " + poolRentViewModel.getUserId() + " with room " + poolRentViewModel.getRoomId());
        BigDecimal totalPriceFloaties = new BigDecimal(poolRentViewModel.getFloatiesNum()*5.99).setScale(2, RoundingMode.HALF_DOWN);
        BigDecimal totalPriceTowels = new BigDecimal(poolRentViewModel.getTowelNum()*5.99).setScale(2, RoundingMode.HALF_DOWN);
        BigDecimal totalPrice = totalPriceFloaties.add(totalPriceTowels);

        confirmData(poolRentViewModel.getUserId(), poolRentViewModel.getRoomId(), new Money(totalPrice));

        PoolRent poolRent = new PoolRent(); poolRent.setTowelNum(poolRentViewModel.getTowelNum());
        if(poolRentViewModel.getFloatiesNum()==null){ poolRent.setFloatiesNum(0); }
        if(poolRentViewModel.getTowelNum()==null){ poolRent.setTowelNum(0); }
        if(poolRentViewModel.getBeginOfActivity()==null){ poolRent.setBeginOfActivity(LocalDateTime.now()); }
        else { poolRent.setBeginOfActivity(poolRentViewModel.getBeginOfActivity()); }

        poolRent.setFloatiesNum(poolRentViewModel.getFloatiesNum()); poolRent.setRoomId(poolRentViewModel.getRoomId()); poolRent.setUserId(poolRentViewModel.getUserId()); poolRent.setTotalPrice(new Money(totalPrice));
        poolRent.setEndOfActivity(poolRent.getBeginOfActivity().plus(Duration.ofHours(LocalTime.of(1, 00).getHour()))); poolRentRepository.save(poolRent);

        LOGGER.info("Create new invoice");
        InvoiceViewModel invoiceViewModel = new InvoiceViewModel(); invoiceViewModel.setUserId(poolRentViewModel.getUserId()); invoiceViewModel.setRoomId(poolRentViewModel.getRoomId()); invoiceViewModel.setPriceOwed(poolRent.getTotalPrice().getAmount()); invoiceViewModel.setInvoiceType(InvoiceType.POOL_RENT);
        invoiceClient.createInvoiceActivity(invoiceViewModel);

        LOGGER.info("[INIT] Create new Pool Rent for user id " + poolRentViewModel.getUserId() + " with room " + poolRentViewModel.getRoomId()); return poolRent; }

    /**
     * Update Floaties Number
     * @param updateFloatiesNumDto receives a UpdateFloatiesNumDto with updateFloatiesNumDto
     */
    @HystrixCommand(fallbackMethod = "updateFloatiesNumFail")
    public void updateFloatiesNum(UpdateFloatiesNumDto updateFloatiesNumDto){
        LOGGER.info("[INIT] Update number of floaties to " + updateFloatiesNumDto.getFloatiesNum() + " for pool rent " + updateFloatiesNumDto.getPoolRentId());

        PoolRent poolRent = findPoolRentById(updateFloatiesNumDto.getPoolRentId());
        if(poolRent.getBeginOfActivity().isBefore(LocalDateTime.now())) throw new ReservationException("It's not possible to cancel pool rent reservation");

        resetMoney(poolRent.getUserId(), new BigDecimal(poolRent.getFloatiesNum()*5.99).setScale(2, RoundingMode.HALF_DOWN));
        confirmData(poolRent.getUserId(), poolRent.getRoomId(), new Money(new BigDecimal(updateFloatiesNumDto.getFloatiesNum()*5.99)));

        poolRent.setFloatiesNum(updateFloatiesNumDto.getFloatiesNum()); poolRent.setTotalPrice(new Money(new BigDecimal(poolRent.getFloatiesNum()*5.99+poolRent.getTowelNum()*5.99)));
        poolRentRepository.save(poolRent);

        LOGGER.info("Create new invoice");
        InvoiceViewModel invoiceViewModel = new InvoiceViewModel(); invoiceViewModel.setUserId(poolRent.getUserId()); invoiceViewModel.setRoomId(poolRent.getRoomId()); invoiceViewModel.setPriceOwed(poolRent.getTotalPrice().getAmount()); invoiceViewModel.setInvoiceType(InvoiceType.POOL_RENT);
        invoiceClient.createInvoiceActivity(invoiceViewModel);

        LOGGER.info("[END] Update number of floaties to " + updateFloatiesNumDto.getFloatiesNum() + " for pool rent " + updateFloatiesNumDto.getPoolRentId()); }

    /**
     * Update Towel Number
     * @param updateTowelNumDto receives a UpdateTowelNumDto
     */
    @HystrixCommand(fallbackMethod = "updateTowelNumFail")
    public void updateTowelNum(UpdateTowelNumDto updateTowelNumDto){
        LOGGER.info("[INIT] Update number of towels to " + updateTowelNumDto.getTowelNum() + " for pool rent " + updateTowelNumDto.getPoolRentId());

        PoolRent poolRent = findPoolRentById(updateTowelNumDto.getPoolRentId());
        if(poolRent.getBeginOfActivity().isBefore(LocalDateTime.now())) throw new ReservationException("It's not possible to cancel pool rent reservation");

        resetMoney(poolRent.getUserId(), new BigDecimal(poolRent.getTowelNum()*5.99).setScale(2, RoundingMode.HALF_DOWN));
        confirmData(poolRent.getUserId(), poolRent.getRoomId(), new Money(new BigDecimal(updateTowelNumDto.getTowelNum()*5.99)));

        poolRent.setTowelNum(updateTowelNumDto.getTowelNum()); poolRent.setTotalPrice(new Money(new BigDecimal(poolRent.getFloatiesNum()*5.99+poolRent.getTowelNum()*5.99))); poolRentRepository.save(poolRent);

        LOGGER.info("Create new invoice");
        InvoiceViewModel invoiceViewModel = new InvoiceViewModel(); invoiceViewModel.setUserId(poolRent.getUserId()); invoiceViewModel.setRoomId(poolRent.getRoomId()); invoiceViewModel.setPriceOwed(poolRent.getTotalPrice().getAmount()); invoiceViewModel.setInvoiceType(InvoiceType.POOL_RENT);
        invoiceClient.createInvoiceActivity(invoiceViewModel);

        LOGGER.info("[END] Update number of towels to " + updateTowelNumDto.getTowelNum() + " for pool rent " + updateTowelNumDto.getPoolRentId()); }

    /**
     * Cancel Pool Rent
     * @param poolRentId receives a Long with poolRentId
     * @throws ReservationException an Exception
     */
    public void cancelPoolRent(Long poolRentId) throws ReservationException {
        LOGGER.info("[INIT] Cancel Pool Rent with id " + poolRentId);

        PoolRent poolRent = findPoolRentById(poolRentId);
        if(poolRent.getBeginOfActivity().isBefore(LocalDateTime.now())) throw new ReservationException("It's not possible to cancel pool rent reservation");
        else { resetMoney(poolRent.getUserId(), poolRent.getTotalPrice().getAmount()); poolRentRepository.delete(poolRent); }
        LOGGER.info("[END] Cancel Pool Rent with id " + poolRentId); }

    /**
     * Confirm Data
     * @param userId receives a Long with userId
     * @param roomId receives a Integer with roomId
     * @param amount receives a Money with amount
     * @throws ReservationException an Exception
     */
    @HystrixCommand(fallbackMethod = "confirmDataFail")
    public void confirmData(Long userId, Integer roomId, Money amount) throws ReservationException {
        LOGGER.info("[INIT] Confirm data from user id " + userId + " with room " + roomId);
        List<Basic> basicUsers = userClient.findAllBasicUser().stream().filter(user -> user.getId().equals(userId)).collect(Collectors.toList());
        List<Premium> premiumUsers = userClient.findAllPremiumUsers().stream().filter(user -> user.getId().equals(userId)).collect(Collectors.toList());
        Basic basic = null;
        Premium premium = null;

        if(basicUsers.size() == 0 && premiumUsers.size() == 0) throw new ReservationException("User id doesn't exist");

        else if (basicUsers.size() > 0){ LOGGER.info("User with id " + userId + " is from type Basic"); basic = userClient.findBasicUserById(userId);
            if(!basic.getRoomId().equals(roomId)) throw new ReservationException("Basic User is not associated with that room"); checkEnoughBalance(basic.getId(), TypeOfUser.BASIC, amount);
            userClient.updateBasicBalance(basic.getId(), basic.getBankAccount().getBalance().decreaseAmount(amount.getAmount())); }

        else if(premiumUsers.size()>0){ LOGGER.info("User with id " + userId + " is from type Premium"); premium = userClient.findPremiumUserById(userId);
            if(!premium.getRoomId().equals(roomId)) throw new ReservationException("Premium User is not associated with that room"); checkEnoughBalance(premium.getId(), TypeOfUser.PREMIUM, amount);
            userClient.updatePremiumBalance(premium.getId(), premium.getBankAccount().getBalance().decreaseAmount(amount.getAmount())); }
        LOGGER.info("[END] Confirm data from user id " + userId + " with room " + roomId); }

    /**
     * Check Enough Balance
     * @param userId receives a Long with userId
     * @param typeOfUser receives a TypeOfUser with typeOfUser
     * @param price receives a Money with price
     */
    @HystrixCommand(fallbackMethod = "checkEnoughBalanceFail")
    public void checkEnoughBalance(Long userId, TypeOfUser typeOfUser, Money price) {
        LOGGER.info("[INIT] Check if user " + userId + " has enough balance to make appointment");
        if (typeOfUser.equals(TypeOfUser.BASIC)) { LOGGER.info("User with id " + userId + " is from type Basic"); Basic basic = userClient.findBasicUserById(userId);
            if (basic.getBankAccount().getBalance().getAmount().compareTo(price.getAmount()) == -1) { throw new NotEnoughBalanceException("User doesn't have enough balance to do reservation"); } }

        else if (typeOfUser.equals(TypeOfUser.PREMIUM)) { LOGGER.info("User with id " + userId + " is from type Premium"); Premium premium = userClient.findPremiumUserById(userId);
            if (premium.getBankAccount().getBalance().getAmount().compareTo(price.getAmount()) == -1) { throw new NotEnoughBalanceException("User doesn't have enough balance to do reservation"); } }

        LOGGER.info("[END] Check if user " + userId + " has enough balance to make appointment"); }

    /**
     * Reset Money
     * @param userId receives a Long with userId
     * @param amount receives a BigDecimal with amount
     */
    @HystrixCommand(fallbackMethod = "resetMoneyFail")
    public void resetMoney(Long userId, BigDecimal amount){
        LOGGER.info("[INIT] Reset balance of user " + userId + " with a total amount of " + amount + " after canceling appointment");
        List<Basic> basicUsers = userClient.findAllBasicUser().stream().filter(user -> user.getId().equals(userId)).collect(Collectors.toList());
        List<Premium> premiumUsers = userClient.findAllPremiumUsers().stream().filter(user -> user.getId().equals(userId)).collect(Collectors.toList()); Basic basic = null; Premium premium = null;

        if(basicUsers.size() == 0 && premiumUsers.size() == 0) throw new ReservationException("User id doesn't exist");

        else if (basicUsers.size() > 0){ LOGGER.info("User with id " + userId + " is from type Basic"); basic = userClient.findBasicUserById(userId);
            userClient.updateBasicBalance(basic.getId(), basic.getBankAccount().getBalance().increaseAmount(amount)); }

        else if(premiumUsers.size()>0){ LOGGER.info("User with id " + userId + " is from type Premium"); premium = userClient.findPremiumUserById(userId);
            userClient.updatePremiumBalance(premium.getId(), premium.getBankAccount().getBalance().increaseAmount(amount)); }

        LOGGER.info("[END] Reset balance of user " + userId + " with a total amount of " + amount + " after canceling appointment"); }

    /**
     * Filter Pool Rent By User Id
     * @param userId receives a Long with userId
     * @returnq list of objects
     */
    public List<PoolRent> filterPoolRentByUserId(Long userId){
        LOGGER.info("Filter Pool Rent Reservation from user with id " + userId);
    return poolRentRepository.filterPoolRentByUserId(userId);
    }

    /**
     * Confirm Data Fail
     * @param userId receives a Long with userId
     * @param roomId receives a Integer with roomId
     * @param amount receives a Money with amount
     * @throws ReservationException an Exception
     */
    public void confirmDataFail(Long userId, Integer roomId, Money amount) throws ReservationException {
        LOGGER.warn("[WARN] It wasn't possible to confirm the data");
    }

    /**
     * Reset Money Fail
     * @param userId receives a Long with userId
     * @param amount receives a BigDecimal with amount
     */
    public void resetMoneyFail(Long userId, BigDecimal amount) {
        LOGGER.warn("[WARN] It wasn't to give user's money back");
    }

    /**
     * Check Enough Balance Fail
     * @param userId receives a Long with userId
     * @param typeOfUser receives a TypeOfUser with typeOfUser
     * @param price receives a Money with price
     */
    public void checkEnoughBalanceFail(Long userId, TypeOfUser typeOfUser, Money price) {
        LOGGER.warn("[WARN] It wasn't possible to check user's balance");
        throw new ReservationException("It wasn't possible to check if user has enough balance");
    }

    /**
     * Create Pool Rent Fail
     * @param poolRentViewModel receives a PoolRentViewModel with poolRentViewModel
     * @return a null value;
     */
    public PoolRent createPoolRentFail(PoolRentViewModel poolRentViewModel){
        LOGGER.warn("[WARN] It wasn't possible to make a new Pool Rent");
        throw new ReservationException("It wasn't possible to create a pool rent");
    }

    /**
     * Update Floatie Number Fail
     * @param updateFloatiesNumDto receives a UpdateFloatiesNumDto with updateFloatiesNumDto
     */
    public void updateFloatiesNumFail(UpdateFloatiesNumDto updateFloatiesNumDto){
        LOGGER.warn("[WARN] It wasn't possible to update floaties number");
    }

    /**
     * Update Towel Number Fail
     * @param updateTowelNumDto receives a UpdateTowelNumDto with updateTowelNumDto
     */
    public void updateTowelNumFail(UpdateTowelNumDto updateTowelNumDto){
        LOGGER.warn("[WARN] It wasn't possible to update towels number");
    }
}

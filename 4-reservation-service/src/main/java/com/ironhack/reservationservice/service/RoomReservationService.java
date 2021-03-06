package com.ironhack.reservationservice.service;

import com.ironhack.reservationservice.client.InvoiceClient;
import com.ironhack.reservationservice.client.RoomClient;
import com.ironhack.reservationservice.client.UserClient;
import com.ironhack.reservationservice.exception.DataNotFoundException;
import com.ironhack.reservationservice.exception.NotEnoughBalanceException;
import com.ironhack.reservationservice.exception.ReservationException;
import com.ironhack.reservationservice.exception.RoomNotAvailableException;
import com.ironhack.reservationservice.model.classes.Money;
import com.ironhack.reservationservice.model.entities.RoomReservation;
import com.ironhack.reservationservice.model.entities.UserReservation;
import com.ironhack.reservationservice.model.entities.rooms.RegularRoom;
import com.ironhack.reservationservice.model.entities.rooms.SuiteRoom;
import com.ironhack.reservationservice.model.entities.users.Basic;
import com.ironhack.reservationservice.model.entities.users.Premium;
import com.ironhack.reservationservice.model.enums.InvoiceType;
import com.ironhack.reservationservice.model.enums.RoomType;
import com.ironhack.reservationservice.model.enums.TypeOfUser;
import com.ironhack.reservationservice.model.viewmodel.InvoiceViewModel;
import com.ironhack.reservationservice.model.viewmodel.RoomReservationViewModel;
import com.ironhack.reservationservice.repository.RoomReservationRepository;
import com.ironhack.reservationservice.repository.UserReservationRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Room Reservation Service
 */
@Service
public class RoomReservationService {
    /**
     * Autowired of RoomReservationRepository
     */
    @Autowired
    private RoomReservationRepository roomReservationRepository;

    /**
     * Autowired of UserReservationRepository
     */
    @Autowired
    private UserReservationRepository userReservationRepository;

    /**
     * Autowired of RoomClient
     */
    @Autowired
    private RoomClient roomClient;

    /**
     * Autowired of UserClient
     */
    @Autowired
    private UserClient userClient;
    /**
     * Autowired of InvoiceClient
     */
    @Autowired
    private InvoiceClient invoiceClient;

    /**
     * Final variable of Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(RoomReservationService.class);

    /**
     * Find All Room Reservations
     * @return list of RoomReservation
     */
    @Transactional(propagation= Propagation.REQUIRED, noRollbackFor=Exception.class)
    public List<RoomReservation> findAllRoomReservations() {
        LOGGER.info("Find all Room Reservations");
        return roomReservationRepository.findAll();
    }

    /**
     * Find Room Reservation by id
     * @param roomReservationId receives a Long with roomReservationId
     * @return a Room Reservation
     */
    public RoomReservation findRoomReservationById(Long roomReservationId) {
        LOGGER.info("Find Room Reservation with id " + roomReservationId);
        return roomReservationRepository.findById(roomReservationId).orElseThrow(() -> new DataNotFoundException("Room Reservation id not found"));
    }

    /**
     * Check if user has enough balance
     * @param userId receives a Long with userId
     * @param typeOfUser receives a TypeOfUser with typeOfUser
     * @param price receives a Money with price
     */
    @HystrixCommand(fallbackMethod = "checkEnoughBalanceFail")
    public void checkEnoughBalance(Long userId, TypeOfUser typeOfUser, Money price) {
        LOGGER.info("Check if user " + userId + " has enough balance to make appointment");
        if (typeOfUser.equals(TypeOfUser.BASIC)) {
            Basic basic = userClient.findBasicUserById(userId);
            if (basic.getBankAccount().getBalance().getAmount().compareTo(price.getAmount()) == -1) { throw new NotEnoughBalanceException("User doesn't have enough balance to do reservation"); } }
        else if (typeOfUser.equals(TypeOfUser.PREMIUM)) {
            Premium premium = userClient.findPremiumUserById(userId);
            if (premium.getBankAccount().getBalance().getAmount().compareTo(price.getAmount()) == -1) { throw new NotEnoughBalanceException("User doesn't have enough balance to do reservation"); } } }

    /**
     * Create a new Room Reservation
     * @param roomReservationViewModel receives a Room Reservation View Model
     * @return a Room Reservation
     */
    @HystrixCommand(fallbackMethod = "newRoomReservationFail")
    @Transactional(propagation= Propagation.REQUIRED, noRollbackFor=Exception.class)
    public RoomReservation newRoomReservation(RoomReservationViewModel roomReservationViewModel) {
        LOGGER.info("[INIT] Make new Room Reservation for user " + roomReservationViewModel.getUserId() + " and room " + roomReservationViewModel.getRoomId());

        LOGGER.info("Initialize need variables");
        RoomReservation roomReservation = new RoomReservation(); RegularRoom regularRoom = new RegularRoom(); SuiteRoom suiteRoom = new SuiteRoom(); Basic basic = null; Premium premium = null;
        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        UserReservation userReservation = null;

        List<Basic> basicUsers = userClient.findAllBasicUser().stream().filter(user -> user.getId().equals(roomReservationViewModel.getUserId())).collect(Collectors.toList());
        List<Premium> premiumUsers = userClient.findAllPremiumUsers().stream().filter(user -> user.getId().equals(roomReservationViewModel.getUserId())).collect(Collectors.toList());

        if (basicUsers.size() == 0 && premiumUsers.size() == 0) throw new ReservationException("User id doesn't exist");

        else if (basicUsers.size() > 0) { LOGGER.info("User with id " + roomReservationViewModel.getUserId() + " is from type Basic");
            basic = userClient.findBasicUserById(roomReservationViewModel.getUserId());
            Boolean isBasic = userClient.basicUserIdMatchesName(basic.getId(), roomReservationViewModel.getUserName());
            if(isBasic) userReservation = new UserReservation(roomReservationViewModel.getUserId(), roomReservationViewModel.getUserName(), TypeOfUser.BASIC); }

        else if (premiumUsers.size() > 0){ LOGGER.info("User with id " + roomReservationViewModel.getUserId() + " is from type Premium");
            premium = userClient.findPremiumUserById(roomReservationViewModel.getUserId());
            Boolean isPremium = userClient.premiumUserIdMatchesName(premium.getId(), roomReservationViewModel.getUserName());
            if(isPremium) userReservation = new UserReservation(roomReservationViewModel.getUserId(), roomReservationViewModel.getUserName(), TypeOfUser.PREMIUM); }

        roomReservation.setUserReservation(userReservation); roomReservation.setNumberOfNights(roomReservationViewModel.getNumberOfNights()); roomReservation.setCheckOutDay(LocalDate.now().plusDays(roomReservationViewModel.getNumberOfNights()));

        if (roomReservationViewModel.getRoomType().equals(RoomType.REGULAR_ROOM)) {
            regularRoom = roomClient.findRegularRoomById(roomReservationViewModel.getRoomId());
            roomReservation.setTotalPrice(new Money(regularRoom.getPrice()));
            findIfRoomAvailable(regularRoom.getRoomId(), regularRoom.getRoomType());
            if (regularRoom.getAvailable().equals(false)) throw new RoomNotAvailableException("Regular room is not available");

            else if (userReservation.getTypeOfUser().equals(TypeOfUser.BASIC)) {
                checkEnoughBalance(basic.getId(), TypeOfUser.BASIC, roomReservation.getTotalPrice());
                userClient.updateBasicRoomId(basic.getId(), regularRoom.getRoomId());
                userClient.updateBasicBalance(basic.getId(), basic.getBankAccount().getBalance().getAmount().subtract(roomReservation.getTotalPrice().getAmount()));
                userClient.updateBasicNumberOfStays(basic.getId(), basic.getNumberOfStays() + 1);
                invoiceViewModel.setUserId(basic.getId()); }

            else if (userReservation.getTypeOfUser().equals(TypeOfUser.PREMIUM)) {
                checkEnoughBalance(premium.getId(), TypeOfUser.PREMIUM, roomReservation.getTotalPrice());
                userClient.updatePremiumRoomId(premium.getId(), regularRoom.getRoomId());
                userClient.updatePremiumBalance(premium.getId(), premium.getBankAccount().getBalance().getAmount().subtract(roomReservation.getTotalPrice().getAmount()));
                userClient.updatePremiumNumberOfStays(premium.getId(), premium.getNumberOfStays() + 1);
                invoiceViewModel.setUserId(premium.getId()); }

            roomReservation.setRoomId(regularRoom.getRoomId());
            roomClient.updateRegularRoomAvailability(regularRoom.getRoomId(), false);
            invoiceViewModel.setRoomId(regularRoom.getRoomId());
            invoiceViewModel.setPriceOwed(regularRoom.getPrice()); }

        else if (roomReservationViewModel.getRoomType().equals(RoomType.SUITE_ROOM)) {
            suiteRoom = roomClient.findSuiteById(roomReservationViewModel.getRoomId());
            roomReservation.setTotalPrice(new Money(suiteRoom.getPrice()));
            findIfRoomAvailable(suiteRoom.getRoomId(), suiteRoom.getRoomType());

            if (suiteRoom.getAvailable().equals(false)) { throw new RoomNotAvailableException("Suite room is not available"); }

            else if (userReservation.getTypeOfUser().equals(TypeOfUser.BASIC)) {
                checkEnoughBalance(basic.getId(), TypeOfUser.BASIC, new Money(roomReservation.getTotalPrice().getAmount().add(roomReservation.getTotalPrice().getAmount().multiply(new BigDecimal("0.2")))));
                userClient.updateBasicRoomId(basic.getId(), suiteRoom.getRoomId());
                userClient.updateBasicBalance(basic.getId(), basic.getBankAccount().getBalance().getAmount().subtract(roomReservation.getTotalPrice().getAmount()));
                userClient.updateBasicNumberOfStays(basic.getId(), basic.getNumberOfStays() + 1); }

            else if (userReservation.getTypeOfUser().equals(TypeOfUser.PREMIUM)) {
                checkEnoughBalance(premium.getId(), TypeOfUser.PREMIUM, roomReservation.getTotalPrice());
                userClient.updatePremiumRoomId(premium.getId(), suiteRoom.getRoomId());
                userClient.updatePremiumBalance(premium.getId(), premium.getBankAccount().getBalance().getAmount().subtract(roomReservation.getTotalPrice().getAmount()));
                userClient.updatePremiumNumberOfStays(premium.getId(), premium.getNumberOfStays() + 1); }
            roomReservation.setRoomId(suiteRoom.getRoomId());
            roomClient.updateSuiteAvailability(suiteRoom.getRoomId(), false);
            invoiceViewModel.setRoomId(suiteRoom.getRoomId());
            invoiceViewModel.setPriceOwed(suiteRoom.getPrice());
        }

        roomReservation.setRoomType(roomReservationViewModel.getRoomType());
        invoiceViewModel.setInvoiceType(InvoiceType.HOTEL_ROOM_RESERVATION);

        invoiceClient.createInvoiceActivity(invoiceViewModel);
        LOGGER.info("[END] Make new Room Reservation for user " + roomReservationViewModel.getUserId() + " and room " + roomReservationViewModel.getRoomId());
        return roomReservationRepository.save(roomReservation); }

    /**
     * Find if Room is Available
     * @param roomId receives an Integer with roomId
     * @param roomType receives a RoomType with roomType
     */
    @HystrixCommand(fallbackMethod = "roomAvailabilityFail")
    public void findIfRoomAvailable(Integer roomId, RoomType roomType) {
        LOGGER.info("Check if room " + roomId + " is available");
        RegularRoom regularRoom = null;
        SuiteRoom suiteRoom = null;
        if (roomType.equals(RoomType.REGULAR_ROOM)) {
            regularRoom = roomClient.findRegularRoomById(roomId);
            if (regularRoom.getAvailable().equals(false)) { throw new ReservationException("Room is not available"); } }
        else if (roomType.equals(RoomType.SUITE_ROOM)) {
            suiteRoom = roomClient.findSuiteById(roomId);
            if (suiteRoom.getAvailable().equals(false)) { throw new ReservationException("Room is not available"); } } }

    /**
     * Check if user id matches room
     * @param userId receives a Long with userId
     * @param roomId receives an Integer with roomId
     * @return a boolean value
     */
    public Boolean userIdMatchesRoom(Long userId, Integer roomId){
        List<Basic> basicUsers = userClient.findAllBasicUser().stream().filter(user -> user.getId().equals(userId)).collect(Collectors.toList());
        List<Premium> premiumUsers = userClient.findAllPremiumUsers().stream().filter(user -> user.getId().equals(userId)).collect(Collectors.toList());

        if (basicUsers.size() == 0 && premiumUsers.size() == 0) throw new ReservationException("User id doesn't exist");

        else if (basicUsers.size() > 0) { LOGGER.info("User with id " + userId + " is from type Basic");
            Basic basic = userClient.findBasicUserById(userId);
            return basic.getRoomId().equals(roomId); }

        else if (premiumUsers.size() > 0){ LOGGER.info("User with id " + userId + " is from type Premium");
            Premium premium = userClient.findPremiumUserById(userId);
            return premium.getRoomId().equals(roomId); }
        return false;
    }
/*
    public void updateRoomReservation(UpdateRoomReservationDto updateRoomReservationDto){
        List<Basic> basicUsers = userClient.findAllBasicUser().stream().filter(user -> user.getId().equals(updateRoomReservationDto.getUserId())).collect(Collectors.toList());
        List<Premium> premiumUsers = userClient.findAllPremiumUsers().stream().filter(user -> user.getId().equals(updateRoomReservationDto.getUserId())).collect(Collectors.toList());
        Basic basic = null;
        Premium premium = null;

        if (basicUsers.size() == 0 && premiumUsers.size() == 0)
            throw new ReservationException("User id doesn't exist");

        else if (basicUsers.size() > 0) {
            LOGGER.info("User with id " + updateRoomReservationDto.getUserId() + " is from type Basic");
            basic = userClient.findBasicUserById(updateRoomReservationDto.getUserId());
            if (!basic.getName().equals(updateRoomReservationDto.getUserName()))
                throw new DataNotFoundException("User name from room reservation doesn't match the actual user name already registered");
            if (basic.getRoomId() == 0 && basic.getRoomId().equals(null))
                throw new ReservationException("Basic user hasn't a reservation on going");

            Basic finalBasic = basic;
            List<RoomReservation> roomReservations = findAllRoomReservations().stream()
                    .filter(roomReservation -> roomReservation.getRoomId().equals(finalBasic.getRoomId())).collect(Collectors.toList());
            if(roomReservations.get(0).getRoomType().equals(RoomType.REGULAR_ROOM)){
                RegularRoom oldRegularRoom = roomClient.findRegularRoomById(roomReservations.get(0).getRoomId());
                roomClient.updateRegularRoomAvailability(oldRegularRoom.getRoomId(), true);
                userClient.updateBasicBalance(finalBasic.getId(), finalBasic.getBankAccount().getBalance().increaseAmount(oldRegularRoom.getPrice()));
                checkEnoughBalance(finalBasic.getId(), TypeOfUser.BASIC, );

            }
        }

        else {
            LOGGER.info("User with id " +  updateRoomReservationDto.getUserId() + " is from type Premium");
            premium = userClient.findPremiumUserById(updateRoomReservationDto.getUserId());
            if (!premium.getName().equals(updateRoomReservationDto.getUserName()))
                throw new DataNotFoundException("User name from room reservation doesn't match the actual user name already registered");
            if (premium.getRoomId() == 0 && premium.getRoomId().equals(null))
                throw new ReservationException("Premium user hasn't a reservation on going");
        }
    }
 */
    /*
    @HystrixCommand(fallbackMethod = "removeRoomReservationFail")
    public void removeRoomReservation(Long roomReservationId) {
        LOGGER.info("[INIT] Cancel Room Reservation " + roomReservationId);
        RoomReservation roomReservation = findRoomReservationById(roomReservationId);
        Basic basic = null;
        Premium premium = null;
        if (roomReservation.getCheckInDay().isBefore(LocalDate.now())) {
            throw new ReservationException("It's not possible to cancel reservation");
        }
        if (roomReservation.getRoomType().equals(RoomType.REGULAR_ROOM)) {
            if(roomReservation.getUserReservation().getTypeOfUser().equals(TypeOfUser.BASIC)){
                basic = userClient.findBasicUserById(roomReservation.getUserReservation().getUserId());
                userClient.updateBasicRoomId(basic.getId(), 0);
                userClient.updateBasicBalance(basic.getId(), basic.getBankAccount().getBalance().increaseAmount(roomReservation.getTotalPrice().getAmount()));
                userClient.updateBasicNumberOfStays(basic.getId(), basic.getNumberOfStays()-1);
                roomClient.updateRegularRoomAvailability(roomReservation.getRoomId(), true);
            }
            else if(roomReservation.getUserReservation().getTypeOfUser().equals(TypeOfUser.PREMIUM)){
                premium = userClient.findPremiumUserById(roomReservation.getUserReservation().getUserId());
                userClient.updatePremiumRoomId(premium.getId(), 0);
                userClient.updatePremiumBalance(premium.getId(), premium.getBankAccount().getBalance().increaseAmount(roomReservation.getTotalPrice().getAmount()));
                userClient.updatePremiumNumberOfStays(premium.getId(), premium.getNumberOfStays()-1);
                roomClient.updateSuiteAvailability(roomReservation.getRoomId(), true);
            }
        } else if (roomReservation.getRoomType().equals(RoomType.SUITE_ROOM)) {
            if(roomReservation.getUserReservation().getTypeOfUser().equals(TypeOfUser.BASIC)){
                basic = userClient.findBasicUserById(roomReservation.getUserReservation().getUserId());
                userClient.updateBasicRoomId(basic.getId(), 0);
                userClient.updateBasicBalance(basic.getId(), basic.getBankAccount().getBalance().increaseAmount(roomReservation.getTotalPrice().getAmount()));
                userClient.updateBasicNumberOfStays(basic.getId(), basic.getNumberOfStays()-1);
                roomClient.updateRegularRoomAvailability(roomReservation.getRoomId(), true);
            }
            else if(roomReservation.getUserReservation().getTypeOfUser().equals(TypeOfUser.PREMIUM)){
                premium = userClient.findPremiumUserById(roomReservation.getUserReservation().getUserId());
                userClient.updatePremiumRoomId(premium.getId(), 0);
                userClient.updatePremiumBalance(premium.getId(), premium.getBankAccount().getBalance().increaseAmount(roomReservation.getTotalPrice().getAmount()));
                userClient.updatePremiumNumberOfStays(premium.getId(), premium.getNumberOfStays()-1);
                roomClient.updateSuiteAvailability(roomReservation.getRoomId(), true);
            }
        }
        roomReservationRepository.delete(roomReservation);
        LOGGER.info("[END] Cancel Room Reservation " + roomReservationId);
    }
    *

    /**
     * New Room Reservation Fail
     * @param roomReservationViewModel receives a Room Reservation View Model
     * @return a RoomReservation
     */
    public RoomReservation newRoomReservationFail(RoomReservationViewModel roomReservationViewModel) {
        LOGGER.warn("[WARN] It wasn't possible to make a new Room Reservation");
        return null;
    }

    /**
     * Room Availability Fail
     * @param roomId receives an Integer with roomId
     * @param roomType receives a RoomType with roomType
     */
    public void roomAvailabilityFail(Integer roomId, RoomType roomType) {
        LOGGER.warn("[WARN] It wasn't possible to check room's availability");
        throw new ReservationException("It wasn't possible to check room's availability");
    }

    /**
     * Remove Room Reservation Fail
     * @param roomReservationId receives a long with roomReservationId
     */
    /*
    public void removeRoomReservationFail(Long roomReservationId) {
        LOGGER.warn("[WARN] It wasn't possible to remove Room Reservation");
        throw new ReservationException("It wasn't possible to remove Room Reservation");
    }
    */

    /**
     * Check Enough Balance Fail
     * @param userId receives a Long with userId
     * @param typeOfUser receives a TypeOfUser with typeOfUser
     * @param price receives a Money with price
     */
    public void checkEnoughBalanceFail(Long userId, TypeOfUser typeOfUser, Money price) {
        LOGGER.warn("[WARN] It wasn't possible to check user's balance");
        throw new NotEnoughBalanceException("It wasn't possible to remove Room Reservation");
    }
}

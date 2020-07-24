package com.ironhack.edgeservice.service;

import com.ironhack.edgeservice.client.ActivityClient;
import com.ironhack.edgeservice.client.InvoiceClient;
import com.ironhack.edgeservice.client.UserClient;
import com.ironhack.edgeservice.dto.*;
import com.ironhack.edgeservice.model.entities.activities.Massage;
import com.ironhack.edgeservice.model.entities.activities.PoolRent;
import com.ironhack.edgeservice.model.entities.activities.RoomFood;
import com.ironhack.edgeservice.model.viewModel.MassageViewModel;
import com.ironhack.edgeservice.model.viewModel.PoolRentViewModel;
import com.ironhack.edgeservice.model.viewModel.RoomFoodViewModel;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Activity Service
 */
@Service
public class ActivityService {
    /**
     * Autowired of ActivityClient
     */
    @Autowired
    private ActivityClient activityClient;
    /**
     * Autowired of UserClient
     */
    @Autowired
    private UserClient userClient;
    /**
     * Autowired of SecurityService
     */
    @Autowired
    private SecurityService securityService;
    /**
     * Autowired of InvoiceClient
     */
    @Autowired
    private InvoiceClient invoiceClient;

    /**
     * Final variable of Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(ActivityService.class);

    // --- MASSAGES ---
    /**
     * Find All Massages
     * @return a list of massages
     */
    @HystrixCommand(fallbackMethod = "findAllMassagesFail", ignoreExceptions = ResponseStatusException.class)
    public List<Massage> findAllMassages(String authorizationHeader){
        LOGGER.info("Find All Massages Appointments");
        securityService.isUser(authorizationHeader);
        return activityClient.findAllMassages(); }

    /**
     * Find Massage by id
     * @param massageId receives a long with massageId
     * @return a Massage
     */
    @HystrixCommand(fallbackMethod = "findMassageByIdFail", ignoreExceptions = ResponseStatusException.class)
    public Massage findMassageById(Long massageId, String authorizationHeader){
        LOGGER.info("[INIT] Find Massage Appointment with id " + massageId);
        securityService.isUser(authorizationHeader);
        Massage massage = activityClient.findMassageById(massageId);
        LOGGER.info("[END] Find Massage Appointment with id " + massageId);
        return massage; }

    /**
     * Filter Massage By User Id
     * @param userId receives an Long with userId
     * @return a list of object
     */
    @HystrixCommand(fallbackMethod = "filterMassageByUserIdFail", ignoreExceptions = ResponseStatusException.class)
    public List<Massage> filterMassageByUserId(Long userId, String authorizationHeader){
        LOGGER.info("Filter Massage Reservation from user with id " + userId);
        securityService.isUser(authorizationHeader);
        return activityClient.filterMassageByUserId(userId); }

    /**
     * Create Massage Appointment
     * @param massageViewModel receives a Massage View Model
     * @return a Massage created
     */
    @HystrixCommand(fallbackMethod = "createMassageAppointmentFail", ignoreExceptions = ResponseStatusException.class)
    public Massage createMassageAppointment(MassageViewModel massageViewModel, String authorizationHeader){
        LOGGER.info("Create new Massage Appointment");
        securityService.isUser(authorizationHeader);
        return activityClient.createMassageAppointment(massageViewModel); }

    /**
     * Change Massage Type
     * @param updateMassageTypeDto receives an UpdateMassageTypeDto
     */
    @HystrixCommand(fallbackMethod = "changeMassageTypeFail", ignoreExceptions = ResponseStatusException.class)
    public void changeMassageType(UpdateMassageTypeDto updateMassageTypeDto, String authorizationHeader){
        LOGGER.info("Update Massage Appointment " + updateMassageTypeDto.getMassageId() + " type of massage to " + updateMassageTypeDto.getMassageType());
        securityService.isUser(authorizationHeader);
        activityClient.changeMassageType(updateMassageTypeDto); }

    /**
     * Cancel Massage Appointment
     * @param massageId receives a long with massageId
     */
    @HystrixCommand(fallbackMethod = "deleteRequestFail", ignoreExceptions = ResponseStatusException.class)
    public void deleteMassageAppointment(Long massageId, String authorizationHeader){
        LOGGER.info("Cancel Massage Appointment with id "+ massageId);
        securityService.isUser(authorizationHeader);
        activityClient.deleteMassageAppointment(massageId); }

    // --- ROOM FOOD SERVICES ---
    /**
     * Find All Room Foods
     * @return a list of RoomFood
     */
    @HystrixCommand(fallbackMethod = "findAllRoomFoodFail", ignoreExceptions = ResponseStatusException.class)
    public List<RoomFood> findAllRoomFood(String authorizationHeader){
        LOGGER.info("Find All Room Food Requests");
        securityService.isUser(authorizationHeader);
        return activityClient.findAllRoomFood(); }

    /**
     * Find RoomFood by id
     * @param roomFoodId receives a Long with roomFoodId
     * @return a RoomFood
     */
    @HystrixCommand(fallbackMethod = "findRoomFoodByIdFail", ignoreExceptions = ResponseStatusException.class)
    public RoomFood findRoomFoodById(Long roomFoodId, String authorizationHeader){
        LOGGER.info("Find Room Food Request with id " + roomFoodId);
        securityService.isUser(authorizationHeader);
        return activityClient.findRoomFoodById(roomFoodId); }

    /**
     * Filter Room Food By User Id
     * @param userId receives a Long with userId
     * @return a list of objects
     */
    @HystrixCommand(fallbackMethod = "filterByUserIdFail", ignoreExceptions = ResponseStatusException.class)
    public List<RoomFood> filterRoomFoodByUserId(Long userId, String authorizationHeader){
        LOGGER.info("Filter Room Food Request from user with id " + userId);
        securityService.isUser(authorizationHeader);
        return activityClient.filterRoomFoodByUserId(userId); }

    /**
     * Create new Room Food Request
     * @param roomFoodViewModel receives a RoomFoodViewModel
     * @return a RoomFood
     */
    @HystrixCommand(fallbackMethod = "createRoomFoodRequestFail", ignoreExceptions = ResponseStatusException.class)
    public RoomFood createRoomFoodRequest(RoomFoodViewModel roomFoodViewModel, String authorizationHeader){
        LOGGER.info("Create new Room Food Request");
        securityService.isUser(authorizationHeader);
        return activityClient.createRoomFoodRequest(roomFoodViewModel); }

    /**
     * Deliver Room Food
     * @param roomFoodId receives a Long with roomFoodId
     */
    @HystrixCommand(fallbackMethod = "deliverRoomFoodFail", ignoreExceptions = ResponseStatusException.class)
    public void deliverRoomFood(Long roomFoodId, String authorizationHeader){
        LOGGER.info("Deliver Room Food Request " + roomFoodId);
        securityService.isUser(authorizationHeader);
        RoomFood roomFood = activityClient.findRoomFoodById(roomFoodId);
        activityClient.deliverRoomFood(roomFoodId); }

    /**
     * Update Food Menu
     * @param updateRoomFoodMenuDto receives a UpdateRoomFoodMenuDto
     */
    @HystrixCommand(fallbackMethod = "updateFoodMenuFail", ignoreExceptions = ResponseStatusException.class)
    public void updateFoodMenu(UpdateRoomFoodMenuDto updateRoomFoodMenuDto, String authorizationHeader){
        LOGGER.info("Update Food of Room Food Request " + updateRoomFoodMenuDto.getRoomFoodId());
        securityService.isUser(authorizationHeader);
        RoomFood roomFood = activityClient.findRoomFoodById(updateRoomFoodMenuDto.getRoomFoodId());
        activityClient.updateFoodMenu(updateRoomFoodMenuDto); }

    /**
     * Update Drink Menu
     * @param updateDrinkMenuDto receives an UpdateDrinkMenuDto
     */
    @HystrixCommand(fallbackMethod = "updateDrinkMenuFail", ignoreExceptions = ResponseStatusException.class)
    public void updateDrinkMenu(UpdateDrinkMenuDto updateDrinkMenuDto, String authorizationHeader){
        LOGGER.info("Update Drink of Room Food Request " + updateDrinkMenuDto.getRoomFoodId());
        securityService.isUser(authorizationHeader);
        RoomFood roomFood = activityClient.findRoomFoodById(updateDrinkMenuDto.getRoomFoodId());
        /*if (roomFood == null){
            LOGGER.warn("[WARN] - Room Food not found with id " + updateDrinkMenuDto.getRoomFoodId());
            throw new NullPointerException("There's no Room Food with id " + updateDrinkMenuDto.getRoomFoodId());
             }*/
        activityClient.updateDrinkMenu(updateDrinkMenuDto); }

    /**
     * Cancel Room Food Request
     * @param roomFoodId receives a Long with roomFoodId
     */
    @HystrixCommand(fallbackMethod = "deleteRequestFail", ignoreExceptions = ResponseStatusException.class)
    public void removeRoomFoodRequest(Long roomFoodId, String authorizationHeader){
        LOGGER.info("Cancel Room Food Request with id "+ roomFoodId);
        securityService.isUser(authorizationHeader);
        RoomFood roomFood = activityClient.findRoomFoodById(roomFoodId);
        /*if (roomFood == null){
            LOGGER.warn("[WARN] - Room Food not found with id " + roomFoodId);
            throw new NullPointerException("There's no Room Food with id " + roomFoodId);
        } */
        activityClient.removeRoomFoodRequest(roomFoodId); }


    // --- POOL RENTS ---
    /**
     * Find All Pool Rent
     * @return a list of PoolRent
     */
    @HystrixCommand(fallbackMethod = "findAllPoolRentsFail", ignoreExceptions = ResponseStatusException.class)
    public List<PoolRent> findAllPoolRents(String authorizationHeader){
        LOGGER.info("Find All Pool Rents");
        securityService.isUser(authorizationHeader);
        return activityClient.findAllPoolRents(); }

    /**
     * Find PoolRent By Id
     * @param poolRentId receives a Long with poolRentId
     * @return a PoolRent
     */
    @HystrixCommand(fallbackMethod = "findPoolRentByIdFail", ignoreExceptions = ResponseStatusException.class)
    public PoolRent findPoolRentById(Long poolRentId, String authorizationHeader){
        LOGGER.info("Find Pool Rent with id " + poolRentId);
        securityService.isUser(authorizationHeader);
        PoolRent poolRent = activityClient.findPoolRentById(poolRentId);
        return activityClient.findPoolRentById(poolRentId); }

    /**
     * Filter Pool Rent By User Id
     * @param userId receives a Long with userId
     * @returnq list of objects
     */
    @HystrixCommand(fallbackMethod = "filterPoolRentByUserIdFail", ignoreExceptions = ResponseStatusException.class)
    public List<PoolRent> filterPoolRentByUserId(Long userId,  String authorizationHeader){
        LOGGER.info("Filter Pool Rent Request from user with id " + userId);
        securityService.isUser(authorizationHeader);
        return activityClient.filterPoolRentByUserId(userId); }

    /**
     * Create Pool Rent
     * @param poolRentViewModel receives a PoolRentViewModel
     * @return a PoolRent
     */
    @HystrixCommand(fallbackMethod = "createPoolRentFail", ignoreExceptions = ResponseStatusException.class)
    public PoolRent createPoolRent(PoolRentViewModel poolRentViewModel, String authorizationHeader){
        LOGGER.info("Create new Pool Rent");
        securityService.isUser(authorizationHeader);
        return activityClient.createPoolRent(poolRentViewModel); }

    /**
     * Update Floaties Number
     * @param updateFloatiesNumDto receives a UpdateFloatiesNumDto with updateFloatiesNumDto
     */
    @HystrixCommand(fallbackMethod = "updateFloatiesNumFail", ignoreExceptions = ResponseStatusException.class)
    public void updateFloatiesNum(UpdateFloatiesNumDto updateFloatiesNumDto, String authorizationHeader){
        LOGGER.info("Update Pool Rent " + updateFloatiesNumDto.getPoolRentId() + " with " + updateFloatiesNumDto.getFloatiesNum() + " number of floaties");
        securityService.isUser(authorizationHeader);
        activityClient.updateFloatiesNum(updateFloatiesNumDto); }

    /**
     * Update Towel Number
     * @param updateTowelNumDto receives a UpdateTowelNumDto
     */
    @HystrixCommand(fallbackMethod = "updateTowelNumFail", ignoreExceptions = ResponseStatusException.class)
    public void updateTowelNum(UpdateTowelNumDto updateTowelNumDto, String authorizationHeader){
        LOGGER.info("Update Pool Rent " + updateTowelNumDto.getPoolRentId() + " with " + updateTowelNumDto.getTowelNum() + " number of towels");
        securityService.isUser(authorizationHeader);
        activityClient.updateTowelNum(updateTowelNumDto); }

    /**
     * Cancel Pool Rent
     * @param poolRentId receives a Long with poolRentId
     */
    @HystrixCommand(fallbackMethod = "deleteRequestFail", ignoreExceptions = ResponseStatusException.class)
    public void removePoolRent(Long poolRentId, String authorizationHeader){
        LOGGER.info("Cancel Pool Rent with id "+ poolRentId);
        securityService.isUser(authorizationHeader);
        activityClient.removePoolRent(poolRentId); }


    // --- FALLBACK METHODS MASSAGE ---
    public List<Massage> findAllMassagesFail(String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to find all Massages"); return null; }

    public Massage findMassageByIdFail(Long massageId, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to find Massage with id " + massageId); return null; }

    public List<Object[]> filterByUserIdFail(Long userId, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to filter activity by user"); return null; }

    public Massage createMassageAppointmentFail(MassageViewModel massageViewModel, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to make a new Massage Appointment");
        return null; }

    public void changeMassageTypeFail(UpdateMassageTypeDto updateMassageTypeDto, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to change massage type of massage with id " + updateMassageTypeDto.getMassageId()); }


    // --- FALLBACK METHODS ROOM FOOD ---
    public List<RoomFood> findAllRoomFoodFail(String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to find all Room Food Requests"); return null; }

    public RoomFood findRoomFoodByIdFail(Long roomFoodId, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to find Room Food Request with id " + roomFoodId); return null; }

    public RoomFood createRoomFoodRequestFail(RoomFoodViewModel roomFoodViewModel, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to make a new Room Food Request"); return null; }

    public void deliverRoomFoodFail(Long roomFoodId, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to deliver Room Food Request " + roomFoodId); }

    public void updateFoodMenuFail(UpdateRoomFoodMenuDto updateRoomFoodMenuDto, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to update Food Menu from Room Food Request " + updateRoomFoodMenuDto.getRoomFoodId()); }

    public void updateDrinkMenuFail(UpdateDrinkMenuDto updateDrinkMenuDto, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to update Drink Menu from Room Food Request " + updateDrinkMenuDto.getRoomFoodId()); }

    // --- FALLBACK METHODS POOL RENT ---
    public List<PoolRent> findAllPoolRentsFail(String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to find all Pool Rents"); return null; }

    public PoolRent findPoolRentByIdFail(Long poolRentId, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to find Pool Rent with id " + poolRentId); return null; }

    public PoolRent createPoolRentFail(PoolRentViewModel poolRentViewModel, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to make a new Pool Rent"); return null; }

    public void updateFloatiesNumFail(UpdateFloatiesNumDto updateFloatiesNumDto, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to update Pool Rent " + updateFloatiesNumDto.getPoolRentId() + " with " + updateFloatiesNumDto.getFloatiesNum() + " number of floaties"); }

    public void updateTowelNumFail(UpdateTowelNumDto updateTowelNumDto, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to update Pool Rent " + updateTowelNumDto.getPoolRentId() + " with " + updateTowelNumDto.getTowelNum() + " number of towels"); }

    public void deleteRequestFail(Long activityId, String authorizationHeader){ LOGGER.warn("[WARN] It wasn't possible to cancel activity"); }

    public List<Massage> filterMassageByUserIdFail(Long userId, String authorizationHeader){ LOGGER.warn("[WARN] It wasn't possible to filter massage by user id " + userId); return null; }

    public List<PoolRent> filterPoolRentByUserIdFail(Long userId,  String authorizationHeader){ LOGGER.warn("[WARN] It wasn't possible to pool rent by user id " + userId); return null; }
}

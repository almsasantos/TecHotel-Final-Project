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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ActivityService {
    @Autowired
    private ActivityClient activityClient;

    @Autowired
    private UserClient userClient;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private InvoiceClient invoiceClient;

    private static final Logger LOGGER = LogManager.getLogger(ActivityService.class);

    // --- MASSAGES ---
    @HystrixCommand(fallbackMethod = "findAllMassagesFail", ignoreExceptions = ResponseStatusException.class)
    public List<Massage> findAllMassages(String authorizationHeader){
        LOGGER.info("Find All Massages Appointments");
        securityService.isUser(authorizationHeader);
        return activityClient.findAllMassages();
    }

    @HystrixCommand(fallbackMethod = "findMassageByIdFail", ignoreExceptions = ResponseStatusException.class)
    public Massage findMassageById(Long massageId, String authorizationHeader){
        LOGGER.info("[INIT] Find Massage Appointment with id " + massageId);
        securityService.isUser(authorizationHeader);
        Massage massage = activityClient.findMassageById(massageId);
        LOGGER.info("[END] Find Massage Appointment with id " + massageId);
        return massage;
    }

    @HystrixCommand(fallbackMethod = "filterByUserIdFail", ignoreExceptions = ResponseStatusException.class)
    public List<Object[]> filterMassageByUserId(Long userId, String authorizationHeader){
        LOGGER.info("Filter Massage Reservation from user with id " + userId);
        securityService.isUser(authorizationHeader);
        return activityClient.filterMassageByUserId(userId);
    }

    @HystrixCommand(fallbackMethod = "createMassageAppointmentFail", ignoreExceptions = ResponseStatusException.class)
    public Massage createMassageAppointment(MassageViewModel massageViewModel, String authorizationHeader){
        LOGGER.info("Create new Massage Appointment");
        securityService.isUser(authorizationHeader);
        return activityClient.createMassageAppointment(massageViewModel);
    }

    @HystrixCommand(fallbackMethod = "changeMassageTypeFail", ignoreExceptions = ResponseStatusException.class)
    public void changeMassageType(UpdateMassageTypeDto updateMassageTypeDto, String authorizationHeader){
        LOGGER.info("Update Massage Appointment " + updateMassageTypeDto.getMassageId() + " type of massage to " + updateMassageTypeDto.getMassageType());
        securityService.isUser(authorizationHeader);
        Massage massage = activityClient.findMassageById(updateMassageTypeDto.getMassageId());
        activityClient.changeMassageType(updateMassageTypeDto);
    }

    @HystrixCommand(fallbackMethod = "deleteRequestFail", ignoreExceptions = ResponseStatusException.class)
    public void deleteMassageAppointment(Long massageId, String authorizationHeader){
        LOGGER.info("Cancel Massage Appointment with id "+ massageId);
        securityService.isUser(authorizationHeader);
        Massage massage = activityClient.findMassageById(massageId);
        activityClient.deleteMassageAppointment(massageId);
    }

    // --- ROOM FOOD SERVICES ---
    @HystrixCommand(fallbackMethod = "findAllRoomFoodFail", ignoreExceptions = ResponseStatusException.class)
    public List<RoomFood> findAllRoomFood(String authorizationHeader){
        LOGGER.info("Find All Room Food Requests");
        securityService.isUser(authorizationHeader);
        return activityClient.findAllRoomFood();
    }

    @HystrixCommand(fallbackMethod = "findRoomFoodByIdFail", ignoreExceptions = ResponseStatusException.class)
    public RoomFood findRoomFoodById(Long roomFoodId, String authorizationHeader){
        LOGGER.info("Find Room Food Request with id " + roomFoodId);
        securityService.isUser(authorizationHeader);
        return activityClient.findRoomFoodById(roomFoodId);
    }
    @HystrixCommand(fallbackMethod = "filterByUserIdFail", ignoreExceptions = ResponseStatusException.class)
    public List<Object[]> filterRoomFoodByUserId(Long userId, String authorizationHeader){
        LOGGER.info("Filter Room Food Request from user with id " + userId);
        securityService.isUser(authorizationHeader);
        return activityClient.filterRoomFoodByUserId(userId);
    }

    @HystrixCommand(fallbackMethod = "createRoomFoodRequestFail", ignoreExceptions = ResponseStatusException.class)
    public RoomFood createRoomFoodRequest(RoomFoodViewModel roomFoodViewModel, String authorizationHeader){
        LOGGER.info("Create new Room Food Request");
        securityService.isUser(authorizationHeader);
        return activityClient.createRoomFoodRequest(roomFoodViewModel);
    }

    @HystrixCommand(fallbackMethod = "deliverRoomFoodFail", ignoreExceptions = ResponseStatusException.class)
    public void deliverRoomFood(Long roomFoodId, String authorizationHeader){
        LOGGER.info("Deliver Room Food Request " + roomFoodId);
        securityService.isUser(authorizationHeader);
        RoomFood roomFood = activityClient.findRoomFoodById(roomFoodId);
        activityClient.deliverRoomFood(roomFoodId);
    }

    @HystrixCommand(fallbackMethod = "updateFoodMenuFail", ignoreExceptions = ResponseStatusException.class)
    public void updateFoodMenu(UpdateRoomFoodMenuDto updateRoomFoodMenuDto, String authorizationHeader){
        LOGGER.info("Update Food of Room Food Request " + updateRoomFoodMenuDto.getRoomFoodId());
        securityService.isUser(authorizationHeader);
        RoomFood roomFood = activityClient.findRoomFoodById(updateRoomFoodMenuDto.getRoomFoodId());
        activityClient.updateFoodMenu(updateRoomFoodMenuDto);
    }

    @HystrixCommand(fallbackMethod = "updateDrinkMenuFail", ignoreExceptions = ResponseStatusException.class)
    public void updateDrinkMenu(UpdateDrinkMenuDto updateDrinkMenuDto, String authorizationHeader){
        LOGGER.info("Update Drink of Room Food Request " + updateDrinkMenuDto.getRoomFoodId());
        securityService.isUser(authorizationHeader);
        RoomFood roomFood = activityClient.findRoomFoodById(updateDrinkMenuDto.getRoomFoodId());
        /*if (roomFood == null){
            LOGGER.warn("[WARN] - Room Food not found with id " + updateDrinkMenuDto.getRoomFoodId());
            throw new NullPointerException("There's no Room Food with id " + updateDrinkMenuDto.getRoomFoodId());
             }*/
        activityClient.updateDrinkMenu(updateDrinkMenuDto);
    }

    @HystrixCommand(fallbackMethod = "deleteRequestFail", ignoreExceptions = ResponseStatusException.class)
    public void removeRoomFoodRequest(Long roomFoodId, String authorizationHeader){
        LOGGER.info("Cancel Room Food Request with id "+ roomFoodId);
        securityService.isUser(authorizationHeader);
        RoomFood roomFood = activityClient.findRoomFoodById(roomFoodId);
        /*if (roomFood == null){
            LOGGER.warn("[WARN] - Room Food not found with id " + roomFoodId);
            throw new NullPointerException("There's no Room Food with id " + roomFoodId);
        } */
        activityClient.removeRoomFoodRequest(roomFoodId);
    }


    // --- POOL RENTS ---
    @HystrixCommand(fallbackMethod = "findAllPoolRentsFail", ignoreExceptions = ResponseStatusException.class)
    public List<PoolRent> findAllPoolRents(String authorizationHeader){
        LOGGER.info("Find All Pool Rents");
        securityService.isUser(authorizationHeader);
        return activityClient.findAllPoolRents();
    }

    @HystrixCommand(fallbackMethod = "findPoolRentByIdFail", ignoreExceptions = ResponseStatusException.class)
    public PoolRent findPoolRentById(Long poolRentId, String authorizationHeader){
        LOGGER.info("Find Pool Rent with id " + poolRentId);
        securityService.isUser(authorizationHeader);
        PoolRent poolRent = activityClient.findPoolRentById(poolRentId);
        return activityClient.findPoolRentById(poolRentId);
    }

    @HystrixCommand(fallbackMethod = "filterByUserIdFail", ignoreExceptions = ResponseStatusException.class)
    public List<Object[]> filterPoolRentByUserId(@PathVariable("userId") Long userId,  String authorizationHeader){
        LOGGER.info("Filter Pool Rent Request from user with id " + userId);
        securityService.isUser(authorizationHeader);
        return activityClient.filterPoolRentByUserId(userId);
    }

    @HystrixCommand(fallbackMethod = "createPoolRentFail", ignoreExceptions = ResponseStatusException.class)
    public PoolRent createPoolRent(PoolRentViewModel poolRentViewModel, String authorizationHeader){
        LOGGER.info("Create new Pool Rent");
        securityService.isUser(authorizationHeader);
        return activityClient.createPoolRent(poolRentViewModel);
    }

    @HystrixCommand(fallbackMethod = "updateFloatiesNumFail", ignoreExceptions = ResponseStatusException.class)
    public void updateFloatiesNum(UpdateFloatiesNumDto updateFloatiesNumDto, String authorizationHeader){
        LOGGER.info("Update Pool Rent " + updateFloatiesNumDto.getPoolRentId() + " with " + updateFloatiesNumDto.getFloatiesNum() + " number of floaties");
        securityService.isUser(authorizationHeader);
        activityClient.updateFloatiesNum(updateFloatiesNumDto);
    }

    @HystrixCommand(fallbackMethod = "updateTowelNumFail", ignoreExceptions = ResponseStatusException.class)
    public void updateTowelNum(UpdateTowelNumDto updateTowelNumDto, String authorizationHeader){
        LOGGER.info("Update Pool Rent " + updateTowelNumDto.getPoolRentId() + " with " + updateTowelNumDto.getTowelNum() + " number of towels");
        securityService.isUser(authorizationHeader);
        activityClient.updateTowelNum(updateTowelNumDto);
    }

    @HystrixCommand(fallbackMethod = "deleteRequestFail", ignoreExceptions = ResponseStatusException.class)
    public void removePoolRent(Long poolRentId, String authorizationHeader){
        LOGGER.info("Cancel Pool Rent with id "+ poolRentId);
        securityService.isUser(authorizationHeader);
        activityClient.removePoolRent(poolRentId);
    }


    // --- FALLBACK METHODS MASSAGE ---
    public List<Massage> findAllMassagesFail(String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to find all Massages");
        return null;
    }

    public Massage findMassageByIdFail(Long massageId, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to find Massage with id " + massageId);
        return null;
    }

    public List<Object[]> filterByUserIdFail(Long userId, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to filter activity by user");
        return null;
    }

    public Massage createMassageAppointmentFail(MassageViewModel massageViewModel, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to make a new Massage Appointment");
        return null;
    }

    public void changeMassageTypeFail(UpdateMassageTypeDto updateMassageTypeDto, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to change massage type of massage with id " + updateMassageTypeDto.getMassageId());
    }


    // --- FALLBACK METHODS ROOM FOOD ---
    public List<RoomFood> findAllRoomFoodFail(String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to find all Room Food Requests");
        return null;
    }

    public RoomFood findRoomFoodByIdFail(Long roomFoodId, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to find Room Food Request with id " + roomFoodId);
        return null;
    }

    public RoomFood createRoomFoodRequestFail(RoomFoodViewModel roomFoodViewModel, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to make a new Room Food Request");
        return null;
    }

    public void deliverRoomFoodFail(Long roomFoodId, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to deliver Room Food Request " + roomFoodId);
    }

    public void updateFoodMenuFail(UpdateRoomFoodMenuDto updateRoomFoodMenuDto, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to update Food Menu from Room Food Request " + updateRoomFoodMenuDto.getRoomFoodId());
    }

    public void updateDrinkMenuFail(UpdateDrinkMenuDto updateDrinkMenuDto, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to update Drink Menu from Room Food Request " + updateDrinkMenuDto.getRoomFoodId());
    }

    // --- FALLBACK METHODS POOL RENT ---
    public List<PoolRent> findAllPoolRentsFail(String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to find all Pool Rents");
        return null;
    }

    public PoolRent findPoolRentByIdFail(Long poolRentId, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to find Pool Rent with id " + poolRentId);
        return null;
    }

    public PoolRent createPoolRentFail(PoolRentViewModel poolRentViewModel, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to make a new Pool Rent");
        return null;
    }

    public void updateFloatiesNumFail(UpdateFloatiesNumDto updateFloatiesNumDto, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to update Pool Rent " + updateFloatiesNumDto.getPoolRentId() + " with " + updateFloatiesNumDto.getFloatiesNum() + " number of floaties");
    }

    public void updateTowelNumFail(UpdateTowelNumDto updateTowelNumDto, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to update Pool Rent " + updateTowelNumDto.getPoolRentId() + " with " + updateTowelNumDto.getTowelNum() + " number of towels");
    }

    public void deleteRequestFail(Long activityId, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to cancel activity");
    }
}

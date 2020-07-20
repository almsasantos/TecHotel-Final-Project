package com.ironhack.edgeservice.controller.impl;

import com.ironhack.edgeservice.dto.*;
import com.ironhack.edgeservice.model.entities.activities.Massage;
import com.ironhack.edgeservice.model.entities.activities.PoolRent;
import com.ironhack.edgeservice.model.entities.activities.RoomFood;
import com.ironhack.edgeservice.model.viewModel.MassageViewModel;
import com.ironhack.edgeservice.model.viewModel.PoolRentViewModel;
import com.ironhack.edgeservice.model.viewModel.RoomFoodViewModel;
import com.ironhack.edgeservice.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    // --- MASSAGES ---
    @GetMapping("/activities/massages")
    @ResponseStatus(HttpStatus.OK)
    public List<Massage> findAllMassages(@RequestHeader(value = "Authorization") String authorizationHeader){
        return activityService.findAllMassages(authorizationHeader);
    }

    @GetMapping("/activities/massages/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Massage findMassageById(@PathVariable("id") Long massageId, @RequestHeader(value = "Authorization") String authorizationHeader){
        return activityService.findMassageById(massageId, authorizationHeader);
    }

    @PostMapping("/activities/massages")
    @ResponseStatus(HttpStatus.CREATED)
    public Massage createMassageAppointment(@RequestBody @Valid MassageViewModel massageViewModel, @RequestHeader(value = "Authorization") String authorizationHeader){
        return activityService.createMassageAppointment(massageViewModel, authorizationHeader);
    }

    @PatchMapping("/activities/massages/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeMassageType(@RequestBody @Valid UpdateMassageTypeDto updateMassageTypeDto, @RequestHeader(value = "Authorization") String authorizationHeader){
        activityService.changeMassageType(updateMassageTypeDto, authorizationHeader);
    }

    @DeleteMapping("/activities/massages/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMassageAppointment(@PathVariable("id") Long massageId, @RequestHeader(value = "Authorization") String authorizationHeader){
        activityService.deleteMassageAppointment(massageId, authorizationHeader);
    }


    // --- ROOM FOOD SERVICES ---
    @GetMapping("/activities/room-food-services")
    @ResponseStatus(HttpStatus.OK)
    public List<RoomFood> findAllRoomFood(@RequestHeader(value = "Authorization") String authorizationHeader){
        return activityService.findAllRoomFood(authorizationHeader);
    }

    @GetMapping("/activities/room-food-services/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoomFood findRoomFoodById(@PathVariable("id") Long roomFoodId, @RequestHeader(value = "Authorization") String authorizationHeader){
        return activityService.findRoomFoodById(roomFoodId, authorizationHeader);
    }

    @PostMapping("/activities/room-food-services")
    @ResponseStatus(HttpStatus.CREATED)
    public RoomFood createRoomFoodRequest(@RequestBody @Valid RoomFoodViewModel roomFoodViewModel, @RequestHeader(value = "Authorization") String authorizationHeader){
        return activityService.createRoomFoodRequest(roomFoodViewModel, authorizationHeader);
    }

    @PatchMapping("/activities/room-food-services/deliver/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deliverRoomFood(@PathVariable("id") Long roomFoodId, @RequestHeader(value = "Authorization") String authorizationHeader){
        activityService.deliverRoomFood(roomFoodId, authorizationHeader);
    }

    @PatchMapping("/activities/room-food-services/update-food")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateFoodMenu(@RequestBody @Valid UpdateRoomFoodMenuDto updateRoomFoodMenuDto, @RequestHeader(value = "Authorization") String authorizationHeader){
        activityService.updateFoodMenu(updateRoomFoodMenuDto, authorizationHeader);
    }

    @PatchMapping("/activities/room-food-services/update-drink")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateDrinkMenu(@RequestBody @Valid UpdateDrinkMenuDto updateDrinkMenuDto, @RequestHeader(value = "Authorization") String authorizationHeader){
        activityService.updateDrinkMenu(updateDrinkMenuDto, authorizationHeader);
    }

    @DeleteMapping("/activities/room-food-services/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeRoomFoodRequest(@PathVariable("id") Long roomFoodId, @RequestHeader(value = "Authorization") String authorizationHeader){
        activityService.removeRoomFoodRequest(roomFoodId, authorizationHeader);
    }


    // --- POOL RENTS ---
    @GetMapping("/activities/pool-rents")
    @ResponseStatus(HttpStatus.OK)
    public List<PoolRent> findAllPoolRents(@RequestHeader(value = "Authorization") String authorizationHeader){
        return activityService.findAllPoolRents(authorizationHeader);
    }

    @GetMapping("/activities/pool-rents/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PoolRent findPoolRentById(@PathVariable("id") Long poolRentId, @RequestHeader(value = "Authorization") String authorizationHeader){
        return activityService.findPoolRentById(poolRentId, authorizationHeader);
    }

    @PostMapping("/activities/pool-rents")
    @ResponseStatus(HttpStatus.CREATED)
    public PoolRent createPoolRent(@RequestBody @Valid PoolRentViewModel poolRentViewModel, @RequestHeader(value = "Authorization") String authorizationHeader){
        return activityService.createPoolRent(poolRentViewModel, authorizationHeader);
    }

    @PatchMapping("/activities/pool-rents/update-floaties")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateFloatiesNum(@RequestBody @Valid UpdateFloatiesNumDto updateFloatiesNumDto, @RequestHeader(value = "Authorization") String authorizationHeader){
        activityService.updateFloatiesNum(updateFloatiesNumDto, authorizationHeader);
    }

    @PatchMapping("/activities/pool-rents/update-towels")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTowelNum(@RequestBody @Valid UpdateTowelNumDto updateTowelNumDto, @RequestHeader(value = "Authorization") String authorizationHeader){
        activityService.updateTowelNum(updateTowelNumDto, authorizationHeader);
    }

    @DeleteMapping("/activities/pool-rents/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePoolRent(@PathVariable("id") Long poolRentId, @RequestHeader(value = "Authorization") String authorizationHeader){
        activityService.removePoolRent(poolRentId, authorizationHeader);
    }
}

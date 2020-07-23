package com.ironhack.edgeservice.controller.interfaces;

import com.ironhack.edgeservice.dto.*;
import com.ironhack.edgeservice.model.entities.activities.Massage;
import com.ironhack.edgeservice.model.entities.activities.PoolRent;
import com.ironhack.edgeservice.model.entities.activities.RoomFood;
import com.ironhack.edgeservice.model.viewModel.MassageViewModel;
import com.ironhack.edgeservice.model.viewModel.PoolRentViewModel;
import com.ironhack.edgeservice.model.viewModel.RoomFoodViewModel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.validation.Valid;
import java.util.List;

/**
 * Activity Controller Interface
 */
public interface IActivityController {
    // --- MASSAGES ---
    /**
     * Find All Massages
     * @return a list of massages
     */
    public List<Massage> findAllMassages(@RequestHeader(value = "Authorization") String authorizationHeader);
    /**
     * Find Massage by id
     * @param massageId receives a long with massageId
     * @return a Massage
     */
    public Massage findMassageById(@PathVariable("id") Long massageId, @RequestHeader(value = "Authorization") String authorizationHeader);
    /**
     * Filter Massage By User Id
     * @param userId receives an Long with userId
     * @return a list of object
     */
    public List<Object[]> filterMassageByUserId(@PathVariable("userId") Long userId, @RequestHeader(value = "Authorization") String authorizationHeader);
    /**
     * Create Massage Appointment
     * @param massageViewModel receives a Massage View Model
     * @return a Massage created
     */
    public Massage createMassageAppointment(@RequestBody @Valid MassageViewModel massageViewModel, @RequestHeader(value = "Authorization") String authorizationHeader);
    /**
     * Change Massage Type
     * @param updateMassageTypeDto receives an UpdateMassageTypeDto
     */
    public void changeMassageType(@RequestBody @Valid UpdateMassageTypeDto updateMassageTypeDto, @RequestHeader(value = "Authorization") String authorizationHeader);
    /**
     * Cancel Massage Appointment
     * @param massageId receives a long with massageId
     */
    public void deleteMassageAppointment(@PathVariable("id") Long massageId, @RequestHeader(value = "Authorization") String authorizationHeader);


    // --- ROOM FOOD SERVICES ---
    /**
     * Find All Room Foods
     * @return a list of RoomFood
     */
    public List<RoomFood> findAllRoomFood(@RequestHeader(value = "Authorization") String authorizationHeader);
    /**
     * Find RoomFood by id
     * @param roomFoodId receives a Long with roomFoodId
     * @return a RoomFood
     */
    public RoomFood findRoomFoodById(@PathVariable("id") Long roomFoodId, @RequestHeader(value = "Authorization") String authorizationHeader);
    /**
     * Filter Room Food By User Id
     * @param userId receives a Long with userId
     * @return a list of objects
     */
    public List<Object[]> filterRoomFoodByUserId(@PathVariable("userId") Long userId, @RequestHeader(value = "Authorization") String authorizationHeader);
    /**
     * Create new Room Food Request
     * @param roomFoodViewModel receives a RoomFoodViewModel
     * @return a RoomFood
     */
    public RoomFood createRoomFoodRequest(@RequestBody @Valid RoomFoodViewModel roomFoodViewModel, @RequestHeader(value = "Authorization") String authorizationHeader);
    /**
     * Deliver Room Food
     * @param roomFoodId receives a Long with roomFoodId
     */
    public void deliverRoomFood(@PathVariable("id") Long roomFoodId, @RequestHeader(value = "Authorization") String authorizationHeader);
    /**
     * Update Food Menu
     * @param updateRoomFoodMenuDto receives a UpdateRoomFoodMenuDto
     */
    public void updateFoodMenu(@RequestBody @Valid UpdateRoomFoodMenuDto updateRoomFoodMenuDto, @RequestHeader(value = "Authorization") String authorizationHeader);
    /**
     * Update Drink Menu
     * @param updateDrinkMenuDto receives an UpdateDrinkMenuDto
     */
    public void updateDrinkMenu(@RequestBody @Valid UpdateDrinkMenuDto updateDrinkMenuDto, @RequestHeader(value = "Authorization") String authorizationHeader);
    /**
     * Cancel Room Food Request
     * @param roomFoodId receives a Long with roomFoodId
     */
    public void removeRoomFoodRequest(@PathVariable("id") Long roomFoodId, @RequestHeader(value = "Authorization") String authorizationHeader);


    // --- POOL RENTS ---
    /**
     * Find All Pool Rent
     * @return a list of PoolRent
     */
    public List<PoolRent> findAllPoolRents(@RequestHeader(value = "Authorization") String authorizationHeader);
    /**
     * Find PoolRent By Id
     * @param poolRentId receives a Long with poolRentId
     * @return a PoolRent
     */
    public PoolRent findPoolRentById(@PathVariable("id") Long poolRentId, @RequestHeader(value = "Authorization") String authorizationHeader);
    /**
     * Filter Pool Rent By User Id
     * @param userId receives a Long with userId
     * @returnq list of objects
     */
    public List<Object[]> filterPoolRentByUserId(@PathVariable("userId") Long userId,  @RequestHeader(value = "Authorization") String authorizationHeader);
    /**
     * Create Pool Rent
     * @param poolRentViewModel receives a PoolRentViewModel
     * @return a PoolRent
     */
    public PoolRent createPoolRent(@RequestBody @Valid PoolRentViewModel poolRentViewModel, @RequestHeader(value = "Authorization") String authorizationHeader);
    /**
     * Update Floaties Number
     * @param updateFloatiesNumDto receives a UpdateFloatiesNumDto with updateFloatiesNumDto
     */
    public void updateFloatiesNum(@RequestBody @Valid UpdateFloatiesNumDto updateFloatiesNumDto, @RequestHeader(value = "Authorization") String authorizationHeader);
    /**
     * Update Towel Number
     * @param updateTowelNumDto receives a UpdateTowelNumDto
     */
    public void updateTowelNum(@RequestBody @Valid UpdateTowelNumDto updateTowelNumDto, @RequestHeader(value = "Authorization") String authorizationHeader);
    /**
     * Cancel Pool Rent
     * @param poolRentId receives a Long with poolRentId
     */
    public void removePoolRent(@PathVariable("id") Long poolRentId, @RequestHeader(value = "Authorization") String authorizationHeader);
}

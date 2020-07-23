package com.ironhack.edgeservice.client;

import com.ironhack.edgeservice.dto.*;
import com.ironhack.edgeservice.model.entities.activities.Massage;
import com.ironhack.edgeservice.model.entities.activities.PoolRent;
import com.ironhack.edgeservice.model.entities.activities.RoomFood;
import com.ironhack.edgeservice.model.viewModel.MassageViewModel;
import com.ironhack.edgeservice.model.viewModel.PoolRentViewModel;
import com.ironhack.edgeservice.model.viewModel.RoomFoodViewModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Activity Client
 */
@FeignClient(name = "activity-service")
public interface ActivityClient {

    // --- MASSAGES ---
    /**
     * Find All Massages
     * @return a list of massages
     */
    @GetMapping("/activities/massages")
    @ResponseStatus(HttpStatus.OK)
    public List<Massage> findAllMassages();
    /**
     * Find Massage by id
     * @param massageId receives a long with massageId
     * @return a Massage
     */
    @GetMapping("/activities/massages/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Massage findMassageById(@PathVariable("id") Long massageId);
    /**
     * Filter Massage By User Id
     * @param userId receives an Long with userId
     * @return a list of object
     */
    @GetMapping("/activities/massages/filter/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Object[]> filterMassageByUserId(@PathVariable("userId") Long userId);
    /**
     * Create Massage Appointment
     * @param massageViewModel receives a Massage View Model
     * @return a Massage created
     */
    @PostMapping("/activities/massages")
    @ResponseStatus(HttpStatus.CREATED)
    public Massage createMassageAppointment(@RequestBody @Valid MassageViewModel massageViewModel);
    /**
     * Change Massage Type
     * @param updateMassageTypeDto receives an UpdateMassageTypeDto
     */
    @PatchMapping("/activities/massages/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeMassageType(@RequestBody @Valid UpdateMassageTypeDto updateMassageTypeDto);
    /**
     * Cancel Massage Appointment
     * @param massageId receives a long with massageId
     */
    @DeleteMapping("/activities/massages/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMassageAppointment(@PathVariable("id") Long massageId);


    // --- ROOM FOOD SERVICES ---
    /**
     * Find All Room Foods
     * @return a list of RoomFood
     */
    @GetMapping("/activities/room-food-services")
    @ResponseStatus(HttpStatus.OK)
    public List<RoomFood> findAllRoomFood();
    /**
     * Find RoomFood by id
     * @param roomFoodId receives a Long with roomFoodId
     * @return a RoomFood
     */
    @GetMapping("/activities/room-food-services/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoomFood findRoomFoodById(@PathVariable("id") Long roomFoodId);
    /**
     * Filter Room Food By User Id
     * @param userId receives a Long with userId
     * @return a list of objects
     */
    @GetMapping("/activities/room-food-services/filter/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Object[]> filterRoomFoodByUserId(@PathVariable("userId") Long userId);
    /**
     * Create new Room Food Request
     * @param roomFoodViewModel receives a RoomFoodViewModel
     * @return a RoomFood
     */
    @PostMapping("/activities/room-food-services")
    @ResponseStatus(HttpStatus.CREATED)
    public RoomFood createRoomFoodRequest(@RequestBody @Valid RoomFoodViewModel roomFoodViewModel);
    /**
     * Deliver Room Food
     * @param roomFoodId receives a Long with roomFoodId
     */
    @PatchMapping("/activities/room-food-services/deliver/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deliverRoomFood(@PathVariable("id") Long roomFoodId);
    /**
     * Update Food Menu
     * @param updateRoomFoodMenuDto receives a UpdateRoomFoodMenuDto
     */
    @PatchMapping("/activities/room-food-services/update-food")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateFoodMenu(@RequestBody @Valid UpdateRoomFoodMenuDto updateRoomFoodMenuDto);
    /**
     * Update Drink Menu
     * @param updateDrinkMenuDto receives an UpdateDrinkMenuDto
     */
    @PatchMapping("/activities/room-food-services/update-drink")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateDrinkMenu(@RequestBody @Valid UpdateDrinkMenuDto updateDrinkMenuDto);
    /**
     * Cancel Room Food Request
     * @param roomFoodId receives a Long with roomFoodId
     */
    @DeleteMapping("/activities/room-food-services/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeRoomFoodRequest(@PathVariable("id") Long roomFoodId);


    // --- POOL RENTS ---
    /**
     * Find All Pool Rent
     * @return a list of PoolRent
     */
    @GetMapping("/activities/pool-rents")
    @ResponseStatus(HttpStatus.OK)
    public List<PoolRent> findAllPoolRents();
    /**
     * Find PoolRent By Id
     * @param poolRentId receives a Long with poolRentId
     * @return a PoolRent
     */
    @GetMapping("/activities/pool-rents/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PoolRent findPoolRentById(@PathVariable("id") Long poolRentId);
    /**
     * Filter Pool Rent By User Id
     * @param userId receives a Long with userId
     * @returnq list of objects
     */
    @GetMapping("/activities/pool-rents/filter/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Object[]> filterPoolRentByUserId(@PathVariable("userId") Long userId);
    /**
     * Create Pool Rent
     * @param poolRentViewModel receives a PoolRentViewModel
     * @return a PoolRent
     */
    @PostMapping("/activities/pool-rents")
    @ResponseStatus(HttpStatus.CREATED)
    public PoolRent createPoolRent(@RequestBody @Valid PoolRentViewModel poolRentViewModel);
    /**
     * Update Floaties Number
     * @param updateFloatiesNumDto receives a UpdateFloatiesNumDto with updateFloatiesNumDto
     */
    @PatchMapping("/activities/pool-rents/update-floaties")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateFloatiesNum(@RequestBody @Valid UpdateFloatiesNumDto updateFloatiesNumDto);
    /**
     * Update Towel Number
     * @param updateTowelNumDto receives a UpdateTowelNumDto
     */
    @PatchMapping("/activities/pool-rents/update-towels")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTowelNum(@RequestBody @Valid UpdateTowelNumDto updateTowelNumDto);
    /**
     * Cancel Pool Rent
     * @param poolRentId receives a Long with poolRentId
     */
    @DeleteMapping("/activities/pool-rents/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePoolRent(@PathVariable("id") Long poolRentId);
}

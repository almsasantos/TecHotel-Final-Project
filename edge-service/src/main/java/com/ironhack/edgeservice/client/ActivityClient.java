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

@FeignClient(name = "activity-service")
public interface ActivityClient {

    // --- MASSAGES ---
    @GetMapping("/activities/massages")
    @ResponseStatus(HttpStatus.OK)
    public List<Massage> findAllMassages();

    @GetMapping("/activities/massages/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Massage findMassageById(@PathVariable("id") Long massageId);

    @PostMapping("/activities/massages")
    @ResponseStatus(HttpStatus.CREATED)
    public Massage createMassageAppointment(@RequestBody @Valid MassageViewModel massageViewModel);

    @PatchMapping("/activities/massages/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeMassageType(@RequestBody @Valid UpdateMassageTypeDto updateMassageTypeDto);

    @DeleteMapping("/activities/massages/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMassageAppointment(@PathVariable("id") Long massageId);


    // --- ROOM FOOD SERVICES ---
    @GetMapping("/activities/room-food-services")
    @ResponseStatus(HttpStatus.OK)
    public List<RoomFood> findAllRoomFood();

    @GetMapping("/activities/room-food-services/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RoomFood findRoomFoodById(@PathVariable("id") Long roomFoodId);

    @PostMapping("/activities/room-food-services")
    @ResponseStatus(HttpStatus.CREATED)
    public RoomFood createRoomFoodRequest(@RequestBody @Valid RoomFoodViewModel roomFoodViewModel);

    @PatchMapping("/activities/room-food-services/deliver/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deliverRoomFood(@PathVariable("id") Long roomFoodId);

    @PatchMapping("/activities/room-food-services/update-food")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateFoodMenu(@RequestBody @Valid UpdateRoomFoodMenuDto updateRoomFoodMenuDto);

    @PatchMapping("/activities/room-food-services/update-drink")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateDrinkMenu(@RequestBody @Valid UpdateDrinkMenuDto updateDrinkMenuDto);

    @DeleteMapping("/activities/room-food-services/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeRoomFoodRequest(@PathVariable("id") Long roomFoodId);


    // --- POOL RENTS ---
    @GetMapping("/activities/pool-rents")
    @ResponseStatus(HttpStatus.OK)
    public List<PoolRent> findAllPoolRents();

    @GetMapping("/activities/pool-rents/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PoolRent findPoolRentById(@PathVariable("id") Long poolRentId);

    @PostMapping("/activities/pool-rents")
    @ResponseStatus(HttpStatus.CREATED)
    public PoolRent createPoolRent(@RequestBody @Valid PoolRentViewModel poolRentViewModel);

    @PatchMapping("/activities/pool-rents/update-floaties")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateFloatiesNum(@RequestBody @Valid UpdateFloatiesNumDto updateFloatiesNumDto);

    @PatchMapping("/activities/pool-rents/update-towels")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTowelNum(@RequestBody @Valid UpdateTowelNumDto updateTowelNumDto);

    @DeleteMapping("/activities/pool-rents/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePoolRent(@PathVariable("id") Long poolRentId);
}

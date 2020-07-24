package com.ironhack.activityservice.controller.impl;

import com.ironhack.activityservice.controller.interfaces.IPoolRentController;
import com.ironhack.activityservice.dto.UpdateFloatiesNumDto;
import com.ironhack.activityservice.dto.UpdateTowelNumDto;
import com.ironhack.activityservice.model.entities.PoolRent;
import com.ironhack.activityservice.model.viewModel.PoolRentViewModel;
import com.ironhack.activityservice.service.PoolRentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Pool Rent Controller
 */
@RestController
public class PoolRentController implements IPoolRentController {
    /**
     * Autowired of Pool Rent Service
     */
    @Autowired
    private PoolRentService poolRentService;

    /**
     * Find All Pool Rent
     * @return a list of PoolRent
     */
    @GetMapping("/activities/pool-rents")
    @ResponseStatus(HttpStatus.OK)
    public List<PoolRent> findAllPoolRents(){
        return poolRentService.findAll();
    }

    /**
     * Find PoolRent By Id
     * @param poolRentId receives a Long with poolRentId
     * @return a PoolRent
     */
    @GetMapping("/activities/pool-rents/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PoolRent findPoolRentById(@PathVariable("id") Long poolRentId){
        return poolRentService.findPoolRentById(poolRentId);
    }

    /**
     * Filter Pool Rent By User Id
     * @param userId receives a Long with userId
     * @returnq list of objects
     */
    @GetMapping("/activities/pool-rents/filter/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<PoolRent> filterPoolRentByUserId(@PathVariable("userId") Long userId){
        return poolRentService.filterPoolRentByUserId(userId);
    }

    /**
     * Create Pool Rent
     * @param poolRentViewModel receives a PoolRentViewModel
     * @return a PoolRent
     */
    @PostMapping("/activities/pool-rents")
    @ResponseStatus(HttpStatus.CREATED)
    public PoolRent createPoolRent(@RequestBody @Valid PoolRentViewModel poolRentViewModel){
        return poolRentService.createPoolRent(poolRentViewModel);
    }

    /**
     * Update Floaties Number
     * @param updateFloatiesNumDto receives a UpdateFloatiesNumDto with updateFloatiesNumDto
     */
    @PatchMapping("/activities/pool-rents/update-floaties")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateFloatiesNum(@RequestBody @Valid UpdateFloatiesNumDto updateFloatiesNumDto){
        poolRentService.updateFloatiesNum(updateFloatiesNumDto);
    }

    /**
     * Update Towel Number
     * @param updateTowelNumDto receives a UpdateTowelNumDto
     */
    @PatchMapping("/activities/pool-rents/update-towels")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTowelNum(@RequestBody @Valid UpdateTowelNumDto updateTowelNumDto){
        poolRentService.updateTowelNum(updateTowelNumDto);
    }

    /**
     * Cancel Pool Rent
     * @param poolRentId receives a Long with poolRentId
     */
    @DeleteMapping("/activities/pool-rents/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePoolRent(@PathVariable("id") Long poolRentId){
        poolRentService.cancelPoolRent(poolRentId);
    }
}

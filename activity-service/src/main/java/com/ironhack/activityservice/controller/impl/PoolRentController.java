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

@RestController
public class PoolRentController implements IPoolRentController {
    @Autowired
    private PoolRentService poolRentService;

    @GetMapping("/activities/pool-rents")
    @ResponseStatus(HttpStatus.OK)
    public List<PoolRent> findAllPoolRents(){
        return poolRentService.findAll();
    }

    @GetMapping("/activities/pool-rents/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PoolRent findPoolRentById(@PathVariable("id") Long poolRentId){
        return poolRentService.findPoolRentById(poolRentId);
    }

    @PostMapping("/activities/pool-rents")
    @ResponseStatus(HttpStatus.CREATED)
    public PoolRent createPoolRent(@RequestBody @Valid PoolRentViewModel poolRentViewModel){
        return poolRentService.createPoolRent(poolRentViewModel);
    }

    @PatchMapping("/activities/pool-rents/update-floaties")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateFloatiesNum(@RequestBody @Valid UpdateFloatiesNumDto updateFloatiesNumDto){
        poolRentService.updateFloatiesNum(updateFloatiesNumDto);
    }

    @PatchMapping("/activities/pool-rents/update-towels")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTowelNum(@RequestBody @Valid UpdateTowelNumDto updateTowelNumDto){
        poolRentService.updateTowelNum(updateTowelNumDto);
    }

    @DeleteMapping("/activities/pool-rents/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removePoolRent(@PathVariable("id") Long poolRentId){
        poolRentService.cancelPoolRent(poolRentId);
    }
}

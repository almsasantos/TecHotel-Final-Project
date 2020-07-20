package com.ironhack.activityservice.controller.interfaces;

import com.ironhack.activityservice.dto.UpdateFloatiesNumDto;
import com.ironhack.activityservice.dto.UpdateTowelNumDto;
import com.ironhack.activityservice.model.entities.PoolRent;
import com.ironhack.activityservice.model.viewModel.PoolRentViewModel;

import java.util.List;

public interface IPoolRentController {
    public List<PoolRent> findAllPoolRents();

    public PoolRent findPoolRentById(Long poolRentId);

    public PoolRent createPoolRent(PoolRentViewModel poolRentViewModel);

    public void updateFloatiesNum(UpdateFloatiesNumDto updateFloatiesNumDto);

    public void updateTowelNum(UpdateTowelNumDto updateTowelNumDto);

    public void removePoolRent(Long poolRentId);
}

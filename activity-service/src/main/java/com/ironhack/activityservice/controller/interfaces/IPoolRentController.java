package com.ironhack.activityservice.controller.interfaces;

import com.ironhack.activityservice.dto.UpdateFloatiesNumDto;
import com.ironhack.activityservice.dto.UpdateTowelNumDto;
import com.ironhack.activityservice.model.entities.PoolRent;
import com.ironhack.activityservice.model.viewModel.PoolRentViewModel;

import java.util.List;

public interface IPoolRentController {
    /**
     * Find All Pool Rent
     * @return a list of PoolRent
     */
    public List<PoolRent> findAllPoolRents();

    /**
     * Find PoolRent By Id
     * @param poolRentId receives a Long with poolRentId
     * @return a PoolRent
     */
    public PoolRent findPoolRentById(Long poolRentId);

    /**
     * Create Pool Rent
     * @param poolRentViewModel receives a PoolRentViewModel
     * @return a PoolRent
     */
    public PoolRent createPoolRent(PoolRentViewModel poolRentViewModel);

    /**
     * Update Floaties Number
     * @param updateFloatiesNumDto receives a UpdateFloatiesNumDto with updateFloatiesNumDto
     */
    public void updateFloatiesNum(UpdateFloatiesNumDto updateFloatiesNumDto);

    /**
     * Update Towel Number
     * @param updateTowelNumDto receives a UpdateTowelNumDto
     */
    public void updateTowelNum(UpdateTowelNumDto updateTowelNumDto);

    /**
     * Cancel Pool Rent
     * @param poolRentId receives a Long with poolRentId
     */
    public void removePoolRent(Long poolRentId);

    /**
     * Filter Pool Rent By User Id
     * @param userId receives a Long with userId
     * @returnq list of objects
     */
    public List<Object[]> filterPoolRentByUserId(Long userId);
}

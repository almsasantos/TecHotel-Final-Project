package com.ironhack.activityservice.controller.interfaces;

import com.ironhack.activityservice.dto.UpdateMassageTypeDto;
import com.ironhack.activityservice.model.entities.Massage;
import com.ironhack.activityservice.model.viewModel.MassageViewModel;

import java.util.List;

/**
 * Massage Controller Interface
 */
public interface IMassageController {
    /**
     * Find All Massages
     * @return a list of massages
     */
    public List<Massage> findAllMassages();

    /**
     * Find Massage by id
     * @param massageId receives a long with massageId
     * @return a Massage
     */
    public Massage findMassageById(Long massageId);

    /**
     * Create Massage Appointment
     * @param massageViewModel receives a Massage View Model
     * @return a Massage created
     */
    public Massage createMassageAppointment(MassageViewModel massageViewModel);

    /**
     * Change Massage Type
     * @param updateMassageTypeDto receives an UpdateMassageTypeDto
     */
    public void changeMassageType(UpdateMassageTypeDto updateMassageTypeDto);

    /**
     * Cancel Massage Appointment
     * @param massageId receives a long with massageId
     */
    public void deleteMassageAppointment(Long massageId);

    /**
     * Filter Massage By User Id
     * @param userId receives an Long with userId
     * @return a list of object
     */
    public List<Object[]> filterMassageByUserId(Long userId);
}

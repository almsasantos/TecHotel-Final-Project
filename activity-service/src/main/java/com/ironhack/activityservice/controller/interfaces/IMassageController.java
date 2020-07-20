package com.ironhack.activityservice.controller.interfaces;

import com.ironhack.activityservice.dto.UpdateMassageTypeDto;
import com.ironhack.activityservice.model.entities.Massage;
import com.ironhack.activityservice.model.viewModel.MassageViewModel;

import java.util.List;

public interface IMassageController {
    public List<Massage> findAllMassages();

    public Massage findMassageById(Long massageId);

    public Massage createMassageAppointment(MassageViewModel massageViewModel);

    public void changeMassageType(UpdateMassageTypeDto updateMassageTypeDto);

    public void deleteMassageAppointment(Long massageId);
}

package com.ironhack.activityservice.controller.impl;

import com.ironhack.activityservice.controller.interfaces.IMassageController;
import com.ironhack.activityservice.dto.UpdateMassageTypeDto;
import com.ironhack.activityservice.model.entities.Massage;
import com.ironhack.activityservice.model.viewModel.MassageViewModel;
import com.ironhack.activityservice.service.MassageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Massage's Controller
 */
@RestController
public class MassageController implements IMassageController {
    /**
     * Autowired of Massage Service
     */
    @Autowired
    private MassageService massageService;

    /**
     * Find All Massages
     * @return a list of massages
     */
    @GetMapping("/activities/massages")
    @ResponseStatus(HttpStatus.OK)
    public List<Massage> findAllMassages(){
        return massageService.findAll();
    }

    /**
     * Find Massage by id
     * @param massageId receives a long with massageId
     * @return a Massage
     */
    @GetMapping("/activities/massages/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Massage findMassageById(@PathVariable("id") Long massageId){
        return massageService.findMassageById(massageId);
    }

    /**
     * Filter Massage By User Id
     * @param userId receives an Long with userId
     * @return a list of object
     */
    @GetMapping("/activities/massages/filter/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Object[]> filterMassageByUserId(@PathVariable("userId") Long userId){
        return massageService.filterMassageByUserId(userId);
    }

    /**
     * Create Massage Appointment
     * @param massageViewModel receives a Massage View Model
     * @return a Massage created
     */
    @PostMapping("/activities/massages")
    @ResponseStatus(HttpStatus.CREATED)
    public Massage createMassageAppointment(@RequestBody @Valid MassageViewModel massageViewModel){
        return massageService.createMassageAppointment(massageViewModel);
    }

    /**
     * Change Massage Type
     * @param updateMassageTypeDto receives an UpdateMassageTypeDto
     */
    @PatchMapping("/activities/massages")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeMassageType(@RequestBody @Valid UpdateMassageTypeDto updateMassageTypeDto){
        massageService.changeMassageType(updateMassageTypeDto);
    }

    /**
     * Cancel Massage Appointment
     * @param massageId receives a long with massageId
     */
    @DeleteMapping("/activities/massages/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMassageAppointment(@PathVariable("id") Long massageId){
        massageService.cancelMassageAppointment(massageId);
    }
}

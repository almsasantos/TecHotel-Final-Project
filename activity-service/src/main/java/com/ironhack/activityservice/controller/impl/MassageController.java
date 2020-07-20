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

@RestController
public class MassageController implements IMassageController {
    @Autowired
    private MassageService massageService;

    @GetMapping("/activities/massages")
    @ResponseStatus(HttpStatus.OK)
    public List<Massage> findAllMassages(){
        return massageService.findAll();
    }

    @GetMapping("/activities/massages/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Massage findMassageById(@PathVariable("id") Long massageId){
        return massageService.findMassageById(massageId);
    }

    @PostMapping("/activities/massages")
    @ResponseStatus(HttpStatus.CREATED)
    public Massage createMassageAppointment(@RequestBody @Valid MassageViewModel massageViewModel){
        return massageService.createMassageAppointment(massageViewModel);
    }

    @PatchMapping("/activities/massages")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeMassageType(@RequestBody @Valid UpdateMassageTypeDto updateMassageTypeDto){
        massageService.changeMassageType(updateMassageTypeDto);
    }

    @DeleteMapping("/activities/massages/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMassageAppointment(@PathVariable("id") Long massageId){
        massageService.cancelMassageAppointment(massageId);
    }
}

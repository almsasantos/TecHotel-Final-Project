package com.ironhack.bookservice.controller.Impl;

import com.ironhack.bookservice.controller.Interfaces.ISuiteController;
import com.ironhack.bookservice.model.SuiteRoom;
import com.ironhack.bookservice.service.SuiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SuiteController implements ISuiteController {
    @Autowired
    private SuiteService suiteService;

    @GetMapping("/suites")
    @ResponseStatus(HttpStatus.OK)
    public List<SuiteRoom> findAllSuites(){
        return suiteService.findAll();
    }

    @GetMapping("/suites/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SuiteRoom findSuiteById(@PathVariable("id") Integer roomId){
        return suiteService.findById(roomId);
    }

    @PatchMapping("/update-suites/{id}/{availability}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateSuiteAvailability(@PathVariable("id") Integer roomId, @PathVariable("availability") Boolean availability){
        suiteService.updateSuiteAvailability(roomId, availability);
    }

    @PostMapping("/suites")
    @ResponseStatus(HttpStatus.CREATED)
    public SuiteRoom createSuite(@RequestBody @Valid SuiteRoom suite){
        return suiteService.createSuite(suite);
    }
}

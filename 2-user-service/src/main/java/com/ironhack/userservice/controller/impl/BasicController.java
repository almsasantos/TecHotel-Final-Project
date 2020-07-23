package com.ironhack.userservice.controller.impl;

import com.ironhack.userservice.controller.interfaces.IBasicController;
import com.ironhack.userservice.model.entities.Basic;
import com.ironhack.userservice.model.viewModel.BasicAndPremiumViewModel;
import com.ironhack.userservice.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

/**
 * Basic's Controller
 */
@RestController
public class BasicController implements IBasicController {
    /**
     * Autowired of Basic Service
     */
    @Autowired
    private BasicService basicService;

    /**
     * Find All Basic Users
     * @return a list of Basic
     */
    @GetMapping("/users/basics")
    @ResponseStatus(HttpStatus.OK)
    public List<Basic> findAllBasicUser(){
        return basicService.findAll();
    }

    /**
     * Find basic user by id
     * @param basicId receives a Long with Basic's Id
     * @return a Basic user
     */
    @GetMapping("/users/basics/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Basic findBasicUserById(@PathVariable("id") Long basicId){
        return basicService.findById(basicId);
    }

    /**
     * Create new Basic User
     * @param basicAndPremiumViewModel receives a basic view model with information necessary
     * @return a Basic User
     */
    @PostMapping("/users/basics")
    @ResponseStatus(HttpStatus.CREATED)
    public Basic createBasicUser(@RequestBody @Valid BasicAndPremiumViewModel basicAndPremiumViewModel){
        return basicService.createBasicUser(basicAndPremiumViewModel);
    }

    /**
     * Update Basic Room Id
     * @param basicId receives a Long with Basic's Id
     * @param roomId receives an Integer with Room's Id
     */
    @PatchMapping("/users/basics/update-room/{id}/{roomId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBasicRoomId(@PathVariable("id") Long basicId, @PathVariable("roomId") Integer roomId){
        basicService.updateRoomId(basicId, roomId);
    }

    /**
     * Update Basic Balance
     * @param basicId receives a Long with Basic's Id
     * @param updatedBalance receives a BigDecimal
     */
    @PatchMapping("/users/basics/update-balance/{id}/{balance}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBasicBalance(@PathVariable("id") Long basicId, @PathVariable("balance") BigDecimal updatedBalance){
        basicService.updateBasicBalance(basicId, updatedBalance);
    }

    /**
     * Update Basic number of stays
     * @param basicId receives a Long with Basic's Id
     * @param numberOfStays receives an Integer with number of stays
     */
    @PatchMapping("/users/basics/update-stays/{id}/{numberOfStays}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBasicNumberOfStays(@PathVariable("id") Long basicId, @PathVariable("numberOfStays") Integer numberOfStays){
        basicService.updateNumberOfStays(basicId, numberOfStays);
    }

    /**
     * Delete Basic User
     * @param basicId receives a Long with Basic's Id
     */
    @DeleteMapping("/users/basics/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBasicUser(@PathVariable("id") Long basicId){
        basicService.deleteBasicUser(basicId);
    }

    /**
     * Check if Basic User matches name
     * @param basicId receives a Long with Basic's Id
     * @param name receives a String with name
     * @return a Boolean value
     */
    @GetMapping("/users/basics/check-name/{basicId}/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean basicUserIdMatchesName(@PathVariable("basicId") Long basicId, @PathVariable("name") String name){
        return basicService.basicUserIdMatchesName(basicId, name);
    }

    @GetMapping("/users/basics-username/{username}")
    @ResponseStatus(HttpStatus.OK)
    public Long findBasicByUsername(@PathVariable("username") String username){
        return basicService.findBasicByUsername(username);
    }
}

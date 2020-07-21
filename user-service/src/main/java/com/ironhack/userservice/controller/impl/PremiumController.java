package com.ironhack.userservice.controller.impl;

import com.ironhack.userservice.controller.interfaces.IPremiumController;
import com.ironhack.userservice.model.entities.Premium;
import com.ironhack.userservice.model.viewModel.BasicAndPremiumViewModel;
import com.ironhack.userservice.service.PremiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

/**
 * Premium's Controller
 */
@RestController
public class PremiumController implements IPremiumController {
    /**
     * Autowired of Premium Service
     */
    @Autowired
    private PremiumService premiumService;

    /**
     * Find All Premium users
     * @return a list of Premium
     */
    @GetMapping("/users/premiums")
    @ResponseStatus(HttpStatus.OK)
    public List<Premium> findAllPremiumUsers(){
        return premiumService.findAll();
    }

    /**
     * Find Premium User by Id
     * @param premiumId receives a Long with Premium's id
     * @return a Premium
     */
    @GetMapping("/users/premiums/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Premium findPremiumUserById(@PathVariable("id") Long premiumId){
        return premiumService.findById(premiumId);
    }

    /**
     * Create Premium User
     * @param basicAndPremiumViewModel receives a premium view model with information necessary
     * @return a Premium User Created
     */
    @PostMapping("/users/premiums")
    @ResponseStatus(HttpStatus.CREATED)
    public Premium createPremiumUser(@RequestBody @Valid BasicAndPremiumViewModel basicAndPremiumViewModel){
        return premiumService.createPremiumUser(basicAndPremiumViewModel);
    }

    /**
     * Update Premium Room Id
     * @param premiumId receives a Long with Premium's id
     * @param roomId receives an Integer with Room's id
     */
    @PatchMapping("/users/premiums/update-room/{id}/{roomId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePremiumRoomId(@PathVariable("id") Long premiumId, @PathVariable("roomId") Integer roomId){
        premiumService.updateRoomId(premiumId, roomId);
    }

    /**
     * Update Premium Balance
     * @param premiumId receives a Long with Premium's id
     * @param updatedBalance receives a BigDecimal
     */
    @PatchMapping("/users/premiums/update-balance/{id}/{balance}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePremiumBalance(@PathVariable("id") Long premiumId, @PathVariable("balance") BigDecimal updatedBalance){
        premiumService.updatePremiumBalance(premiumId, updatedBalance);
    }

    /**
     * Update Premium Number of Stays
     * @param premiumId receives a Long with Premium's id
     * @param numberOfStays receives an Integer with number of stays
     */
    @PatchMapping("/users/premiums/update-stays/{id}/{numberOfStays}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePremiumNumberOfStays(@PathVariable("id") Long premiumId, @PathVariable("numberOfStays") Integer numberOfStays){
        premiumService.updateNumberOfStays(premiumId, numberOfStays);
    }

    /**
     * Delete Premium User
     * @param premiumId receives a Long with Premium's id
     */
    @DeleteMapping("/users/premiums/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePremiumUser(@PathVariable("id") Long premiumId){
        premiumService.deletePremiumUser(premiumId);
    }

    /**
     * Check if Premium Id matches name
     * @param premiumId receives a Long with Premium's id
     * @param name receives a String with name
     * @return a Boolean Value
     */
    @GetMapping("/users/premiums/check-name/{premiumId}/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean premiumUserIdMatchesName(@PathVariable("premiumId") Long premiumId, @PathVariable("name") String name){
        return premiumService.premiumUserIdMatchesName(premiumId, name);
    }

    @GetMapping("/users/premiums-username/{username}")
    @ResponseStatus(HttpStatus.OK)
    public Long findPremiumByUsername(@PathVariable("username") String username){
        return premiumService.findPremiumByUsername(username);
    }
}

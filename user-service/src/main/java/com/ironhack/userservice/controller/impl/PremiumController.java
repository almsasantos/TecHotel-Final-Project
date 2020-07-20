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

@RestController
public class PremiumController implements IPremiumController {
    @Autowired
    private PremiumService premiumService;

    @GetMapping("/users/premiums")
    @ResponseStatus(HttpStatus.OK)
    public List<Premium> findAllPremiumUsers(){
        return premiumService.findAll();
    }

    @GetMapping("/users/premiums/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Premium findPremiumUserById(@PathVariable("id") Long premiumId){
        return premiumService.findById(premiumId);
    }

    @PostMapping("/users/premiums")
    @ResponseStatus(HttpStatus.CREATED)
    public Premium createPremiumUser(@RequestBody @Valid BasicAndPremiumViewModel basicAndPremiumViewModel){
        return premiumService.createPremiumUser(basicAndPremiumViewModel);
    }

    @PatchMapping("/users/premiums/update-room/{id}/{roomId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePremiumRoomId(@PathVariable("id") Long premiumId, @PathVariable("roomId") Integer roomId){
        premiumService.updateRoomId(premiumId, roomId);
    }

    @PatchMapping("/users/premiums/update-balance/{id}/{balance}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePremiumBalance(@PathVariable("id") Long premiumId, @PathVariable("balance") BigDecimal updatedBalance){
        premiumService.updatePremiumBalance(premiumId, updatedBalance);
    }

    @PatchMapping("/users/premiums/update-stays/{id}/{numberOfStays}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePremiumNumberOfStays(@PathVariable("id") Long premiumId, @PathVariable("numberOfStays") Integer numberOfStays){
        premiumService.updateNumberOfStays(premiumId, numberOfStays);
    }

    @DeleteMapping("/users/premiums/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePremiumUser(@PathVariable("id") Long premiumId){
        premiumService.deletePremiumUser(premiumId);
    }

    @GetMapping("/users/premiums/check-name/{premiumId}/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean premiumUserIdMatchesName(@PathVariable("premiumId") Long premiumId, @PathVariable("name") String name){
        return premiumService.premiumUserIdMatchesName(premiumId, name);
    }
}

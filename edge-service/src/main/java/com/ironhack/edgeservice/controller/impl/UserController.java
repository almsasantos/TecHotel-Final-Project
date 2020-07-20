package com.ironhack.edgeservice.controller.impl;

import com.ironhack.edgeservice.model.entities.users.Basic;
import com.ironhack.edgeservice.model.entities.users.Premium;
import com.ironhack.edgeservice.model.viewModel.BasicAndPremiumViewModel;
import com.ironhack.edgeservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    // --- BASIC USERS ---
    @GetMapping("/users/basics")
    @ResponseStatus(HttpStatus.OK)
    public List<Basic> findAllBasicUser(@RequestHeader(value = "Authorization") String authorizationHeader){
        return userService.findAllBasicUser(authorizationHeader);
    }

    @GetMapping("/users/basics/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Basic findBasicUserById(@PathVariable("id") Long basicId, @RequestHeader(value = "Authorization") String authorizationHeader){
        return userService.findBasicUserById(basicId, authorizationHeader);
    }

    @PostMapping("/users/basics")
    @ResponseStatus(HttpStatus.CREATED)
    public Basic createBasicUser(@RequestBody @Valid BasicAndPremiumViewModel basicAndPremiumViewModel){
        return userService.createBasicUser(basicAndPremiumViewModel);
    }

    @PatchMapping("/users/basics/update-room/{id}/{roomId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBasicRoomId(@PathVariable("id") Long basicId, @PathVariable("roomId") Integer roomId, @RequestHeader(value = "Authorization") String authorizationHeader){
        userService.updateBasicRoomId(basicId, roomId, authorizationHeader);
    }

    @PatchMapping("/users/basics/update-balance/{id}/{balance}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBasicBalance(@PathVariable("id") Long basicId, @PathVariable("balance") BigDecimal updatedBalance, @RequestHeader(value = "Authorization") String authorizationHeader){
        userService.updateBasicBalance(basicId, updatedBalance, authorizationHeader);
    }

    @PatchMapping("/users/basics/update-stays/{id}/{numberOfStays}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBasicNumberOfStays(@PathVariable("id") Long basicId, @PathVariable("numberOfStays") Integer numberOfStays, @RequestHeader(value = "Authorization") String authorizationHeader){
        userService.updateBasicNumberOfStays(basicId, numberOfStays, authorizationHeader);
    }

    @DeleteMapping("/users/basics/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBasicUser(@PathVariable("id") Long basicId, @RequestHeader(value = "Authorization") String authorizationHeader){
        userService.deleteBasicUser(basicId, authorizationHeader);
    }

    // --- PREMIUM USERS ---
    @GetMapping("/users/premiums")
    @ResponseStatus(HttpStatus.OK)
    public List<Premium> findAllPremiumUsers(@RequestHeader(value = "Authorization") String authorizationHeader){
        return userService.findAllPremiumUsers(authorizationHeader);
    }

    @GetMapping("/users/premiums/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Premium findPremiumUserById(@PathVariable("id") Long premiumId, @RequestHeader(value = "Authorization") String authorizationHeader){
        return userService.findPremiumUserById(premiumId, authorizationHeader);
    }

    @PostMapping("/users/premiums")
    @ResponseStatus(HttpStatus.CREATED)
    public Premium createPremiumUser(@RequestBody @Valid BasicAndPremiumViewModel basicAndPremiumViewModel){
        return userService.createPremiumUser(basicAndPremiumViewModel);
    }

    @PatchMapping("/users/premiums/update-room/{id}/{roomId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePremiumRoomId(@PathVariable("id") Long premiumId, @PathVariable("roomId") Integer roomId, @RequestHeader(value = "Authorization") String authorizationHeader){
        userService.updatePremiumRoomId(premiumId, roomId, authorizationHeader);
    }

    @PatchMapping("/users/premiums/update-balance/{id}/{balance}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePremiumBalance(@PathVariable("id") Long premiumId, @PathVariable("balance") BigDecimal updatedBalance, @RequestHeader(value = "Authorization") String authorizationHeader){
        userService.updatePremiumBalance(premiumId, updatedBalance, authorizationHeader);
    }

    @PatchMapping("/users/premiums/update-stays/{id}/{numberOfStays}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePremiumNumberOfStays(@PathVariable("id") Long premiumId, @PathVariable("numberOfStays") Integer numberOfStays, @RequestHeader(value = "Authorization") String authorizationHeader){
        userService.updatePremiumNumberOfStays(premiumId, numberOfStays, authorizationHeader);
    }

    @DeleteMapping("/users/premiums/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePremiumUser(@PathVariable("id") Long premiumId, @RequestHeader(value = "Authorization") String authorizationHeader){
        userService.deletePremiumUser(premiumId, authorizationHeader);
    }
}

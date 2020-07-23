package com.ironhack.edgeservice.controller.interfaces;

import com.ironhack.edgeservice.model.entities.users.Admin;
import com.ironhack.edgeservice.model.entities.users.Basic;
import com.ironhack.edgeservice.model.entities.users.Premium;
import com.ironhack.edgeservice.model.viewModel.BasicAndPremiumViewModel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

/**
 * User Controller Interface
 */
public interface IUserController {
    /**
     * Create new Admin
     * @param admin receives a class Admin
     * @return an Admin created
     */
    public Admin createNewAdmin(@RequestBody @Valid Admin admin);

    // --- BASIC USERS ---
    /**
     * Find All Basic Users
     * @return a list of Basic
     */
    public List<Basic> findAllBasicUser(@RequestHeader(value = "Authorization") String authorizationHeader);
    /**
     * Find basic user by id
     * @param basicId receives a Long with Basic's Id
     * @return a Basic user
     */
    public Basic findBasicUserById(@PathVariable("id") Long basicId, @RequestHeader(value = "Authorization") String authorizationHeader);
    /**
     * Create new Basic User
     * @param basicAndPremiumViewModel receives a basic view model with information necessary
     * @return a Basic User
     */
    public Basic createBasicUser(@RequestBody @Valid BasicAndPremiumViewModel basicAndPremiumViewModel);
    /**
     * Update Basic Room Id
     * @param basicId receives a Long with Basic's Id
     * @param roomId receives an Integer with Room's Id
     */
    public void updateBasicRoomId(@PathVariable("id") Long basicId, @PathVariable("roomId") Integer roomId, @RequestHeader(value = "Authorization") String authorizationHeader);
    /**
     * Update Basic Balance
     * @param basicId receives a Long with Basic's Id
     * @param updatedBalance receives a BigDecimal
     */
    public void updateBasicBalance(@PathVariable("id") Long basicId, @PathVariable("balance") BigDecimal updatedBalance, @RequestHeader(value = "Authorization") String authorizationHeader);
    /**
     * Update Basic number of stays
     * @param basicId receives a Long with Basic's Id
     * @param numberOfStays receives an Integer with number of stays
     */
    public void updateBasicNumberOfStays(@PathVariable("id") Long basicId, @PathVariable("numberOfStays") Integer numberOfStays, @RequestHeader(value = "Authorization") String authorizationHeader);
    /**
     * Delete Basic User
     * @param basicId receives a Long with Basic's Id
     */
    public void deleteBasicUser(@PathVariable("id") Long basicId, @RequestHeader(value = "Authorization") String authorizationHeader);

    // --- PREMIUM USERS ---
    /**
     * Find All Premium users
     * @return a list of Premium
     */
    public List<Premium> findAllPremiumUsers(@RequestHeader(value = "Authorization") String authorizationHeader);
    /**
     * Find Premium User by Id
     * @param premiumId receives a Long with Premium's id
     * @return a Premium
     */
    public Premium findPremiumUserById(@PathVariable("id") Long premiumId, @RequestHeader(value = "Authorization") String authorizationHeader);
    /**
     * Create Premium User
     * @param basicAndPremiumViewModel receives a premium view model with information necessary
     * @return a Premium User Created
     */
    public Premium createPremiumUser(@RequestBody @Valid BasicAndPremiumViewModel basicAndPremiumViewModel);
    /**
     * Update Premium Room Id
     * @param premiumId receives a Long with Premium's id
     * @param roomId receives an Integer with Room's id
     */
    public void updatePremiumRoomId(@PathVariable("id") Long premiumId, @PathVariable("roomId") Integer roomId, @RequestHeader(value = "Authorization") String authorizationHeader);
    /**
     * Update Premium Balance
     * @param premiumId receives a Long with Premium's id
     * @param updatedBalance receives a BigDecimal
     */
    public void updatePremiumBalance(@PathVariable("id") Long premiumId, @PathVariable("balance") BigDecimal updatedBalance, @RequestHeader(value = "Authorization") String authorizationHeader);
    /**
     * Update Premium Number of Stays
     * @param premiumId receives a Long with Premium's id
     * @param numberOfStays receives an Integer with number of stays
     */
    public void updatePremiumNumberOfStays(@PathVariable("id") Long premiumId, @PathVariable("numberOfStays") Integer numberOfStays, @RequestHeader(value = "Authorization") String authorizationHeader);
    /**
     * Delete Premium User
     * @param premiumId receives a Long with Premium's id
     */
    public void deletePremiumUser(@PathVariable("id") Long premiumId, @RequestHeader(value = "Authorization") String authorizationHeader);

    /**
     * Find admin by id
     * @param adminId receives a long with id from Admin
     * @return an Admin
     */
    public Admin findAdminById(@PathVariable("id") Long adminId, @RequestHeader(value = "Authorization") String authorizationHeader);

    /**
     * Find All Admins
     * @return a list of Admin
     */
    public List<Admin> findAllAdmin(@RequestHeader(value = "Authorization") String authorizationHeader);

    public Long findBasicByUsername(@PathVariable("username") String username);

    public Long findPremiumByUsername(@PathVariable("username") String username);
}

package com.ironhack.edgeservice.client;

import com.ironhack.edgeservice.model.entities.users.Admin;
import com.ironhack.edgeservice.model.entities.users.Basic;
import com.ironhack.edgeservice.model.entities.users.Premium;
import com.ironhack.edgeservice.model.viewModel.BasicAndPremiumViewModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@FeignClient(name = "user-service")
public interface UserClient {

    // --- BASIC USERS ---
    /**
     * Find All Basic Users
     * @return a list of Basic
     */
    @GetMapping("/users/basics")
    @ResponseStatus(HttpStatus.OK)
    public List<Basic> findAllBasicUser();
    /**
     * Find basic user by id
     * @param basicId receives a Long with Basic's Id
     * @return a Basic user
     */
    @GetMapping("/users/basics/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Basic findBasicUserById(@PathVariable("id") Long basicId);
    /**
     * Create new Basic User
     * @param basicAndPremiumViewModel receives a basic view model with information necessary
     * @return a Basic User
     */
    @PostMapping("/users/basics")
    @ResponseStatus(HttpStatus.CREATED)
    public Basic createBasicUser(@RequestBody @Valid BasicAndPremiumViewModel basicAndPremiumViewModel);
    /**
     * Update Basic Room Id
     * @param basicId receives a Long with Basic's Id
     * @param roomId receives an Integer with Room's Id
     */
    @PatchMapping("/users/basics/update-room/{id}/{roomId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBasicRoomId(@PathVariable("id") Long basicId, @PathVariable("roomId") Integer roomId);
    /**
     * Update Basic Balance
     * @param basicId receives a Long with Basic's Id
     * @param updatedBalance receives a BigDecimal
     */
    @PatchMapping("/users/basics/update-balance/{id}/{balance}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBasicBalance(@PathVariable("id") Long basicId, @PathVariable("balance") BigDecimal updatedBalance);
    /**
     * Update Basic number of stays
     * @param basicId receives a Long with Basic's Id
     * @param numberOfStays receives an Integer with number of stays
     */
    @PatchMapping("/users/basics/update-stays/{id}/{numberOfStays}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBasicNumberOfStays(@PathVariable("id") Long basicId, @PathVariable("numberOfStays") Integer numberOfStays);
    /**
     * Delete Basic User
     * @param basicId receives a Long with Basic's Id
     */
    @DeleteMapping("/users/basics/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBasicUser(@PathVariable("id") Long basicId);

    /**
     * Check if Basic User matches name
     * @param basicId receives a Long with Basic's Id
     * @param name receives a String with name
     * @return a Boolean value
     */
    @GetMapping("/users/basics/check-name/{basicId}/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean basicUserIdMatchesName(@PathVariable("basicId") Long basicId, @PathVariable("name") String name);

    @GetMapping("/users/basics-username/{username}")
    @ResponseStatus(HttpStatus.OK)
    public Long findBasicByUsername(@PathVariable("username") String username);


    // --- PREMIUM USERS ---
    /**
     * Find All Premium users
     * @return a list of Premium
     */
    @GetMapping("/users/premiums")
    @ResponseStatus(HttpStatus.OK)
    public List<Premium> findAllPremiumUsers();
    /**
     * Find Premium User by Id
     * @param premiumId receives a Long with Premium's id
     * @return a Premium
     */
    @GetMapping("/users/premiums/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Premium findPremiumUserById(@PathVariable("id") Long premiumId);
    /**
     * Create Premium User
     * @param basicAndPremiumViewModel receives a premium view model with information necessary
     * @return a Premium User Created
     */
    @PostMapping("/users/premiums")
    @ResponseStatus(HttpStatus.CREATED)
    public Premium createPremiumUser(@RequestBody @Valid BasicAndPremiumViewModel basicAndPremiumViewModel);
    /**
     * Update Premium Room Id
     * @param premiumId receives a Long with Premium's id
     * @param roomId receives an Integer with Room's id
     */
    @PatchMapping("/users/premiums/update-room/{id}/{roomId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePremiumRoomId(@PathVariable("id") Long premiumId, @PathVariable("roomId") Integer roomId);
    /**
     * Update Premium Balance
     * @param premiumId receives a Long with Premium's id
     * @param updatedBalance receives a BigDecimal
     */
    @PatchMapping("/users/premiums/update-balance/{id}/{balance}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePremiumBalance(@PathVariable("id") Long premiumId, @PathVariable("balance") BigDecimal updatedBalance);
    /**
     * Update Premium Number of Stays
     * @param premiumId receives a Long with Premium's id
     * @param numberOfStays receives an Integer with number of stays
     */
    @PatchMapping("/users/premiums/update-stays/{id}/{numberOfStays}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePremiumNumberOfStays(@PathVariable("id") Long premiumId, @PathVariable("numberOfStays") Integer numberOfStays);
    /**
     * Delete Premium User
     * @param premiumId receives a Long with Premium's id
     */
    @DeleteMapping("/users/premiums/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePremiumUser(@PathVariable("id") Long premiumId);
    /**
     * Check if Premium Id matches name
     * @param premiumId receives a Long with Premium's id
     * @param name receives a String with name
     * @return a Boolean Value
     */
    @GetMapping("/users/premiums/check-name/{premiumId}/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean premiumUserIdMatchesName(@PathVariable("premiumId") Long premiumId, @PathVariable("name") String name);

    @GetMapping("/users/premiums-username/{username}")
    @ResponseStatus(HttpStatus.OK)
    public Long findPremiumByUsername(@PathVariable("username") String username);

    // --- ADMIN USER ---

    /**
     * Find All Admins
     * @return a list of Admin
     */
    @GetMapping("/users/admins")
    @ResponseStatus(HttpStatus.OK)
    public List<Admin> findAllAdmin();

    /**
     * Find admin by id
     * @param adminId receives a long with id from Admin
     * @return an Admin
     */
    @GetMapping("/users/admins/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Admin findAdminById(@PathVariable("id") Long adminId);

    /**
     * Create new Admin
     * @param admin receives a class Admin
     * @return an Admin created
     */
    @PostMapping("/users/admins")
    @ResponseStatus(HttpStatus.CREATED)
    public Admin createNewAdmin(@RequestBody @Valid Admin admin);

    /**
     * Delete an Admin
     * @param adminId receives a long with id from Admin
     */
    @DeleteMapping("/users/admins/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAdmin(@PathVariable("id") Long adminId);

}

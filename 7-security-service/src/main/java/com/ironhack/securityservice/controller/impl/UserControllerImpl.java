package com.ironhack.securityservice.controller.impl;

import com.ironhack.securityservice.controller.interfaces.UserController;
import com.ironhack.securityservice.model.User;
import com.ironhack.securityservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User controller implementation
 */
@RestController
public class UserControllerImpl implements UserController {
    /**
     * User service
     */
    @Autowired
    private UserService userService;

    /**
     * Is User a Basic User
     * @param user User user
     * @return a boolean value
     */
    @GetMapping("/users/is-basic-user")
    @ResponseStatus(HttpStatus.OK)
    public boolean isBasicUser(@AuthenticationPrincipal User user) {
        return userService.isBasicUser(user);
    }

    /**
     * Is User a Premium User
     * @param user User user
     * @return a boolean value
     */
    @GetMapping("/users/is-premium-user")
    @ResponseStatus(HttpStatus.OK)
    public boolean isPremiumUser(@AuthenticationPrincipal User user) {
        return userService.isPremiumUser(user);
    }

    /**
     * Is User an Admin
     * @param user User user
     * @return boolean value
     */
    @GetMapping("/users/is-admin")
    @ResponseStatus(HttpStatus.OK)
    public boolean isAdmin(@AuthenticationPrincipal User user) {
        return userService.isAdmin(user);
    }

    /**
     * Get all Basic Users
     * @return List of Basic Users
     */
    @GetMapping("/users/basic-users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> viewAllBasicUsers() {
        return userService.viewAllBasicUsers();
    }

    /**
     * Get all Premium Users
     * @return List of Premium Users
     */
    @GetMapping("/users/premium-users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> viewAllPremiumUsers() {
        return userService.viewAllPremiumUsers();
    }

    /**
     * Create Basic User
     * @param basic User basic
     * @return User
     */
    @PostMapping("/users/basic-users")
    @ResponseStatus(HttpStatus.CREATED)
    public User createBasicUser(@RequestBody User basic) {
        return userService.createBasicUser(basic);
    }

    /**
     * Create Premium User
     * @param premium User premium
     * @return User
     */
    @PostMapping("/users/premium-users")
    @ResponseStatus(HttpStatus.CREATED)
    public User createPremiumUser(@RequestBody User premium) {
        return userService.createPremiumUser(premium);
    }

    /**
     * Create Admin User
     * @param admin User admin
     * @return User
     */
    @PostMapping("/users/admin-users")
    @ResponseStatus(HttpStatus.CREATED)
    public User createAdminUser(@RequestBody User admin) {
        return userService.createAdminUser(admin);
    }

    @PostMapping("/users/login")
    @ResponseStatus(HttpStatus.OK)
    public boolean login(@AuthenticationPrincipal User user) {
        return userService.login(user);
    }
}
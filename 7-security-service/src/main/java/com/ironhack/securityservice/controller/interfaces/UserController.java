package com.ironhack.securityservice.controller.interfaces;

import com.ironhack.securityservice.model.User;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * User controller interface
 */
public interface UserController {
    /**
     * Is User a Basic User
     * @param user User user
     * @return boolean
     */
    boolean isBasicUser(User user);

    /**
     * Is User a Premium User
     * @param user User user
     * @return boolean
     */
    boolean isPremiumUser(User user);

    /**
     * Is User an Admin
     * @param user User user
     * @return boolean
     */
    boolean isAdmin(User user);

    /**
     * Get all Basic Users
     * @return List of Basic Users
     */
    List<User> viewAllBasicUsers();

    /**
     * Get all Premium Users
     * @return List of Premium Users
     */
    List<User> viewAllPremiumUsers();

    /**
     * Create Basic User
     * @param basic User basic
     * @return User
     */
    public User createBasicUser(@RequestBody User basic);

    /**
     * Create Premium User
     * @param premium User premium
     * @return User
     */
    public User createPremiumUser(@RequestBody User premium);

    /**
     * Create Admin User
     * @param admin User admin
     * @return User
     */
    public User createAdminUser(@RequestBody User admin);

}

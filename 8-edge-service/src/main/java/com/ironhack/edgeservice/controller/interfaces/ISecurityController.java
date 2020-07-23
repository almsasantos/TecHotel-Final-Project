package com.ironhack.edgeservice.controller.interfaces;

import com.ironhack.edgeservice.dto.UserDto;
import com.ironhack.edgeservice.model.entities.security.UserSecurity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

/**
 * Security Controller Interface
 */
public interface ISecurityController {

    /**
     * Is User an Admin
     * @param authorizationHeader Authorization header
     * @return boolean value
     */
    public boolean isAdmin(@RequestHeader(value = "Authorization") String authorizationHeader);

    /**
     * Get all Basic Users
     * @return List of Basic Users
     */
    public List<UserDto> viewAllBasicUsers(@RequestHeader(value = "Authorization") String authorizationHeader);

    /**
     * Get all Premium Users
     * @return List of Premium Users
     */
    public List<UserDto> viewAllPremiumUsers(@RequestHeader(value = "Authorization") String authorizationHeader);

    /**
     * Create Basic User
     * @param basic User basic
     * @return User
     */
    public UserSecurity createBasicUser(@RequestBody UserSecurity basic);
    /**
     * Create Premium User
     * @param premium User basic
     * @return User
     */
    public UserSecurity createPremiumUser(@RequestBody UserSecurity premium);

    /**
     * Create Admin User
     * @param admin User admini
     * @return User
     */
    public UserSecurity createAdminUser(@RequestBody UserSecurity admin);

    public boolean login(@RequestHeader(value = "Authorization") String authorizationHeader);
}

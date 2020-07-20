package com.ironhack.edgeservice.client;

import com.ironhack.edgeservice.dto.UserDto;
import com.ironhack.edgeservice.model.entities.security.UserSecurity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Feign Client Interface for Security-Service
 */
@FeignClient(name = "security-service")
public interface SecurityClient {
    /**
     * Is User a Basic User
     * @param authorizationHeader Authorization header
     * @return a boolean value
     */
    @GetMapping("/users/is-basic-user")
    @ResponseStatus(HttpStatus.OK)
    public boolean isBasicUser(@RequestHeader(value = "Authorization") String authorizationHeader);

    /**
     * Is User a Premium User
     * @param authorizationHeader Authorization header
     * @return a boolean value
     */
    @GetMapping("/users/is-premium-user")
    @ResponseStatus(HttpStatus.OK)
    public boolean isPremiumUser(@RequestHeader(value = "Authorization") String authorizationHeader);

    /**
     * Is User an Admin
     * @param authorizationHeader Authorization header
     * @return boolean value
     */
    @GetMapping("/users/is-admin")
    @ResponseStatus(HttpStatus.OK)
    public boolean isAdmin(@RequestHeader(value = "Authorization") String authorizationHeader);

    /**
     * Get all Basic Users
     * @return List of Basic Users
     */
    @GetMapping("/users/basic-users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> viewAllBasicUsers();

    /**
     * Get all Premium Users
     * @return List of Premium Users
     */
    @GetMapping("/users/premium-users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> viewAllPremiumUsers();

    /**
     * Create Basic User
     * @param basic User basic
     * @return User
     */
    @PostMapping("/users/basic-users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserSecurity createBasicUser(@RequestBody UserSecurity basic);
    /**
     * Create Premium User
     * @param premium User basic
     * @return User
     */
    @PostMapping("/users/premium-users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserSecurity createPremiumUser(@RequestBody UserSecurity premium);
}

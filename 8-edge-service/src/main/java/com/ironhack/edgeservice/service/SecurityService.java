package com.ironhack.edgeservice.service;

import com.ironhack.edgeservice.client.SecurityClient;
import com.ironhack.edgeservice.dto.UserDto;
import com.ironhack.edgeservice.exceptions.SecurityMicroserviceFail;
import com.ironhack.edgeservice.model.entities.security.UserSecurity;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Service of Security
 */
@Service
public class SecurityService {

    /**
     * Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(SecurityService.class);

    /**
     * Security Client
     */
    @Autowired
    private SecurityClient securityClient;

    public boolean login(String authorizationHeader) {
        try { isUser(authorizationHeader);
            return true; }
        catch (Exception e) { return false; } }

    public boolean admin(String authorizationHeader) {
        try { isAdmin(authorizationHeader);
            return true; }
        catch (Exception e) {
            return false; } }

    /**
     * Is User
     * @param authorizationHeader Authorization header
     */
    public void isUser(String authorizationHeader) {
        try {
            if (!securityClient.isBasicUser(authorizationHeader) && !securityClient.isAdmin(authorizationHeader) && !securityClient.isPremiumUser(authorizationHeader))
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            LOGGER.warn("[WARN] - Hystrix send failure for Security Microservice");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED); } }

    /**
     * Is Admin
     * @param authorizationHeader Authorization header
     */
    private void isAdmin(String authorizationHeader) {
        try { if (!securityClient.isAdmin(authorizationHeader)) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        } catch (Exception e) { LOGGER.warn("[WARN] - Hystrix send failure for Security Microservice");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED); } }

    /**
     * Get all Basic Users
     * @param authorizationHeader Authorization header
     * @return List of Basic Users
     */
    @HystrixCommand(fallbackMethod = "SecurityMicroserviceFail", ignoreExceptions = ResponseStatusException.class)
    public List<UserDto> viewAllBasicUsers(String authorizationHeader) {
        isUser(authorizationHeader);
        return securityClient.viewAllBasicUsers(); }

    /**
     * Get all Premium Users
     * @param authorizationHeader Authorization header
     * @return List of Premium Users
     */
    @HystrixCommand(fallbackMethod = "SecurityMicroserviceFail", ignoreExceptions = ResponseStatusException.class)
    public List<UserDto> viewAllPremiumUsers(String authorizationHeader) {
        isAdmin(authorizationHeader);
        return securityClient.viewAllPremiumUsers(); }

    /**
     * Create Basic User
     * @param basic User basic
     * @return User
     */
    @HystrixCommand(fallbackMethod = "createUserFail")
    public UserSecurity createBasicUser(@RequestBody UserSecurity basic){
        return securityClient.createBasicUser(basic);
    }

    /**
     * Create Premium User
     * @param premium User premium
     * @return User
     */
    @HystrixCommand(fallbackMethod = "createUserFail")
    public UserSecurity createPremiumUser(@RequestBody UserSecurity premium){
        return securityClient.createPremiumUser(premium);
    }

    /**
     * Create Admin User
     * @param admin User admin
     * @return User
     */
    @HystrixCommand(fallbackMethod = "createUserFail")
    public UserSecurity createAdminUser(@RequestBody UserSecurity admin){
        return securityClient.createAdminUser(admin);
    }

    /**
     * Hystrix Fallback method when User Microservice fails
     * @param user User to be created
     * @param authorizationHeader Authorization header
     * @return User created
     */
    public UserDto SecurityMicroserviceFail(UserDto user, String authorizationHeader) {
        LOGGER.warn("[WARN] - Hystrix send failure for Security Microservice");
        throw new SecurityMicroserviceFail("Failure in Security Microservice"); }

    /**
     * Hystrix Fallback method when User Microservice fails
     * @param authorizationHeader Authorization header
     * @return List of Users
     */
    public List<UserDto> SecurityMicroserviceFail(String authorizationHeader) {
        LOGGER.warn("[WARN] - Hystrix send failure for Security Microservice");
        throw new SecurityMicroserviceFail("Failure in Security Microservice"); }

    public UserSecurity createUserFail(@RequestBody UserSecurity user){
        LOGGER.warn("[WARN] - Hystrix send failure for Security Microservice, It wasn't possible to crea a new user"); return null;
    }

}

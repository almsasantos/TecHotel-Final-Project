package com.ironhack.edgeservice.service;

import com.ironhack.edgeservice.client.UserClient;
import com.ironhack.edgeservice.model.entities.security.UserSecurity;
import com.ironhack.edgeservice.model.entities.users.Admin;
import com.ironhack.edgeservice.model.entities.users.Basic;
import com.ironhack.edgeservice.model.entities.users.Premium;
import com.ironhack.edgeservice.model.enums.Role;
import com.ironhack.edgeservice.model.viewModel.BasicAndPremiumViewModel;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

/**
 * User Service
 */
@Service
public class UserService {
    /**
     * Autowired of UserClient
     */
    @Autowired
    private UserClient userClient;

    /**
     * Autowired of SecurityService
     */
    @Autowired
    private SecurityService securityService;

    /**
     * Final variable of Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    /**
     * Create new Admin
     * @param admin receives a class Admin
     * @return an Admin created
     */
    @HystrixCommand(fallbackMethod = "createNewAdminFail", ignoreExceptions = ResponseStatusException.class)
    public Admin createNewAdmin(Admin admin){
        LOGGER.info("Create new Admin");
        Admin newAdmin = userClient.createNewAdmin(admin);
        UserSecurity userSecurity = new UserSecurity();
        userSecurity.setUsername(newAdmin.getUsername());
        userSecurity.setPassword(newAdmin.getPassword());
        userSecurity.setRol(Role.ADMIN);
        securityService.createAdminUser(userSecurity);
        return newAdmin;
    }

    // --- BASIC USERS ---
    /**
     * Find All Basic Users
     * @return a list of Basic
     */
    @HystrixCommand(fallbackMethod = "findAllBasicUserFail", ignoreExceptions = ResponseStatusException.class)
    public List<Basic> findAllBasicUser(String authorizationHeader){
        LOGGER.info("Find All Basic Users");
        securityService.isUser(authorizationHeader);
        return userClient.findAllBasicUser();
    }

    /**
     * Find basic user by id
     * @param basicId receives a Long with Basic's Id
     * @return a Basic user
     */
    @HystrixCommand(fallbackMethod = "findBasicUserByIdFail", ignoreExceptions = ResponseStatusException.class)
    public Basic findBasicUserById(Long basicId, String authorizationHeader){
        LOGGER.info("Find Basic User with id " + basicId);
        securityService.isUser(authorizationHeader);
        return userClient.findBasicUserById(basicId);
    }

    /**
     * Create new Basic User
     * @param basicAndPremiumViewModel receives a basic view model with information necessary
     * @return a Basic User
     */
    @HystrixCommand(fallbackMethod = "createBasicUserFail", ignoreExceptions = ResponseStatusException.class)
    public Basic createBasicUser(BasicAndPremiumViewModel basicAndPremiumViewModel){
        LOGGER.info("Create a new Basic User");
        Basic basic = userClient.createBasicUser(basicAndPremiumViewModel);
        UserSecurity userSecurity = new UserSecurity();
        userSecurity.setUsername(basic.getUsername());
        userSecurity.setPassword(basic.getPassword());
        userSecurity.setRol(Role.BASIC);
        securityService.createBasicUser(userSecurity);
        return basic;
    }

    /**
     * Update Basic Room Id
     * @param basicId receives a Long with Basic's Id
     * @param roomId receives an Integer with Room's Id
     */
    @HystrixCommand(fallbackMethod = "updateRoomIdFail", ignoreExceptions = ResponseStatusException.class)
    public void updateBasicRoomId(Long basicId, Integer roomId, String authorizationHeader){
        LOGGER.info("Update Basic User's " + basicId + " with Room id " + roomId);
        securityService.isUser(authorizationHeader);
        userClient.updateBasicRoomId(basicId, roomId);
    }

    /**
     * Update Basic Balance
     * @param basicId receives a Long with Basic's Id
     * @param updatedBalance receives a BigDecimal
     */
    @HystrixCommand(fallbackMethod = "updateBalanceFail", ignoreExceptions = ResponseStatusException.class)
    public void updateBasicBalance(Long basicId, BigDecimal updatedBalance, String authorizationHeader){
        LOGGER.info("Update Basic User's " + basicId + " balance to " + updatedBalance);
        securityService.isUser(authorizationHeader);
        userClient.updateBasicBalance(basicId, updatedBalance);
    }

    /**
     * Update Basic number of stays
     * @param basicId receives a Long with Basic's Id
     * @param numberOfStays receives an Integer with number of stays
     */
    @HystrixCommand(fallbackMethod = "updateNumberOfStaysFail", ignoreExceptions = ResponseStatusException.class)
    public void updateBasicNumberOfStays(Long basicId, Integer numberOfStays, String authorizationHeader){
        LOGGER.info("Update Basic User's " + basicId + " number of stays to " + numberOfStays);
        securityService.isUser(authorizationHeader);
        userClient.updateBasicNumberOfStays(basicId, numberOfStays);
    }

    /**
     * Delete Basic User
     * @param basicId receives a Long with Basic's Id
     */
    @HystrixCommand(fallbackMethod = "deleteUserFail", ignoreExceptions = ResponseStatusException.class)
    public void deleteBasicUser(Long basicId, String authorizationHeader){
        LOGGER.info("Delete Basic User with id " + basicId);
        securityService.isUser(authorizationHeader);
        userClient.deleteBasicUser(basicId);
    }

    // --- PREMIUM USERS ---
    /**
     * Find All Premium users
     * @return a list of Premium
     */
    @HystrixCommand(fallbackMethod = "findAllPremiumUsersFail", ignoreExceptions = ResponseStatusException.class)
    public List<Premium> findAllPremiumUsers(String authorizationHeader){
        LOGGER.info("Find All Premium Users");
        securityService.isUser(authorizationHeader);
        return userClient.findAllPremiumUsers();
    }

    /**
     * Find Premium User by Id
     * @param premiumId receives a Long with Premium's id
     * @return a Premium
     */
    @HystrixCommand(fallbackMethod = "findPremiumUserByIdFail", ignoreExceptions = ResponseStatusException.class)
    public Premium findPremiumUserById(Long premiumId, String authorizationHeader){
        LOGGER.info("Find Premium User with id " + premiumId);
        securityService.isUser(authorizationHeader);
        return userClient.findPremiumUserById(premiumId);
    }

    /**
     * Create Premium User
     * @param basicAndPremiumViewModel receives a premium view model with information necessary
     * @return a Premium User Created
     */
    @HystrixCommand(fallbackMethod = "createPremiumUserFail", ignoreExceptions = ResponseStatusException.class)
    public Premium createPremiumUser(BasicAndPremiumViewModel basicAndPremiumViewModel){
        LOGGER.info("Create a new Premium User");
        Premium premium = userClient.createPremiumUser(basicAndPremiumViewModel);
        UserSecurity userSecurity = new UserSecurity();
        userSecurity.setUsername(premium.getUsername());
        userSecurity.setPassword(premium.getPassword());
        userSecurity.setRol(Role.PREMIUM);
        securityService.createBasicUser(userSecurity);
        return premium;
    }

    /**
     * Update Premium Room Id
     * @param premiumId receives a Long with Premium's id
     * @param roomId receives an Integer with Room's id
     */
    @HystrixCommand(fallbackMethod = "updateRoomIdFail", ignoreExceptions = ResponseStatusException.class)
    public void updatePremiumRoomId(Long premiumId, Integer roomId, String authorizationHeader){
        LOGGER.info("Update Premium User's " + premiumId + " with Room id " + roomId);
        securityService.isUser(authorizationHeader);
        userClient.updatePremiumRoomId(premiumId, roomId); }

    /**
     * Update Premium Balance
     * @param premiumId receives a Long with Premium's id
     * @param updatedBalance receives a BigDecimal
     */
    @HystrixCommand(fallbackMethod = "updateBalanceFail", ignoreExceptions = ResponseStatusException.class)
    public void updatePremiumBalance(Long premiumId, BigDecimal updatedBalance, String authorizationHeader){
        LOGGER.info("Update Premium User's " + premiumId + " balance to " + updatedBalance);
        securityService.isUser(authorizationHeader);
        userClient.updatePremiumBalance(premiumId, updatedBalance); }

    /**
     * Update Premium Number of Stays
     * @param premiumId receives a Long with Premium's id
     * @param numberOfStays receives an Integer with number of stays
     */
    @HystrixCommand(fallbackMethod = "updateNumberOfStaysFail", ignoreExceptions = ResponseStatusException.class)
    public void updatePremiumNumberOfStays(Long premiumId, Integer numberOfStays, String authorizationHeader){
        LOGGER.info("Update Premium User's " + premiumId + " number of stays to " + numberOfStays);
        securityService.isUser(authorizationHeader);
        userClient.updateBasicNumberOfStays(premiumId, numberOfStays); }

    /**
     * Delete Premium User
     * @param premiumId receives a Long with Premium's id
     */
    @HystrixCommand(fallbackMethod = "deleteUserFail", ignoreExceptions = ResponseStatusException.class)
    public void deletePremiumUser(Long premiumId, String authorizationHeader){
        LOGGER.info("Delete Premium User with id " + premiumId);
        securityService.isUser(authorizationHeader);
        userClient.deletePremiumUser(premiumId); }

    @HystrixCommand(fallbackMethod = "findByUsernameFail", ignoreExceptions = ResponseStatusException.class)
    public Long findPremiumByUsername(String username){
        LOGGER.info("Find Premium with username " + username);
        return userClient.findPremiumByUsername(username); }

    @HystrixCommand(fallbackMethod = "findByUsernameFail", ignoreExceptions = ResponseStatusException.class)
    public Long findBasicByUsername(String username){
        LOGGER.info("Find Basic with username " + username);
        return userClient.findBasicByUsername(username); }

    /**
     * Find All Admins
     * @return a list of Admin
     */
    @HystrixCommand(fallbackMethod = "findAllAdminFail", ignoreExceptions = ResponseStatusException.class)
    public List<Admin> findAllAdmin(String authorizationHeader){
        LOGGER.info("Find All Admins ");
        securityService.isUser(authorizationHeader);
        return userClient.findAllAdmin(); }

    /**
     * Find admin by id
     * @param adminId receives a long with id from Admin
     * @return an Admin
     */
    @HystrixCommand(fallbackMethod = "findAdminByIdFail", ignoreExceptions = ResponseStatusException.class)
    public Admin findAdminById(Long adminId, String authorizationHeader){
        LOGGER.info("Find Admin with id " + adminId);
        securityService.isUser(authorizationHeader);
        return userClient.findAdminById(adminId); }

    // --- FallBack Methods ---
    public List<Basic> findAllBasicUserFail(String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to find all Basic Users");
        return null; }

    public Basic findBasicUserByIdFail(Long basicId, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to find Basic User with id " + basicId);
        return null; }

    public Basic createBasicUserFail(BasicAndPremiumViewModel basicAndPremiumViewModel){
        LOGGER.warn("[WARN] It wasn't possible to create new Basic User");
        return null; }

    public void updateRoomIdFail(Long userId, Integer roomId, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to update User's " + userId + " with Room id " + roomId); }

    public void updateBasicBalanceFail(Long userId, BigDecimal updatedBalance, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to update User's " + userId + " balance to " + updatedBalance); }

    public void updateNumberOfStaysFail(Long userId, Integer numberOfStays, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to update User's " + userId + " number of stays to " + numberOfStays); }

    public void deleteUserFail(Long userId, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to delete User with id " + userId); }

    public List<Premium> findAllPremiumUsersFail(String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to find all Premium Users");
        return null; }

    public Premium findPremiumUserByIdFail(Long premiumId, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to find Premium User with id " + premiumId);
        return null; }

    public Premium createPremiumUserFail(BasicAndPremiumViewModel basicAndPremiumViewModel){
        LOGGER.warn("[WARN] It wasn't possible to create new Premium User");
        return null; }

    public Admin createNewAdminFail(Admin admin){
        LOGGER.warn("[WARN] It wasn't possible to create new Admin User");
        return null; }

    public List<Admin> findAllAdminFail(String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to find All Admin Users");
        return null; }

    public Admin findAdminByIdFail(Long adminId, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to find Admin by id");
        return null; }

    public Long findByUsernameFail(String username){
        LOGGER.warn("[WARN] It wasn't possible to username");
        return null; }

}

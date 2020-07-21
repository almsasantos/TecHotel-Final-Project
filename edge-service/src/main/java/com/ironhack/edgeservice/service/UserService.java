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

@Service
public class UserService {
    @Autowired
    private UserClient userClient;

    @Autowired
    private SecurityService securityService;

    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

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
    @HystrixCommand(fallbackMethod = "findAllBasicUserFail", ignoreExceptions = ResponseStatusException.class)
    public List<Basic> findAllBasicUser(String authorizationHeader){
        LOGGER.info("Find All Basic Users");
        securityService.isUser(authorizationHeader);
        return userClient.findAllBasicUser();
    }

    @HystrixCommand(fallbackMethod = "findBasicUserByIdFail", ignoreExceptions = ResponseStatusException.class)
    public Basic findBasicUserById(Long basicId, String authorizationHeader){
        LOGGER.info("Find Basic User with id " + basicId);
        securityService.isUser(authorizationHeader);
        return userClient.findBasicUserById(basicId);
    }

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

    @HystrixCommand(fallbackMethod = "updateRoomIdFail", ignoreExceptions = ResponseStatusException.class)
    public void updateBasicRoomId(Long basicId, Integer roomId, String authorizationHeader){
        LOGGER.info("Update Basic User's " + basicId + " with Room id " + roomId);
        securityService.isUser(authorizationHeader);
        userClient.updateBasicRoomId(basicId, roomId);
    }

    @HystrixCommand(fallbackMethod = "updateBalanceFail", ignoreExceptions = ResponseStatusException.class)
    public void updateBasicBalance(Long basicId, BigDecimal updatedBalance, String authorizationHeader){
        LOGGER.info("Update Basic User's " + basicId + " balance to " + updatedBalance);
        securityService.isUser(authorizationHeader);
        userClient.updateBasicBalance(basicId, updatedBalance);
    }

    @HystrixCommand(fallbackMethod = "updateNumberOfStaysFail", ignoreExceptions = ResponseStatusException.class)
    public void updateBasicNumberOfStays(Long basicId, Integer numberOfStays, String authorizationHeader){
        LOGGER.info("Update Basic User's " + basicId + " number of stays to " + numberOfStays);
        securityService.isUser(authorizationHeader);
        userClient.updateBasicNumberOfStays(basicId, numberOfStays);
    }

    @HystrixCommand(fallbackMethod = "deleteUserFail", ignoreExceptions = ResponseStatusException.class)
    public void deleteBasicUser(Long basicId, String authorizationHeader){
        LOGGER.info("Delete Basic User with id " + basicId);
        securityService.isUser(authorizationHeader);
        userClient.deleteBasicUser(basicId);
    }

    // --- PREMIUM USERS ---
    @HystrixCommand(fallbackMethod = "findAllPremiumUsersFail", ignoreExceptions = ResponseStatusException.class)
    public List<Premium> findAllPremiumUsers(String authorizationHeader){
        LOGGER.info("Find All Premium Users");
        securityService.isUser(authorizationHeader);
        return userClient.findAllPremiumUsers();
    }

    @HystrixCommand(fallbackMethod = "findPremiumUserByIdFail", ignoreExceptions = ResponseStatusException.class)
    public Premium findPremiumUserById(Long premiumId, String authorizationHeader){
        LOGGER.info("Find Premium User with id " + premiumId);
        securityService.isUser(authorizationHeader);
        return userClient.findPremiumUserById(premiumId);
    }

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

    @HystrixCommand(fallbackMethod = "updateRoomIdFail", ignoreExceptions = ResponseStatusException.class)
    public void updatePremiumRoomId(Long premiumId, Integer roomId, String authorizationHeader){
        LOGGER.info("Update Premium User's " + premiumId + " with Room id " + roomId);
        securityService.isUser(authorizationHeader);
        userClient.updatePremiumRoomId(premiumId, roomId);
    }

    @HystrixCommand(fallbackMethod = "updateBalanceFail", ignoreExceptions = ResponseStatusException.class)
    public void updatePremiumBalance(Long premiumId, BigDecimal updatedBalance, String authorizationHeader){
        LOGGER.info("Update Premium User's " + premiumId + " balance to " + updatedBalance);
        securityService.isUser(authorizationHeader);
        userClient.updatePremiumBalance(premiumId, updatedBalance);
    }

    @HystrixCommand(fallbackMethod = "updateNumberOfStaysFail", ignoreExceptions = ResponseStatusException.class)
    public void updatePremiumNumberOfStays(Long premiumId, Integer numberOfStays, String authorizationHeader){
        LOGGER.info("Update Premium User's " + premiumId + " number of stays to " + numberOfStays);
        securityService.isUser(authorizationHeader);
        userClient.updateBasicNumberOfStays(premiumId, numberOfStays);
    }

    @HystrixCommand(fallbackMethod = "deleteUserFail", ignoreExceptions = ResponseStatusException.class)
    public void deletePremiumUser(Long premiumId, String authorizationHeader){
        LOGGER.info("Delete Premium User with id " + premiumId);
        securityService.isUser(authorizationHeader);
        userClient.deletePremiumUser(premiumId);
    }

    @HystrixCommand(fallbackMethod = "findByUsernameFail", ignoreExceptions = ResponseStatusException.class)
    public Long findPremiumByUsername(String username, String authorizationHeader){
        LOGGER.info("Find Premium with username " + username);
        securityService.isUser(authorizationHeader);
        return userClient.findPremiumByUsername(username);
    }

    @HystrixCommand(fallbackMethod = "findByUsernameFail", ignoreExceptions = ResponseStatusException.class)
    public Long findBasicByUsername(String username, String authorizationHeader){
        LOGGER.info("Find Basic with username " + username);
        securityService.isUser(authorizationHeader);
        return userClient.findBasicByUsername(username);
    }

    /**
     * Find All Admins
     * @return a list of Admin
     */
    @HystrixCommand(fallbackMethod = "findAllAdminFail", ignoreExceptions = ResponseStatusException.class)
    public List<Admin> findAllAdmin(String authorizationHeader){
        LOGGER.info("Find All Admins ");
        securityService.isUser(authorizationHeader);
        return userClient.findAllAdmin();
    }

    /**
     * Find admin by id
     * @param adminId receives a long with id from Admin
     * @return an Admin
     */
    @HystrixCommand(fallbackMethod = "findAdminByIdFail", ignoreExceptions = ResponseStatusException.class)
    public Admin findAdminById(Long adminId, String authorizationHeader){
        LOGGER.info("Find Admin with id " + adminId);
        securityService.isUser(authorizationHeader);
        return userClient.findAdminById(adminId);
    }

    // --- FallBack Methods ---
    public List<Basic> findAllBasicUserFail(String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to find all Basic Users");
        return null;
    }

    public Basic findBasicUserByIdFail(Long basicId, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to find Basic User with id " + basicId);
        return null;
    }

    public Basic createBasicUserFail(BasicAndPremiumViewModel basicAndPremiumViewModel){
        LOGGER.warn("[WARN] It wasn't possible to create new Basic User");
        return null;
    }

    public void updateRoomIdFail(Long userId, Integer roomId, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to update User's " + userId + " with Room id " + roomId);
    }

    public void updateBasicBalanceFail(Long userId, BigDecimal updatedBalance, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to update User's " + userId + " balance to " + updatedBalance);
    }

    public void updateNumberOfStaysFail(Long userId, Integer numberOfStays, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to update User's " + userId + " number of stays to " + numberOfStays);
    }

    public void deleteUserFail(Long userId, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to delete User with id " + userId);
    }

    public List<Premium> findAllPremiumUsersFail(String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to find all Premium Users");
        return null;
    }

    public Premium findPremiumUserByIdFail(Long premiumId, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to find Premium User with id " + premiumId);
        return null;
    }

    public Premium createPremiumUserFail(BasicAndPremiumViewModel basicAndPremiumViewModel){
        LOGGER.warn("[WARN] It wasn't possible to create new Premium User");
        return null;
    }

    public Admin createNewAdminFail(Admin admin){
        LOGGER.warn("[WARN] It wasn't possible to create new Admin User");
        return null;
    }

    public List<Admin> findAllAdminFail(String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to find All Admin Users");
        return null;
    }

    public Admin findAdminByIdFail(Long adminId, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to find Admin by id");
        return null;
    }

    public Long findByUsernameFail(String username, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to username");
        return null;
    }

}

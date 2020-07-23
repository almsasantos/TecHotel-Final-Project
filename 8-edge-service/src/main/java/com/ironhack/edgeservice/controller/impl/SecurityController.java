package com.ironhack.edgeservice.controller.impl;

import com.ironhack.edgeservice.controller.interfaces.ISecurityController;
import com.ironhack.edgeservice.dto.UserDto;
import com.ironhack.edgeservice.model.entities.security.UserSecurity;
import com.ironhack.edgeservice.service.SecurityService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Security Controller")
@RestController
@RequestMapping("/")
public class SecurityController implements ISecurityController {

    @Autowired
    private SecurityService securityService;

    /**
     * Is User an Admin
     * @param authorizationHeader Authorization header
     * @return boolean value
     */
    @GetMapping("/users/is-admin")
    @ResponseStatus(HttpStatus.OK)
    public boolean isAdmin(@RequestHeader(value = "Authorization") String authorizationHeader){
        return securityService.admin(authorizationHeader); }

    /**
     * Get all Basic Users
     * @return List of Basic Users
     */
    @GetMapping("/users/basic-users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> viewAllBasicUsers(@RequestHeader(value = "Authorization") String authorizationHeader){
        return securityService.viewAllBasicUsers(authorizationHeader); }

    /**
     * Get all Premium Users
     * @return List of Premium Users
     */
    @GetMapping("/users/premium-users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> viewAllPremiumUsers(@RequestHeader(value = "Authorization") String authorizationHeader){
        return securityService.viewAllPremiumUsers(authorizationHeader); }

    /**
     * Create Basic User
     * @param basic User basic
     * @return User
     */
    @PostMapping("/users/basic-users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserSecurity createBasicUser(@RequestBody UserSecurity basic){
        return securityService.createBasicUser(basic);
    }
    /**
     * Create Premium User
     * @param premium User basic
     * @return User
     */
    @PostMapping("/users/premium-users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserSecurity createPremiumUser(@RequestBody UserSecurity premium){
        return securityService.createPremiumUser(premium);
    }

    /**
     * Create Admin User
     * @param admin User admini
     * @return User
     */
    @PostMapping("/users/admin-users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserSecurity createAdminUser(@RequestBody UserSecurity admin){
        return securityService.createAdminUser(admin);
    }

    @GetMapping("/users/login")
    @ResponseStatus(HttpStatus.OK)
    public boolean login(@RequestHeader(value = "Authorization") String authorizationHeader){
        return securityService.login(authorizationHeader); }
}

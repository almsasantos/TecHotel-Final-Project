package com.ironhack.userservice.controller.impl;

import com.ironhack.userservice.controller.interfaces.IAdminController;
import com.ironhack.userservice.model.entities.Admin;
import com.ironhack.userservice.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AdminController implements IAdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/users/admins")
    @ResponseStatus(HttpStatus.OK)
    public List<Admin> findAllAdmin(){
        return adminService.findAll();
    }

    @GetMapping("/users/admins/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Admin findAdminById(@PathVariable("id") Long adminId){
        return adminService.findById(adminId);
    }

    @PostMapping("/users/admins")
    @ResponseStatus(HttpStatus.CREATED)
    public Admin createNewAdmin(@RequestBody @Valid Admin admin){
        return adminService.createNewAdmin(admin);
    }

    @DeleteMapping("/users/admins/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAdmin(@PathVariable("id") Long adminId){
        adminService.deleteAdmin(adminId);
    }
}

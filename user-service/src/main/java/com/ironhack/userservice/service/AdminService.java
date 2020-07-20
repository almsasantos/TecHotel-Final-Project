package com.ironhack.userservice.service;

import com.ironhack.userservice.exception.DataNotFoundException;
import com.ironhack.userservice.exception.UsernameExistsException;
import com.ironhack.userservice.model.entities.Admin;
import com.ironhack.userservice.repository.AdminRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    private static final Logger LOGGER = LogManager.getLogger(AdminService.class);

    public List<Admin> findAll(){
        LOGGER.info("Find all Admins");
        return adminRepository.findAll();
    }

    public Admin findById(Long adminId){
        LOGGER.info("Find Admin with id " + adminId);
        return adminRepository.findById(adminId).orElseThrow(() -> new DataNotFoundException("Admin id not found"));
    }

    public Admin createNewAdmin(Admin admin){
        LOGGER.info("Create new Admin");
        LOGGER.info("Check if username already exists");
        String username = admin.getUsername();
        restrictUsername(username);
        return adminRepository.save(admin);
    }

    public void deleteAdmin(Long adminId){
        LOGGER.info("Delete Admin with id " + adminId);
        Admin admin = findById(adminId);
        adminRepository.delete(admin);
    }

    public void restrictUsername(String username) throws UsernameExistsException {
        LOGGER.info("Make sure username " + username + " is available to new admin" );
        List<Admin> admins =  adminRepository.findAll().stream().filter(admin -> admin.getUsername().equals(username)).collect(Collectors.toList());
        if(admins.size()>0)
            throw new UsernameExistsException("This username already exists");

    }
}

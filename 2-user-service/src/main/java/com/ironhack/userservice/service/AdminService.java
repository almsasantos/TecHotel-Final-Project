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

/**
 * Service of Admin
 */
@Service
public class AdminService {
    /**
     * Autowired of Admin Repository
     */
    @Autowired
    private AdminRepository adminRepository;

    /**
     * Final variable of Logger
     */
    private static final Logger LOGGER = LogManager.getLogger(AdminService.class);

    /**
     * Find All Admins
     * @return a list of admins
     */
    public List<Admin> findAll(){
        LOGGER.info("Find all Admins");
        return adminRepository.findAll();
    }

    /**
     * Find admin by id
     * @param adminId receives a Long with admin's id
     * @return an Admin
     */
    public Admin findById(Long adminId){
        LOGGER.info("Find Admin with id " + adminId);
        return adminRepository.findById(adminId).orElseThrow(() -> new DataNotFoundException("Admin id not found"));
    }

    /**
     * Create a new Admin
     * @param admin receives an Admin
     * @return an Admin
     */
    public Admin createNewAdmin(Admin admin){
        LOGGER.info("Create new Admin");
        LOGGER.info("Check if username already exists");
        String username = admin.getUsername();
        restrictUsername(username);
        return adminRepository.save(admin);
    }

    /**
     * Delete an Admin
     * @param adminId receives a Long with admin's id
     */
    public void deleteAdmin(Long adminId){
        LOGGER.info("Delete Admin with id " + adminId);
        Admin admin = findById(adminId);
        adminRepository.delete(admin);
    }

    /**
     * Restrict username
     * @param username receives a String with username
     * @throws UsernameExistsException an Exception
     */
    public void restrictUsername(String username) throws UsernameExistsException {
        LOGGER.info("Make sure username " + username + " is available to new admin" );
        List<Admin> admins =  adminRepository.findAll().stream().filter(admin -> admin.getUsername().equals(username)).collect(Collectors.toList());
        if(admins.size()>0)
            throw new UsernameExistsException("This username already exists");

    }
}

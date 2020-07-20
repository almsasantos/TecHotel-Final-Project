package com.ironhack.userservice.controller.interfaces;

import com.ironhack.userservice.model.entities.Admin;

import java.util.List;

/**
 * Interface with all methods which will be implemented by AdminController
 */
public interface IAdminController {
    /**
     * Find all existing admins
     * @return a list with Admins
     */
    public List<Admin> findAllAdmin();

    /**
     * Find admin by id
     * @param adminId receives a long with id from Admin
     * @return an Admin
     */
    public Admin findAdminById(Long adminId);

    /**
     * Creates a new Admin
     * @param admin receives a class Admin
     * @return an Admin
     */
    public Admin createNewAdmin(Admin admin);

    /**
     * Remove an Admin
     * @param adminId receives a long with id from Admin
     */
    public void deleteAdmin(Long adminId);
}

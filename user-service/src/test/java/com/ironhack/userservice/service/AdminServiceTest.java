package com.ironhack.userservice.service;

import com.ironhack.userservice.exception.UsernameExistsException;
import com.ironhack.userservice.model.entities.Admin;
import com.ironhack.userservice.repository.AdminRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class AdminServiceTest {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminRepository adminRepository;

    private Admin admin;

    @BeforeEach
    void setUp() {
        admin = new Admin("Ana Santos", "alms", "pass");
        adminRepository.save(admin);
    }

    @AfterEach
    void tearDown() {
        adminRepository.deleteAll();
    }

    @Test
    void findAll() {
        assertEquals(1, adminService.findAll().size());
    }

    @Test
    void findById() {
        assertEquals(admin.getName(), adminService.findById(admin.getId()).getName());
    }

    @Test
    void createNewAdmin() {
        Admin admin1 = new Admin("Ana Martins", "almsasantos", "pass");
        adminService.createNewAdmin(admin1);
        assertEquals(2, adminService.findAll().size());
    }

    @Test
    void deleteAdmin() {
        adminService.deleteAdmin(admin.getId());
        assertEquals(0, adminService.findAll().size());
    }

    @Test
    void restrictUsername() {
        Admin admin = new Admin("Ana Santos", "alms", "pass");
        assertThrows(UsernameExistsException.class, () -> {
            adminService.createNewAdmin(admin);});
    }
}
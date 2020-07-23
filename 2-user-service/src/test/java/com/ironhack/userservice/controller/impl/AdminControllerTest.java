package com.ironhack.userservice.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.userservice.model.entities.Admin;
import com.ironhack.userservice.service.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class AdminControllerTest {
    @Autowired
    private AdminController adminController;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private AdminService adminService;

    private MockMvc mockMvc;
    private Admin admin;
    private Admin admin1;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        admin = new Admin("Ana Santos", "alms", "pass");

        List<Admin> adminList = Arrays.asList(admin);
        when(adminService.findAll()).thenReturn(adminList);
        when(adminService.findById(admin.getId())).thenReturn(admin);
    }

    @Test
    void findAllAdmin() throws Exception {
        mockMvc.perform(get("/users/admins"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(admin.getId()))
                .andExpect(jsonPath("$[0].name").value(admin.getName()))
                .andExpect(jsonPath("$[0].username").value(admin.getUsername()))
                .andExpect(jsonPath("$[0].password").value(admin.getPassword()));
    }

    @Test
    void findAdminById() throws Exception {
        mockMvc.perform(get("/users/admins/1"))
                .andExpect(status().isOk());
    }

    @Test
    void createNewAdmin() throws Exception {
        admin1 = new Admin("Ana Martins", "almsasantos", "pass");
        mockMvc.perform(post("/users/admins")
                .content(objectMapper.writeValueAsString(admin1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void deleteAdmin() throws Exception {
        mockMvc.perform(delete("/users/admins/1"))
                .andExpect(status().isNoContent());
    }
}
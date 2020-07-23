package com.ironhack.edgeservice.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.edgeservice.client.*;
import com.ironhack.edgeservice.dto.UserDto;
import com.ironhack.edgeservice.model.entities.security.UserSecurity;
import com.ironhack.edgeservice.model.enums.Role;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class SecurityControllerTest {

    @Autowired
    SecurityController securityController;

    @MockBean
    private ActivityClient activityClient;

    @MockBean
    private InvoiceClient invoiceClient;

    @MockBean
    private ReservationClient reservationClient;

    @MockBean
    private RoomClient roomClient;

    @MockBean
    private SecurityClient securityClient;

    @MockBean
    private UserClient userClient;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    private UserDto userDtoAdmin;
    private UserSecurity userSecurityAdmin;
    private UserSecurity userSecurityPremium;
    private UserSecurity userSecurityBasic;
    private List<UserDto> users = new ArrayList<UserDto>();

    @BeforeEach
    void setUp() {
        mockMvc =  MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        userSecurityAdmin = new UserSecurity("admin", "admin", Role.ADMIN);
        userSecurityPremium = new UserSecurity("premium", "admin", Role.PREMIUM);
        userSecurityBasic = new UserSecurity("basic", "admin", Role.BASIC);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void viewAllBasicUsers() throws Exception {
        when(securityClient.viewAllBasicUsers()).thenReturn(users);
        mockMvc.perform(get("/users/basic-users"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void viewAllPremiumUsers() throws Exception {
        when(securityClient.viewAllPremiumUsers()).thenReturn(users);
        mockMvc.perform(get("/users/premium-users"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createBasicUser() throws Exception {
        when(securityClient.createBasicUser(userSecurityBasic)).thenReturn(userSecurityBasic);
        mockMvc.perform(post("/users/basic-users")
                .content(objectMapper.writeValueAsString(userSecurityBasic))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void createPremiumUser() throws Exception {
        when(securityClient.createPremiumUser(userSecurityPremium)).thenReturn(userSecurityPremium);
        mockMvc.perform(post("/users/premium-users")
                .content(objectMapper.writeValueAsString(userSecurityPremium))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void createAdminUser() throws Exception {
        when(securityClient.createAdminUser(userSecurityAdmin)).thenReturn(userSecurityAdmin);
        mockMvc.perform(post("/users/admin-users")
                .content(objectMapper.writeValueAsString(userSecurityAdmin))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void login() {
    }
}
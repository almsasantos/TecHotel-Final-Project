package com.ironhack.securityservice.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.securityservice.enums.Role;
import com.ironhack.securityservice.model.User;
import com.ironhack.securityservice.repository.UserRepository;
import com.ironhack.securityservice.security.CustomSecurityUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class UserControllerImplTest {
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private CustomSecurityUser admin;
    private CustomSecurityUser basic;
    private CustomSecurityUser premium;
    ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
        admin = new CustomSecurityUser(new User("admin", "admin", Role.ADMIN));
        basic = new CustomSecurityUser(new User("basic", "basic", Role.BASIC));
        premium = new CustomSecurityUser(new User("premium", "premium", Role.PREMIUM));
        userRepository.save(new User("testUser", "1234", Role.PREMIUM));
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void isBasic() throws Exception {
        assertEquals(Boolean.toString(true), mockMvc.perform(get("/users/is-basic-user").with(user(basic)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString());
    }

    @Test
    void isPremium() throws Exception {
        assertEquals(Boolean.toString(true), mockMvc.perform(get("/users/is-premium-user").with(user(premium)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString());
    }

    @Test
    void isAdmin() throws Exception {
        assertEquals(Boolean.toString(true), mockMvc.perform(get("/users/is-admin").with(user(admin)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString());
    }

    @Test
    void createBasicUser() throws Exception {
        User user = new User("manolo", "manolo", Role.BASIC);

        String json = mapper.writeValueAsString(user);
        mockMvc.perform((post("/users/basic-users").with(user(admin)))
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username" ).value("manolo"))
                .andExpect(jsonPath("$.rol" ).value("BASIC"));
    }

    @Test
    void createUser_AlreadyExisting() throws Exception {
        User user = new User("basic", "basic", Role.BASIC);
        String json = mapper.writeValueAsString(user);
        mockMvc.perform((post("/users/basic-users").with(user(admin)))
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated());
        assertTrue(mockMvc.perform((post("/users/basic-users").with(user(admin)))
                .contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString().isEmpty());
    }

    @Test
    void viewAllBasicUsers() throws Exception {
        mockMvc.perform(get("/users/basic-users").with(user(basic)))
                .andExpect(status().isOk());
    }

    @Test
    void viewAllPremiumUsers() throws Exception {
        mockMvc.perform(get("/users/premium-users").with(user(premium)))
                .andExpect(status().isOk());
    }
}
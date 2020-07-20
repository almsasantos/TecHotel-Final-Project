package com.ironhack.userservice.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.userservice.model.classes.Account;
import com.ironhack.userservice.model.classes.Address;
import com.ironhack.userservice.model.classes.Money;
import com.ironhack.userservice.model.entities.Basic;
import com.ironhack.userservice.model.viewModel.BasicAndPremiumViewModel;
import com.ironhack.userservice.service.BasicService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class BasicControllerTest {
    @Autowired
    private BasicController basicController;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private BasicService basicService;

    private MockMvc mockMvc;

    private Account account;
    private Address address;
    private LocalDate birthDate = LocalDate.of(1995, 8, 19);
    private Basic basic;
    private Basic basic1;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        account = new Account(new Money(new BigDecimal("19.99")));

        address = new Address("Portugal", "Faro", "Rua Nova", "9800");

        basic = new Basic("Ana Martins", "alms", "pass", "987654321", "almsasantos@gmail.com", birthDate, address, account);

        List<Basic> basicList = Arrays.asList(basic);
        when(basicService.findAll()).thenReturn(basicList);
        when(basicService.findById(1L)).thenReturn(basic);
    }

    @Test
    void findAllBasicUser() throws Exception {
        mockMvc.perform(get("/users/basics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(basic.getId()))
                .andExpect(jsonPath("$[0].name").value(basic.getName()))
                .andExpect(jsonPath("$[0].username").value(basic.getUsername()))
                .andExpect(jsonPath("$[0].password").value(basic.getPassword()));
    }

    @Test
    void findBasicUserById() throws Exception {
        mockMvc.perform(get("/users/basics/1"))
                .andExpect(status().isOk());
    }

    @Test
    void createBasicUser() throws Exception {
        BasicAndPremiumViewModel basicAndPremiumViewModel = new BasicAndPremiumViewModel();
        basicAndPremiumViewModel.setName("Ana Santos");
        basicAndPremiumViewModel.setUsername("almsasantos");
        basicAndPremiumViewModel.setPassword("password");
        basicAndPremiumViewModel.setPhoneNumber("987654323");
        basicAndPremiumViewModel.setEmail("almsalmas@gmail.com");
        basicAndPremiumViewModel.setBirthDate(LocalDate.of(1999, 9, 9));
        basicAndPremiumViewModel.setCountry("Spain");
        basicAndPremiumViewModel.setCity("Madrid");
        basicAndPremiumViewModel.setPostalCode("28033");
        basicAndPremiumViewModel.setStreet("Calle Sol");
        basicAndPremiumViewModel.setBalance(new BigDecimal("2000"));

        mockMvc.perform(post("/users/basics")
                .content(objectMapper.writeValueAsString(basicAndPremiumViewModel))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void updateBasicRoomId() throws Exception {
        mockMvc.perform(patch("/users/basics/update-room/1/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void updateBasicBalance() throws Exception {
        mockMvc.perform(patch("/users/basics/update-balance/1/2500")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void updateBasicNumberOfStays() throws Exception {
        mockMvc.perform(patch("/users/basics/update-stays/1/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteBasicUser() throws Exception {
        mockMvc.perform(delete("/users/basics/1"))
                .andExpect(status().isNoContent());
    }
}
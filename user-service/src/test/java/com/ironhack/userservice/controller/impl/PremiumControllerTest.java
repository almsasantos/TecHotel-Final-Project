package com.ironhack.userservice.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.userservice.model.classes.Account;
import com.ironhack.userservice.model.classes.Address;
import com.ironhack.userservice.model.classes.Money;
import com.ironhack.userservice.model.entities.Premium;
import com.ironhack.userservice.model.viewModel.BasicAndPremiumViewModel;
import com.ironhack.userservice.service.PremiumService;
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
class PremiumControllerTest {
    @Autowired
    private PremiumController premiumController;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private PremiumService premiumService;

    private MockMvc mockMvc;

    private Account account;
    private Address address;
    private LocalDate birthDate = LocalDate.of(1995, 8, 19);
    private Premium premium;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        account = new Account(new Money(new BigDecimal("19.99")));

        address = new Address("Portugal", "Faro", "Rua Nova", "9800");

        premium = new Premium("Ana Martins", "alms", "pass", "987654321", "almsasantos@gmail.com", birthDate, address, account);

        List<Premium> premiumList = Arrays.asList(premium);
        when(premiumService.findAll()).thenReturn(premiumList);
        when(premiumService.findById(1L)).thenReturn(premium);
    }

    @Test
    void findAllPremiumUsers() throws Exception {
        mockMvc.perform(get("/users/premiums"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(premium.getId()))
                .andExpect(jsonPath("$[0].name").value(premium.getName()))
                .andExpect(jsonPath("$[0].username").value(premium.getUsername()))
                .andExpect(jsonPath("$[0].password").value(premium.getPassword()));
    }

    @Test
    void findPremiumUserById() throws Exception {
        mockMvc.perform(get("/users/premiums/1"))
                .andExpect(status().isOk());
    }

    @Test
    void createPremiumUser() throws Exception {
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

        mockMvc.perform(post("/users/premiums")
                .content(objectMapper.writeValueAsString(basicAndPremiumViewModel))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void updatePremiumRoomId() throws Exception {
        mockMvc.perform(patch("/users/premiums/update-room/1/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void updatePremiumBalance() throws Exception {
        mockMvc.perform(patch("/users/premiums/update-balance/1/2500")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void updatePremiumNumberOfStays() throws Exception {
        mockMvc.perform(patch("/users/premiums/update-stays/1/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void deletePremiumUser() throws Exception {
        mockMvc.perform(delete("/users/premiums/1"))
                .andExpect(status().isNoContent());
    }
}
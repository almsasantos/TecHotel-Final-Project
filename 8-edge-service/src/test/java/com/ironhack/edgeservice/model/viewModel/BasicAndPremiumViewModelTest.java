package com.ironhack.edgeservice.model.viewModel;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BasicAndPremiumViewModelTest {
    private BasicAndPremiumViewModel basicAndPremiumViewModel = new BasicAndPremiumViewModel();

    @Test
    void getName() {
        basicAndPremiumViewModel.setName("Ana Martins");

        assertEquals("Ana Martins", basicAndPremiumViewModel.getName());
    }

    @Test
    void getUsername() {
        basicAndPremiumViewModel.setUsername("alms");

        assertEquals("alms", basicAndPremiumViewModel.getUsername());
    }

    @Test
    void getPassword() {
        basicAndPremiumViewModel.setPassword("pass");

        assertEquals("pass", basicAndPremiumViewModel.getPassword());
    }

    @Test
    void getPhoneNumber() {
        basicAndPremiumViewModel.setPhoneNumber("987654321");

        assertEquals("987654321", basicAndPremiumViewModel.getPhoneNumber());
    }

    @Test
    void getEmail() {
        basicAndPremiumViewModel.setEmail("alms@gmail.com");

        assertEquals("alms@gmail.com", basicAndPremiumViewModel.getEmail());
    }

    @Test
    void getBirthDate() {
        basicAndPremiumViewModel.setBirthDate(LocalDate.of(1995, 8, 19));

        assertEquals(LocalDate.of(1995, 8, 19), basicAndPremiumViewModel.getBirthDate());
    }

    @Test
    void getCountry() {
        basicAndPremiumViewModel.setCountry("Spain");

        assertEquals("Spain", basicAndPremiumViewModel.getCountry());
    }

    @Test
    void getCity() {
        basicAndPremiumViewModel.setCity("Madrid");

        assertEquals("Madrid", basicAndPremiumViewModel.getCity());
    }

    @Test
    void getStreet() {
        basicAndPremiumViewModel.setStreet("Calle Gran Via");

        assertEquals("Calle Gran Via", basicAndPremiumViewModel.getStreet());
    }

    @Test
    void getPostalCode() {
        basicAndPremiumViewModel.setPostalCode("28033");

        assertEquals("28033", basicAndPremiumViewModel.getPostalCode());
    }

    @Test
    void getBalance() {
        basicAndPremiumViewModel.setBalance(new BigDecimal("1000"));

        assertEquals(new BigDecimal("1000"), basicAndPremiumViewModel.getBalance());
    }
}
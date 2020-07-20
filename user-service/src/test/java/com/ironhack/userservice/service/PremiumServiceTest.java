package com.ironhack.userservice.service;

import com.ironhack.userservice.exception.DataNotFoundException;
import com.ironhack.userservice.exception.UsernameExistsException;
import com.ironhack.userservice.model.classes.Account;
import com.ironhack.userservice.model.classes.Address;
import com.ironhack.userservice.model.classes.Money;
import com.ironhack.userservice.model.entities.Premium;
import com.ironhack.userservice.model.viewModel.BasicAndPremiumViewModel;
import com.ironhack.userservice.repository.PremiumRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class PremiumServiceTest {
    @Autowired
    private PremiumService premiumService;

    @Autowired
    private PremiumRepository premiumRepository;

    private Premium premium;
    private Account account;
    private Address address;
    private LocalDate birthDate = LocalDate.of(1995, 8, 19);

    @BeforeEach
    void setUp() {
        account = new Account(new Money(new BigDecimal("19.99")));

        address = new Address("Portugal", "Faro", "Rua Nova", "9800");

        premium = new Premium("Ana Martins", "alms", "pass", "987654321", "almsasantos@gmail.com", birthDate, address, account);
        premiumRepository.save(premium);
    }

    @AfterEach
    void tearDown() {
        premiumRepository.deleteAll();
    }

    @Test
    void findAll() {
        assertEquals(1, premiumRepository.findAll().size());
    }

    @Test
    void findById() {
        assertEquals(premium.getName(), premiumRepository.findById(premium.getId()).get().getName());
    }

    @Test
    void createPremiumUser() {
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

        premiumService.createPremiumUser(basicAndPremiumViewModel);

        assertEquals(2, premiumService.findAll().size());
    }

    @Test
    void updateRoomId() {
        premiumService.updateRoomId(premium.getId(), 2);

        assertEquals(2, premiumService.findById(premium.getId()).getRoomId());
    }

    @Test
    void updateNumberOfStays() {
        premiumService.updateNumberOfStays(premium.getId(), 2);

        assertEquals(2, premiumService.findById(premium.getId()).getNumberOfStays());
    }

    @Test
    void updatePremiumBalance() {
        premiumService.updatePremiumBalance(premium.getId(), new BigDecimal("2500"));

        assertEquals(new Money(new BigDecimal("2500")), premiumService.findById(premium.getId()).getBankAccount().getBalance());
    }

    @Test
    void deletePremiumUser() {
        premiumService.deletePremiumUser(premium.getId());

        assertEquals(0, premiumService.findAll().size());
    }

    @Test
    void restrictUsername() {
        BasicAndPremiumViewModel basicAndPremiumViewModel = new BasicAndPremiumViewModel();
        basicAndPremiumViewModel.setName("Ana Santos");
        basicAndPremiumViewModel.setUsername("alms");
        basicAndPremiumViewModel.setPassword("password");
        basicAndPremiumViewModel.setPhoneNumber("987654323");
        basicAndPremiumViewModel.setEmail("almsalmas@gmail.com");
        basicAndPremiumViewModel.setBirthDate(LocalDate.of(1999, 9, 9));
        basicAndPremiumViewModel.setCountry("Spain");
        basicAndPremiumViewModel.setCity("Madrid");
        basicAndPremiumViewModel.setPostalCode("28033");
        basicAndPremiumViewModel.setStreet("Calle Sol");
        basicAndPremiumViewModel.setBalance(new BigDecimal("2000"));

        assertThrows(UsernameExistsException.class, () -> {
            premiumService.createPremiumUser(basicAndPremiumViewModel);});
    }

    @Test
    void premiumUserIdMatchesName_throwsDataNotFoundException() {
        premiumService.deletePremiumUser(premium.getId());
        assertThrows(DataNotFoundException.class, () -> {
            premiumService.premiumUserIdMatchesName(premium.getId(), premium.getName());});
    }

    @Test
    void premiumUserIdMatchesName_throwsDataNotFoundException_nameDifferent() {
        assertThrows(DataNotFoundException.class, () -> {
            premiumService.premiumUserIdMatchesName(premium.getId(), "Marta Santos");});
    }
}
package com.ironhack.userservice.service;

import com.ironhack.userservice.exception.DataNotFoundException;
import com.ironhack.userservice.exception.UsernameExistsException;
import com.ironhack.userservice.model.classes.Account;
import com.ironhack.userservice.model.classes.Address;
import com.ironhack.userservice.model.classes.Money;
import com.ironhack.userservice.model.entities.Basic;
import com.ironhack.userservice.model.viewModel.BasicAndPremiumViewModel;
import com.ironhack.userservice.repository.BasicRepository;
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
class BasicServiceTest {

    @Autowired
    private BasicService basicService;

    @Autowired
    private BasicRepository basicRepository;

    private Basic basic;
    private Account account;
    private Address address;
    private LocalDate birthDate = LocalDate.of(1995, 8, 19);

    @BeforeEach
    void setUp() {
        account = new Account(new Money(new BigDecimal("19.99")));

        address = new Address("Portugal", "Faro", "Rua Nova", "9800");

        basic = new Basic("Ana Martins", "alms", "pass", "987654321", "almsasantos@gmail.com", birthDate, address, account);
        basicRepository.save(basic);
    }

    @AfterEach
    void tearDown() {
        basicRepository.deleteAll();
    }

    @Test
    void findAll() {
        assertEquals(1, basicService.findAll().size());
    }

    @Test
    void findById() {
        assertEquals(basic.getName(), basicService.findById(basic.getId()).getName());
    }

    @Test
    void createBasicUser() {
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

        basicService.createBasicUser(basicAndPremiumViewModel);

        assertEquals(2, basicService.findAll().size());
    }

    @Test
    void updateRoomId() {
        basicService.updateRoomId(basic.getId(), 1);
        assertEquals(1, basicService.findById(basic.getId()).getRoomId());
    }

    @Test
    void updateNumberOfStays() {
        basicService.updateNumberOfStays(basic.getId(), 2);
        assertEquals(2, basicService.findById(basic.getId()).getNumberOfStays());
    }

    @Test
    void updateBasicBalance() {
        basicService.updateBasicBalance(basic.getId(), new BigDecimal("2000"));
        assertEquals(new Money(new BigDecimal("2000")), basicService.findById(basic.getId()).getBankAccount().getBalance());
    }

    @Test
    void deleteBasicUser() {
        basicService.deleteBasicUser(basic.getId());
        assertEquals(0, basicService.findAll().size());
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
            basicService.createBasicUser(basicAndPremiumViewModel);});
    }

    @Test
    void basicUserIdMatchesName_throwsDataNotFoundException() {
        basicService.deleteBasicUser(basic.getId());
        assertThrows(DataNotFoundException.class, () -> {
            basicService.basicUserIdMatchesName(basic.getId(), basic.getName());});
    }

    @Test
    void basicUserIdMatchesName_throwsDataNotFoundException_nameDifferent() {
        assertThrows(DataNotFoundException.class, () -> {
            basicService.basicUserIdMatchesName(basic.getId(), "Marta Santos");});
    }
}
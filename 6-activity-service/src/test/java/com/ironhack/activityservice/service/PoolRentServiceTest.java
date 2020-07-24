package com.ironhack.activityservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.activityservice.client.InvoiceClient;
import com.ironhack.activityservice.client.RoomClient;
import com.ironhack.activityservice.client.UserClient;
import com.ironhack.activityservice.dto.UpdateFloatiesNumDto;
import com.ironhack.activityservice.dto.UpdateTowelNumDto;
import com.ironhack.activityservice.exception.ReservationException;
import com.ironhack.activityservice.model.classes.Account;
import com.ironhack.activityservice.model.classes.Address;
import com.ironhack.activityservice.model.classes.Money;
import com.ironhack.activityservice.model.entities.PoolRent;
import com.ironhack.activityservice.model.enums.TypeOfUser;
import com.ironhack.activityservice.model.invoices.Invoice;
import com.ironhack.activityservice.model.users.Basic;
import com.ironhack.activityservice.model.viewModel.PoolRentViewModel;
import com.ironhack.activityservice.repository.PoolRentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class PoolRentServiceTest {

    @Autowired
    private PoolRentService poolRentService;

    @Autowired
    private PoolRentRepository poolRentRepository;

    @MockBean
    private InvoiceClient invoiceClient;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private RoomClient roomClient;

    @MockBean
    private UserClient userClient;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();
    private PoolRent poolRent;
    private List<PoolRent> poolRentList = new ArrayList<PoolRent>();
    List<Object[]> list = new ArrayList<Object[]>();
    private Account account = new Account();
    private Address address = new Address();
    private LocalDate birthDate = LocalDate.of(1995, 8, 19);
    private Invoice invoice;
    private Basic basic;

    @BeforeEach
    void setUp() {
        mockMvc =  MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        poolRent = new PoolRent(1L, 2, 1, 1, LocalDateTime.now());
        poolRent.setActivityId(1L);
        poolRentRepository.save(poolRent);
        poolRentList.add(poolRent);
        basic = new Basic("Ana Martins", "alms", "pass", "987654321", "almsasantos@gmail.com", birthDate, address, account);
    }

    @AfterEach
    void tearDown() {
        poolRentRepository.deleteAll();
    }

    @Test
    void findAll() {
        assertEquals(1, poolRentService.findAll().size());
    }

    @Test
    void findPoolRentById() {
        assertEquals(poolRent.getRoomId(), poolRentService.findPoolRentById(poolRent.getActivityId()).getRoomId());
    }

    @Test
    void createPoolRent() {
        PoolRentViewModel poolRentViewModel = new PoolRentViewModel();
        poolRentViewModel.setFloatiesNum(3);
        poolRentViewModel.setRoomId(1);
        poolRentViewModel.setTowelNum(3);
        poolRentViewModel.setUserId(1L);
        PoolRent poolRent1 = new PoolRent(1L, 1, 3, 3, LocalDateTime.now());

        assertEquals(null, poolRentService.createPoolRent(poolRentViewModel));
    }

    @Test
    void updateFloatiesNum() {
        UpdateFloatiesNumDto updateFloatiesNumDto = new UpdateFloatiesNumDto(1L, 1);
        poolRentService.updateFloatiesNum(updateFloatiesNumDto);
        assertEquals(1, poolRent.getFloatiesNum());

    }

    @Test
    void updateTowelNum() {
        UpdateTowelNumDto updateTowelNumDto = new UpdateTowelNumDto(1L, 1);
        poolRentService.updateTowelNum(updateTowelNumDto);
        assertEquals(1, poolRent.getTowelNum());
    }

    @Test
    void cancelPoolRent() {
        assertThrows(ReservationException.class, () -> {
            poolRentService.cancelPoolRent(poolRent.getActivityId());}); }

    @Test
    void confirmData() {
    }

    @Test
    void checkEnoughBalance() {
    }

    @Test
    void resetMoney() {
    }

    @Test
    void confirmDataFail() {
        poolRentService.confirmDataFail(1L, 1, new Money(new BigDecimal("20.99")));
        assertEquals(1, poolRent.getTowelNum());
    }

    @Test
    void resetMoneyFail() {
        poolRentService.resetMoneyFail(1L, new BigDecimal("20.99"));
    }

    @Test
    void checkEnoughBalanceFail() {
        assertThrows(ReservationException.class, () -> {
            poolRentService.checkEnoughBalanceFail(basic.getId(), TypeOfUser.BASIC, new Money(new BigDecimal("19.99")));});
    }

    @Test
    void createPoolRentFail() {
        PoolRentViewModel poolRentViewModel = new PoolRentViewModel();
        poolRentViewModel.setFloatiesNum(3);
        poolRentViewModel.setRoomId(1);
        poolRentViewModel.setTowelNum(3);
        poolRentViewModel.setUserId(1L);
        assertThrows(ReservationException.class, () -> {
            poolRentService.createPoolRentFail(poolRentViewModel);});
    }

    @Test
    void filterPoolRentByUserId() {
        assertEquals(1, poolRentService.filterPoolRentByUserId(1L).size());
    }
}
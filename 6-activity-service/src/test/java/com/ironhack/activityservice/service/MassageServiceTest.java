package com.ironhack.activityservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.activityservice.client.InvoiceClient;
import com.ironhack.activityservice.client.RoomClient;
import com.ironhack.activityservice.client.UserClient;
import com.ironhack.activityservice.dto.UpdateMassageTypeDto;
import com.ironhack.activityservice.exception.NotEnoughBalanceException;
import com.ironhack.activityservice.exception.ReservationException;
import com.ironhack.activityservice.model.classes.Account;
import com.ironhack.activityservice.model.classes.Address;
import com.ironhack.activityservice.model.classes.Money;
import com.ironhack.activityservice.model.entities.Massage;
import com.ironhack.activityservice.model.enums.InvoiceType;
import com.ironhack.activityservice.model.enums.MassageType;
import com.ironhack.activityservice.model.enums.TypeOfUser;
import com.ironhack.activityservice.model.invoices.Invoice;
import com.ironhack.activityservice.model.users.Basic;
import com.ironhack.activityservice.model.viewModel.MassageViewModel;
import com.ironhack.activityservice.repository.MassageRepository;
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
class MassageServiceTest {
    @Autowired
    private MassageService massageService;

    @Autowired
    private MassageRepository massageRepository;

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
    private Massage massage;
    List<Massage> massageList = new ArrayList<Massage>();
    private Account account = new Account();
    private Address address = new Address();
    private LocalDate birthDate = LocalDate.of(1995, 8, 19);
    private Invoice invoice;
    private Basic basic;

    @BeforeEach
    void setUp() {
        mockMvc =  MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        massage = new Massage(1L, 2, MassageType.AROMATHERAPY, LocalDateTime.now());
        massageRepository.save(massage);
        massageList.add(massage);
        invoice = new Invoice(1L, "Ana Martins", 2, InvoiceType.ROOM_FOOD_SERVICE, new BigDecimal("9.99"));
        basic = new Basic("Ana Martins", "alms", "pass", "987654321", "almsasantos@gmail.com", birthDate, address, account);
    }

    @AfterEach
    void tearDown() {
        massageRepository.deleteAll();
    }

    @Test
    void findAll() {
        assertEquals(1, massageService.findAll().size());
    }

    @Test
    void findMassageById() {
        assertEquals(massage.getRoomId(), massageService.findMassageById(massage.getActivityId()).getRoomId());
    }

    @Test
    void changeMassageType_withoutMicroservice() {
        UpdateMassageTypeDto updateMassageTypeDto = new UpdateMassageTypeDto(1L, MassageType.COUPLES);
        massageService.changeMassageType(updateMassageTypeDto);
        assertEquals(MassageType.AROMATHERAPY, massage.getMassageType());
    }

    @Test
    void createMassageAppointment() {
        MassageViewModel massageViewModel = new MassageViewModel();
        massageViewModel.setUserId(1L);
        massageViewModel.setRoomId(1);
        massageViewModel.setMassageType(MassageType.COUPLES);
        Massage newMassage = massageService.createMassageAppointment(massageViewModel);
        assertThrows(ReservationException.class, () -> {
            massageService.createMassageAppointment(massageViewModel);});
    }

    @Test
    void cancelMassageAppointment() {
        assertThrows(ReservationException.class, () -> {
            massageService.cancelMassageAppointment(massage.getActivityId());});
    }

    @Test
    void confirmData() {
        basic.setId(1L);
        basic.setRoomId(1);
        assertThrows(ReservationException.class, () -> {
            massageService.confirmData(basic.getId(), basic.getRoomId(), new Money(new BigDecimal("19.99")));});
    }

    @Test
    void checkEnoughBalance() {
        account.setBalance(new Money(new BigDecimal("10.00")));
        Basic basic = new Basic("Ana Martins", "alms", "pass", "987654321", "almsasantos@gmail.com", birthDate, address, account);
        basic.setId(1L);
        basic.setRoomId(1);
        assertThrows(NotEnoughBalanceException.class, () -> {
            massageService.checkEnoughBalance(basic.getId(), TypeOfUser.BASIC, new Money(new BigDecimal("19.99")));});
    }

    @Test
    void resetMoney() {
    }

    @Test
    void confirmDataFail() {
        massageService.confirmDataFail(1L, 1, new Money(new BigDecimal("20.99")));
        assertEquals(MassageType.AROMATHERAPY.getPrice(), massage.getMassageType().getPrice());
    }

    @Test
    void resetMoneyFail() {
        massageService.resetMoneyFail(1L, new BigDecimal("20.99"));
    }

    @Test
    void checkEnoughBalanceFail() {
        assertThrows(ReservationException.class, () -> {
            massageService.checkEnoughBalanceFail(basic.getId(), TypeOfUser.BASIC, new Money(new BigDecimal("19.99")));});
    }

    @Test
    void createMassageAppointmentFail() {
        MassageViewModel massageViewModel = new MassageViewModel();
        massageViewModel.setUserId(1L);
        massageViewModel.setRoomId(1);
        massageViewModel.setMassageType(MassageType.COUPLES);

        assertThrows(ReservationException.class, () -> {
            massageService.createMassageAppointmentFail(massageViewModel);});
    }

    @Test
    void updateUserMassageFail() {
        assertThrows(ReservationException.class, () -> {
            massageService.updateUserMassageFail(massage, invoice, 1L);});
    }

    @Test
    void filterMassageByUserId() {
        assertEquals(1, massageService.filterMassageByUserId(1L).size());
    }
}
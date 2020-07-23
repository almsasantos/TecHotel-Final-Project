package com.ironhack.reservationservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.reservationservice.client.InvoiceClient;
import com.ironhack.reservationservice.client.RoomClient;
import com.ironhack.reservationservice.client.UserClient;
import com.ironhack.reservationservice.exception.NotEnoughBalanceException;
import com.ironhack.reservationservice.exception.ReservationException;
import com.ironhack.reservationservice.model.classes.Money;
import com.ironhack.reservationservice.model.entities.RoomReservation;
import com.ironhack.reservationservice.model.entities.UserReservation;
import com.ironhack.reservationservice.model.entities.rooms.RegularRoom;
import com.ironhack.reservationservice.model.entities.rooms.SuiteRoom;
import com.ironhack.reservationservice.model.entities.users.Basic;
import com.ironhack.reservationservice.model.enums.RoomType;
import com.ironhack.reservationservice.model.enums.TypeOfUser;
import com.ironhack.reservationservice.model.viewmodel.InvoiceViewModel;
import com.ironhack.reservationservice.model.viewmodel.RoomReservationViewModel;
import com.ironhack.reservationservice.repository.RoomReservationRepository;
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
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class RoomReservationServiceTest {

    @Autowired
    private RoomReservationService roomReservationService;

    @Autowired
    private RoomReservationRepository roomReservationRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private InvoiceClient invoiceClient;

    @MockBean
    private RoomClient roomClient;

    @MockBean
    private UserClient userClient;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    private RoomReservation roomReservation;
    private UserReservation userReservation;
    List<RoomReservation> list;
    private RoomReservationViewModel roomReservationViewModel = new RoomReservationViewModel();

    @BeforeEach
    void setUp() {
        mockMvc =  MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        userReservation = new UserReservation();
        userReservation.setTypeOfUser(TypeOfUser.BASIC);
        userReservation.setUserId(1L);

        roomReservation = new RoomReservation();
        roomReservation.setUserReservation(userReservation);
        roomReservation.setRoomType(RoomType.REGULAR_ROOM);
        roomReservation.setCheckInDay(LocalDate.now());
        roomReservation.setTotalPrice(new Money(new BigDecimal("109")));
        roomReservation.setNumberOfNights(2);
        roomReservation.setRoomId(1);
        roomReservation.setRoomReservationId(1L);
        roomReservation.setCheckOutDay(LocalDate.now());

        roomReservationRepository.save(roomReservation);

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void findAllRoomReservations() {
        assertEquals(1, roomReservationService.findAllRoomReservations().size());
    }

    @Test
    void findRoomReservationById() {
        assertEquals(1, roomReservationService.findRoomReservationById(roomReservation.getRoomReservationId()).getRoomId());
    }

    @Test
    void newRoomReservation() {
        RoomReservation roomReservation = new RoomReservation();
        RegularRoom regularRoom = new RegularRoom();
        Basic basic = new Basic();
        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        UserReservation userReservation = null;
        List<Basic> basicUsers = new ArrayList<Basic>();
        basicUsers.add(basic);
    }

    @Test
    void findIfRegularRoomAvailable() {
        RegularRoom regularRoom = new RegularRoom();
        regularRoom.setRoomId(1);
        regularRoom.setRoomType(RoomType.REGULAR_ROOM);
        regularRoom.setAvailable(false);
        assertThrows(ReservationException.class, () -> {
            roomReservationService.findIfRoomAvailable(1, RoomType.REGULAR_ROOM);});
    }

    @Test
    void findIfSuiteRoomAvailable() {
        SuiteRoom suite = new SuiteRoom();
        suite.setRoomId(1);
        suite.setRoomType(RoomType.SUITE_ROOM);
        suite.setAvailable(false);
        assertThrows(ReservationException.class, () -> {
            roomReservationService.findIfRoomAvailable(1, RoomType.SUITE_ROOM);});
    }

    @Test
    void userIdMatchesRoom() {
        Basic basic = new Basic();
        basic.setId(1L);
        basic.setRoomId(1);
        List<Basic> basics = new ArrayList<Basic>();
        basics.add(basic);
        assertThrows(ReservationException.class, () -> {
            roomReservationService.userIdMatchesRoom(1L,1);});

    }

    @Test
    void newRoomReservationFail() {
        assertEquals(null, roomReservationService.newRoomReservationFail(roomReservationViewModel));
    }

    @Test
    void roomAvailabilityFail() {
        assertThrows(ReservationException.class, () -> {
            roomReservationService.roomAvailabilityFail(1, RoomType.REGULAR_ROOM);});
    }

    @Test
    void checkEnoughBalanceBasicFail() {
        assertThrows(NotEnoughBalanceException.class, () -> {
            roomReservationService.checkEnoughBalance(1L, TypeOfUser.BASIC, new Money(new BigDecimal("9.99")));});
    }

    @Test
    void checkEnoughBalancePremiumFail() {
        assertThrows(NotEnoughBalanceException.class, () -> {
            roomReservationService.checkEnoughBalance(1L, TypeOfUser.PREMIUM, new Money(new BigDecimal("9.99")));});
    }
}
package com.ironhack.reservationservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.reservationservice.client.InvoiceClient;
import com.ironhack.reservationservice.client.RoomClient;
import com.ironhack.reservationservice.client.UserClient;
import com.ironhack.reservationservice.exception.ReservationException;
import com.ironhack.reservationservice.model.classes.Money;
import com.ironhack.reservationservice.model.entities.RoomReservation;
import com.ironhack.reservationservice.model.entities.UserReservation;
import com.ironhack.reservationservice.model.enums.RoomType;
import com.ironhack.reservationservice.model.enums.TypeOfUser;
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
    void checkEnoughBalance() {
    }

    @Test
    void newRoomReservation() {
    }

    @Test
    void findIfRoomAvailable() {
    }

    @Test
    void userIdMatchesRoom() {
    }

    @Test
    void removeRoomReservation() {
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
    void removeRoomReservationFail() {
        assertThrows(ReservationException.class, () -> {
            roomReservationService.removeRoomReservationFail(1L);});
    }

    @Test
    void checkEnoughBalanceFail() {
        assertThrows(ReservationException.class, () -> {
            roomReservationService.newRoomReservationFail(roomReservationViewModel);});
    }
}
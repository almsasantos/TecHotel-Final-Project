package com.ironhack.reservationservice.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.reservationservice.client.InvoiceClient;
import com.ironhack.reservationservice.client.RoomClient;
import com.ironhack.reservationservice.client.UserClient;
import com.ironhack.reservationservice.model.classes.Money;
import com.ironhack.reservationservice.model.entities.RoomReservation;
import com.ironhack.reservationservice.model.entities.UserReservation;
import com.ironhack.reservationservice.model.enums.RoomType;
import com.ironhack.reservationservice.model.enums.TypeOfUser;
import com.ironhack.reservationservice.model.viewmodel.RoomReservationViewModel;
import com.ironhack.reservationservice.service.RoomReservationService;
import org.junit.jupiter.api.AfterEach;
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
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class RoomReservationControllerTest {

    @Autowired
    private RoomReservationController roomReservationController;

    @MockBean
    private RoomReservationService roomReservationService;

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
        list = Collections.singletonList(roomReservation);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAllRoomReservations() throws Exception {
        when(roomReservationController.findAllRoomReservations()).thenReturn(list);
        mockMvc.perform(get("/reservations/rooms"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void findRoomReservationById() throws Exception {
        when(roomReservationController.findRoomReservationById(1L)).thenReturn(roomReservation);
        mockMvc.perform(get("/reservations/rooms/"+1))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void newRoomReservation() throws Exception {
        roomReservationViewModel.setNumberOfNights(2);
        roomReservationViewModel.setRoomType(RoomType.REGULAR_ROOM);
        roomReservationViewModel.setRoomId(2);
        roomReservationViewModel.setUserName("Ana Martins");
        roomReservationViewModel.setUserId(1L);
        when(roomReservationController.newRoomReservation(roomReservationViewModel)).thenReturn(roomReservation);
        mockMvc.perform(post("/reservations/rooms")
                .content(objectMapper.writeValueAsString(roomReservationViewModel))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void userIdMatchesRoom() throws Exception {
        mockMvc.perform(get("/reservations/user-matches-room/"+1+"/"+1))
                .andExpect(status().isOk());
    }
}
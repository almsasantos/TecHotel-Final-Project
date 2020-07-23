package com.ironhack.edgeservice.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.edgeservice.client.*;
import com.ironhack.edgeservice.model.classes.Money;
import com.ironhack.edgeservice.model.entities.reservations.RoomReservation;
import com.ironhack.edgeservice.model.entities.reservations.UserReservation;
import com.ironhack.edgeservice.model.enums.RoomType;
import com.ironhack.edgeservice.model.enums.TypeOfUser;
import com.ironhack.edgeservice.model.viewModel.RoomReservationViewModel;
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
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ReservationControllerTest {
    @Autowired
    ReservationController reservationController;

    @MockBean
    private ActivityClient activityClient;

    @MockBean
    private InvoiceClient invoiceClient;

    @MockBean
    private ReservationClient reservationClient;

    @MockBean
    private RoomClient roomClient;

    @MockBean
    private SecurityClient securityClient;

    @MockBean
    private UserClient userClient;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    private RoomReservation roomReservation;
    private UserReservation userReservation;
    List<RoomReservation> roomReservationList = new ArrayList<RoomReservation>();

    @BeforeEach
    void setUp() {
        mockMvc =  MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        userReservation = new UserReservation();
        userReservation.setTypeOfUser(TypeOfUser.BASIC);
        userReservation.setUserId(1L);

        roomReservation = new RoomReservation();
        roomReservation.setCheckInDay(LocalDate.now());
        roomReservation.setNumberOfNights(1);
        roomReservation.setRoomId(1);
        roomReservation.setRoomReservationId(1L);
        roomReservation.setRoomType(RoomType.REGULAR_ROOM);
        roomReservation.setUserReservation(userReservation);
        roomReservation.setTotalPrice(new Money(new BigDecimal("100")));

        roomReservationList.add(roomReservation);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAllRoomReservations() throws Exception {
        when(reservationClient.findAllRoomReservations()).thenReturn(roomReservationList);
        mockMvc.perform(get("/reservations/rooms")
                .header("Authorization","admin"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void findRoomReservationById() throws Exception {
        when(reservationClient.findRoomReservationById(1L)).thenReturn(roomReservation);
        mockMvc.perform(get("/reservations/rooms/"+1)
                .header("Authorization","admin"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void newRoomReservation() throws Exception {
        RoomReservationViewModel roomReservationViewModel = new RoomReservationViewModel();
        roomReservationViewModel.setUserName("alms");
        roomReservationViewModel.setNumberOfNights(1);
        roomReservationViewModel.setUserId(1L);
        roomReservationViewModel.setRoomType(RoomType.REGULAR_ROOM);

        when(reservationClient.newRoomReservation(roomReservationViewModel)).thenReturn(roomReservation);
        mockMvc.perform(post("/reservations/rooms")
                .header("Authorization","admin")
                .content(objectMapper.writeValueAsString(roomReservationViewModel))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void userIdMatchesRoom() throws Exception {
        when(reservationClient.userIdMatchesRoom(1L, 1)).thenReturn(true);
        mockMvc.perform(get("/reservations/user-matches-room/"+ 1L+"/"+1)
                .header("Authorization","admin"))
                .andExpect(status().is4xxClientError());
    }
}
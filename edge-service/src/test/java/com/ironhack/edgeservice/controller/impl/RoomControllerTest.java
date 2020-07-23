package com.ironhack.edgeservice.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.edgeservice.client.*;
import com.ironhack.edgeservice.model.entities.rooms.RegularRoom;
import com.ironhack.edgeservice.model.entities.rooms.SuiteRoom;
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
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class RoomControllerTest {
    @Autowired
    RoomController roomController;

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
    private RegularRoom regularRoom;
    private List<RegularRoom> regularRoomList = new ArrayList<RegularRoom>();
    private SuiteRoom suiteRoom;
    private List<SuiteRoom> suiteRoomList = new ArrayList<SuiteRoom>();

    @BeforeEach
    void setUp() {
        mockMvc =  MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        regularRoom = new RegularRoom(2, true, true, new BigDecimal("89.99"));
        regularRoomList.add(regularRoom);
        suiteRoom = new SuiteRoom(2, new BigDecimal("89.99"));
        suiteRoomList.add(suiteRoom);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void findAllRegularRooms() throws Exception {
        when(roomClient.findAllRegularRooms()).thenReturn(regularRoomList);
        mockMvc.perform(get("/regular-rooms")
                .header("Authorization","admin"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void findRegularRoomById() throws Exception {
        when(roomClient.findRegularRoomById(1)).thenReturn(regularRoom);
        mockMvc.perform(get("/regular-rooms/"+1)
                .header("Authorization","admin"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void updateRegularRoomAvailability() throws Exception {
        mockMvc.perform(patch("/update-regular-rooms/"+1+"/true")
                .header("Authorization","admin"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void createRoom() throws Exception {
        when(roomClient.createRoom(regularRoom)).thenReturn(regularRoom);
        mockMvc.perform(post("/regular-rooms")
                .header("Authorization","admin")
                .content(objectMapper.writeValueAsString(regularRoom))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void findAllSuites() throws Exception {
        when(roomClient.findAllSuites()).thenReturn(suiteRoomList);
        mockMvc.perform(get("/suites")
                .header("Authorization","admin"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void findSuiteById() throws Exception {
        when(roomClient.findSuiteById(1)).thenReturn(suiteRoom);
        mockMvc.perform(get("/suites/"+1)
                .header("Authorization","admin"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void updateSuiteAvailability() throws Exception {
        mockMvc.perform(patch("/update-suites/"+1+"/true")
                .header("Authorization","admin"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void createSuite() throws Exception {
        when(roomClient.createSuite(suiteRoom)).thenReturn(suiteRoom);
        mockMvc.perform(post("/suites")
                .header("Authorization","admin")
                .content(objectMapper.writeValueAsString(suiteRoom))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
}
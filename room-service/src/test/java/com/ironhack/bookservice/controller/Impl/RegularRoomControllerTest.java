package com.ironhack.bookservice.controller.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.bookservice.model.RegularRoom;
import com.ironhack.bookservice.service.RegularRoomService;
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
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class RegularRoomControllerTest {
    @Autowired
    private RegularRoomController regularRoomController;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private RegularRoomService regularRoomService;

    private MockMvc mockMvc;
    private RegularRoom regularRoom;
    private RegularRoom regularRoom1;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        regularRoom = new RegularRoom(2, true, true, new BigDecimal("89.99"));

        List<RegularRoom> regularRooms = Arrays.asList(regularRoom);
        when(regularRoomService.findAll()).thenReturn(regularRooms);
        when(regularRoomService.findById(regularRoom.getRoomId())).thenReturn(regularRoom);
    }

    @Test
    void findAllRegularRooms() throws Exception {
        mockMvc.perform(get("/regular-rooms"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].roomId").value(regularRoom.getRoomId()))
                .andExpect(jsonPath("$[0].numberOfBeds").value(regularRoom.getNumberOfBeds()))
                .andExpect(jsonPath("$[0].hairDryer").value(regularRoom.getHairDryer()))
                .andExpect(jsonPath("$[0].bathTub").value(regularRoom.getBathTub()))
                .andExpect(jsonPath("$[0].price").value(regularRoom.getPrice()))
                .andExpect(jsonPath("$[0].available").value(regularRoom.getAvailable()));

    }

    @Test
    void findRegularRoomById() throws Exception {
        mockMvc.perform(get("/regular-rooms/1"))
                .andExpect(status().isOk());
    }

    @Test
    void updateRegularRoomAvailability() throws Exception {
        mockMvc.perform(patch("/update-regular-rooms/1/false"))
                .andExpect(status().isNoContent());
    }

    @Test
    void createRoom() throws Exception {
        regularRoom1 = new RegularRoom(2, true, true, new BigDecimal("89.99"));
        mockMvc.perform(post("/regular-rooms")
                .content(objectMapper.writeValueAsString(regularRoom1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}
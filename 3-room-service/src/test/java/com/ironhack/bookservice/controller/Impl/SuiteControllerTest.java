package com.ironhack.bookservice.controller.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.bookservice.model.SuiteRoom;
import com.ironhack.bookservice.service.SuiteService;
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
class SuiteControllerTest {
    @Autowired
    private SuiteController suiteController;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private SuiteService suiteService;

    private MockMvc mockMvc;
    private SuiteRoom suiteRoom;
    private SuiteRoom suiteRoom1;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        suiteRoom = new SuiteRoom(2, new BigDecimal("89.99"));

        List<SuiteRoom> suiteRooms = Arrays.asList(suiteRoom);
        when(suiteService.findAll()).thenReturn(suiteRooms);
        when(suiteService.findById(suiteRoom.getRoomId())).thenReturn(suiteRoom);
    }

    @Test
    void findAllSuites() throws Exception {
        mockMvc.perform(get("/suites"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].roomId").value(suiteRoom.getRoomId()))
                .andExpect(jsonPath("$[0].numberOfBeds").value(suiteRoom.getNumberOfBeds()))
                .andExpect(jsonPath("$[0].hairDryer").value(suiteRoom.getHairDryer()))
                .andExpect(jsonPath("$[0].bathTub").value(suiteRoom.getBathTub()))
                .andExpect(jsonPath("$[0].price").value(suiteRoom.getPrice()))
                .andExpect(jsonPath("$[0].available").value(suiteRoom.getAvailable()));
    }

    @Test
    void findSuiteById() throws Exception {
        mockMvc.perform(get("/suites/1"))
                .andExpect(status().isOk());
    }

    @Test
    void updateSuiteAvailability() throws Exception {
        mockMvc.perform(patch("/update-suites/1/false"))
                .andExpect(status().isNoContent());
    }

    @Test
    void createSuite() throws Exception {
        suiteRoom1 = new SuiteRoom(3, new BigDecimal("129.99"));
        mockMvc.perform(post("/suites")
                .content(objectMapper.writeValueAsString(suiteRoom1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}
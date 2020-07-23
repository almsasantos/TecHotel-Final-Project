package com.ironhack.activityservice.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.activityservice.client.InvoiceClient;
import com.ironhack.activityservice.client.RoomClient;
import com.ironhack.activityservice.client.UserClient;
import com.ironhack.activityservice.dto.UpdateFloatiesNumDto;
import com.ironhack.activityservice.dto.UpdateTowelNumDto;
import com.ironhack.activityservice.model.entities.PoolRent;
import com.ironhack.activityservice.model.viewModel.PoolRentViewModel;
import com.ironhack.activityservice.service.PoolRentService;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class PoolRentControllerTest {

    @Autowired
    private PoolRentController poolRentController;

    @MockBean
    private PoolRentService poolRentService;

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


    @BeforeEach
    void setUp() {
        mockMvc =  MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        poolRent = new PoolRent(1L, 2, 1, 1);
        poolRentList.add(poolRent);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAllPoolRents() throws Exception {
        when(poolRentController.findAllPoolRents()).thenReturn(poolRentList);
        mockMvc.perform(get("/activities/pool-rents")
                .header("Authorization","admin"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void findPoolRentById() throws Exception {
        when(poolRentController.findPoolRentById(1L)).thenReturn(poolRent);
        mockMvc.perform(get("/activities/pool-rents/"+1L)
                .header("Authorization","admin"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void filterPoolRentByUserId() throws Exception {
        when(poolRentController.filterPoolRentByUserId(1L)).thenReturn(list);
        mockMvc.perform(get("/activities/pool-rents/filter/"+1L)
                .header("Authorization","admin"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void createPoolRent() throws Exception {
        PoolRentViewModel poolRentViewModel = new PoolRentViewModel();
        poolRentViewModel.setFloatiesNum(3);
        poolRentViewModel.setRoomId(1);
        poolRentViewModel.setTowelNum(3);
        poolRentViewModel.setUserId(1L);

        when(poolRentController.createPoolRent(poolRentViewModel)).thenReturn(poolRent);
        mockMvc.perform(post("/activities/pool-rents")
                .header("Authorization","admin")
                .content(objectMapper.writeValueAsString(poolRentViewModel))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void updateFloatiesNum() throws Exception {
        UpdateFloatiesNumDto updateFloatiesNumDto = new UpdateFloatiesNumDto(1L, 5);
        mockMvc.perform(patch("/activities/pool-rents/update-floaties")
                .header("Authorization","admin")
                .content(objectMapper.writeValueAsString(updateFloatiesNumDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void updateTowelNum() throws Exception {
        UpdateTowelNumDto updateTowelNumDto = new UpdateTowelNumDto(1L, 4);
        mockMvc.perform(patch("/activities/pool-rents/update-towels")
                .header("Authorization","admin")
                .content(objectMapper.writeValueAsString(updateTowelNumDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void removePoolRent() throws Exception {
        mockMvc.perform(delete("/activities/pool-rents/"+1L)
                .header("Authorization","admin"))
                .andExpect(status().is2xxSuccessful());
    }
}
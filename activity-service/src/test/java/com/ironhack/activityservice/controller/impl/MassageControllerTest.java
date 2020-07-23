package com.ironhack.activityservice.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.activityservice.client.InvoiceClient;
import com.ironhack.activityservice.client.RoomClient;
import com.ironhack.activityservice.client.UserClient;
import com.ironhack.activityservice.dto.UpdateMassageTypeDto;
import com.ironhack.activityservice.model.entities.Massage;
import com.ironhack.activityservice.model.enums.MassageType;
import com.ironhack.activityservice.model.viewModel.MassageViewModel;
import com.ironhack.activityservice.service.MassageService;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class MassageControllerTest {

    @Autowired
    private MassageController massageController;

    @MockBean
    private MassageService massageService;

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
    private List<Massage> massageList = new ArrayList<Massage>();
    List<Object[]> list = new ArrayList<Object[]>();


    @BeforeEach
    void setUp() {
        mockMvc =  MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        massage = new Massage(1L, 2, MassageType.AROMATHERAPY, LocalDateTime.now());
        massageList.add(massage);
    }


    @AfterEach
    void tearDown() {
    }

    @Test
    void findAllMassages() throws Exception {
        when(massageController.findAllMassages()).thenReturn(massageList);
        mockMvc.perform(get("/activities/massages")
                .header("Authorization","admin"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void findMassageById() throws Exception {
        when(massageController.findMassageById(1L)).thenReturn(massage);
        mockMvc.perform(get("/activities/massages/"+1L)
                .header("Authorization","admin"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void filterMassageByUserId() throws Exception {
        when(massageController.filterMassageByUserId(1L)).thenReturn(list);
        mockMvc.perform(get("/activities/massages/filter/"+1L)
                .header("Authorization","admin"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void createMassageAppointment() throws Exception {
        MassageViewModel massageViewModel = new MassageViewModel();
        massageViewModel.setMassageType(MassageType.AROMATHERAPY);
        massageViewModel.setRoomId(2);
        massageViewModel.setUserId(1L);
        when(massageController.createMassageAppointment(massageViewModel)).thenReturn(massage);
        mockMvc.perform(post("/activities/massages")
                .header("Authorization","admin")
                .content(objectMapper.writeValueAsString(massageViewModel))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void changeMassageType() throws Exception {
        UpdateMassageTypeDto updateMassageTypeDto = new UpdateMassageTypeDto(1L , MassageType.COUPLES);
        mockMvc.perform(patch("/activities/massages")
                .header("Authorization","admin")
                .content(objectMapper.writeValueAsString(updateMassageTypeDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void deleteMassageAppointment() throws Exception {
        mockMvc.perform(delete("/activities/massages/"+1L)
                .header("Authorization","admin"))
                .andExpect(status().is2xxSuccessful());
    }
}
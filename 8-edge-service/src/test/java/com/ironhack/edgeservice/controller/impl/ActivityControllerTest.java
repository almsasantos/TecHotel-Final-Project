package com.ironhack.edgeservice.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.edgeservice.client.*;
import com.ironhack.edgeservice.dto.*;
import com.ironhack.edgeservice.model.entities.activities.Massage;
import com.ironhack.edgeservice.model.entities.activities.PoolRent;
import com.ironhack.edgeservice.model.entities.activities.RoomFood;
import com.ironhack.edgeservice.model.entities.invoices.Invoice;
import com.ironhack.edgeservice.model.entities.reservations.RoomReservation;
import com.ironhack.edgeservice.model.entities.rooms.RegularRoom;
import com.ironhack.edgeservice.model.entities.users.Basic;
import com.ironhack.edgeservice.model.enums.DrinkMenu;
import com.ironhack.edgeservice.model.enums.FoodMenu;
import com.ironhack.edgeservice.model.enums.InvoiceType;
import com.ironhack.edgeservice.model.enums.MassageType;
import com.ironhack.edgeservice.model.viewModel.MassageViewModel;
import com.ironhack.edgeservice.model.viewModel.PoolRentViewModel;
import com.ironhack.edgeservice.model.viewModel.RoomFoodViewModel;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ActivityControllerTest {
    @Autowired
    ActivityController activityController;

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
    private Basic basic;
    private RegularRoom regularRoom;
    private RoomReservation roomReservation;
    private Invoice invoice;
    private Massage massage;
    private RoomFood roomFood;
    private PoolRent poolRent;
    private List<Massage> massageList = new ArrayList<Massage>();
    private List<RoomFood> roomFoodList = new ArrayList<RoomFood>();
    private List<PoolRent> poolRentList = new ArrayList<PoolRent>();
    List<Object[]> list = new ArrayList<Object[]>();

    @BeforeEach
    void setUp() {
        mockMvc =  MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        invoice = new Invoice(1L, "Ana Martins", 2, InvoiceType.ROOM_FOOD_SERVICE, new BigDecimal("9.99"));
        massage = new Massage(1L, 2, MassageType.AROMATHERAPY, LocalDateTime.now());
        massageList.add(massage);
        poolRent = new PoolRent(1L, 2, 1, 1);
        poolRentList.add(poolRent);
        roomFood = new RoomFood(1L, 1, FoodMenu.BEEF_STROGANOFF, DrinkMenu.BEER);
        roomFoodList.add(roomFood);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAllMassages() throws Exception {
        when(activityClient.findAllMassages()).thenReturn(massageList);
        mockMvc.perform(get("/activities/massages")
                .header("Authorization","admin"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void findMassageById() throws Exception {
        when(activityClient.findMassageById(1L)).thenReturn(massage);
        mockMvc.perform(get("/activities/massages/"+1L)
                .header("Authorization","admin"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void filterMassageByUserId() throws Exception {
        when(activityClient.filterMassageByUserId(1L)).thenReturn(list);
        mockMvc.perform(get("/activities/massages/filter/"+1L)
                .header("Authorization","admin"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void createMassageAppointment() throws Exception {
        MassageViewModel massageViewModel = new MassageViewModel();
        massageViewModel.setMassageType(MassageType.AROMATHERAPY);
        massageViewModel.setRoomId(2);
        massageViewModel.setUserId(1L);
        when(activityClient.createMassageAppointment(massageViewModel)).thenReturn(massage);
        mockMvc.perform(post("/activities/massages")
                .header("Authorization","admin")
                .content(objectMapper.writeValueAsString(massageViewModel))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void changeMassageType() throws Exception {
        UpdateMassageTypeDto updateMassageTypeDto = new UpdateMassageTypeDto(1L , MassageType.COUPLES);
        mockMvc.perform(patch("/activities/massages")
                .header("Authorization","admin")
                .content(objectMapper.writeValueAsString(updateMassageTypeDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void deleteMassageAppointment() throws Exception {
        mockMvc.perform(delete("/activities/massages/"+1L)
                .header("Authorization","admin"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void findAllRoomFood() throws Exception {
        when(activityClient.findAllRoomFood()).thenReturn(roomFoodList);
        mockMvc.perform(get("/activities/room-food-services")
                .header("Authorization","admin"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void findRoomFoodById() throws Exception {
        when(activityClient.findRoomFoodById(1L)).thenReturn(roomFood);
        mockMvc.perform(get("/activities/room-food-services/"+1L)
                .header("Authorization","admin"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void filterRoomFoodByUserId() throws Exception  {
        when(activityClient.filterRoomFoodByUserId(1L)).thenReturn(list);
        mockMvc.perform(get("/activities/room-food-services/filter/"+1L)
                .header("Authorization","admin"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void createRoomFoodRequest() throws Exception {
        RoomFoodViewModel roomFoodViewModel = new RoomFoodViewModel();
        roomFoodViewModel.setDrinkMenu(DrinkMenu.CAPPUCCINO);
        roomFoodViewModel.setFoodMenu(FoodMenu.FRESH_SALMON);
        roomFoodViewModel.setRoomId(2);
        roomFoodViewModel.setUserId(1L);

        when(activityClient.createRoomFoodRequest(roomFoodViewModel)).thenReturn(roomFood);
        mockMvc.perform(post("/activities/room-food-services")
                .header("Authorization","admin")
                .content(objectMapper.writeValueAsString(roomFoodViewModel))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void deliverRoomFood() throws Exception {
        mockMvc.perform(patch("/activities/room-food-services/deliver/"+1L)
                .header("Authorization","admin"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void updateFoodMenu() throws Exception {
        UpdateRoomFoodMenuDto updateRoomFoodMenuDto = new UpdateRoomFoodMenuDto(1L, FoodMenu.BEEF_STROGANOFF);
        mockMvc.perform(patch("/activities/room-food-services/update-food")
                .header("Authorization","admin")
                .content(objectMapper.writeValueAsString(updateRoomFoodMenuDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void updateDrinkMenu() throws Exception {
        UpdateDrinkMenuDto updateDrinkMenuDto = new UpdateDrinkMenuDto(1L, DrinkMenu.BEER);
        mockMvc.perform(patch("/activities/room-food-services/update-drink")
                .header("Authorization","admin")
                .content(objectMapper.writeValueAsString(updateDrinkMenuDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void removeRoomFoodRequest() throws Exception {
        mockMvc.perform(delete("/activities/room-food-services/"+1L)
                .header("Authorization","admin"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void findAllPoolRents() throws Exception {
        when(activityClient.findAllPoolRents()).thenReturn(poolRentList);
        mockMvc.perform(get("/activities/pool-rents")
                .header("Authorization","admin"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void findPoolRentById() throws Exception {
        when(activityClient.findPoolRentById(1L)).thenReturn(poolRent);
        mockMvc.perform(get("/activities/pool-rents/"+1L)
                .header("Authorization","admin"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void filterPoolRentByUserId() throws Exception {
        when(activityClient.filterPoolRentByUserId(1L)).thenReturn(list);
        mockMvc.perform(get("/activities/pool-rents/filter/"+1L)
                .header("Authorization","admin"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void createPoolRent() throws Exception {
        PoolRentViewModel poolRentViewModel = new PoolRentViewModel();
        poolRentViewModel.setFloatiesNum(3);
        poolRentViewModel.setRoomId(1);
        poolRentViewModel.setTowelNum(3);
        poolRentViewModel.setUserId(1L);

        when(activityClient.createPoolRent(poolRentViewModel)).thenReturn(poolRent);
        mockMvc.perform(post("/activities/pool-rents")
                .header("Authorization","admin")
                .content(objectMapper.writeValueAsString(poolRentViewModel))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void updateFloatiesNum() throws Exception {
        UpdateFloatiesNumDto updateFloatiesNumDto = new UpdateFloatiesNumDto(1L, 5);
        mockMvc.perform(patch("/activities/pool-rents/update-floaties")
                .header("Authorization","admin")
                .content(objectMapper.writeValueAsString(updateFloatiesNumDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void updateTowelNum() throws Exception {
        UpdateTowelNumDto updateTowelNumDto = new UpdateTowelNumDto(1L, 4);
        mockMvc.perform(patch("/activities/pool-rents/update-towels")
                .header("Authorization","admin")
                .content(objectMapper.writeValueAsString(updateTowelNumDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void removePoolRent() throws Exception {
        mockMvc.perform(delete("/activities/pool-rents/"+1L)
                .header("Authorization","admin"))
                .andExpect(status().is4xxClientError());
    }
}
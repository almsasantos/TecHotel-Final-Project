package com.ironhack.activityservice.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.activityservice.client.InvoiceClient;
import com.ironhack.activityservice.client.RoomClient;
import com.ironhack.activityservice.client.UserClient;
import com.ironhack.activityservice.dto.UpdateDrinkMenuDto;
import com.ironhack.activityservice.dto.UpdateRoomFoodMenuDto;
import com.ironhack.activityservice.model.entities.RoomFood;
import com.ironhack.activityservice.model.enums.DrinkMenu;
import com.ironhack.activityservice.model.enums.FoodMenu;
import com.ironhack.activityservice.model.viewModel.RoomFoodViewModel;
import com.ironhack.activityservice.service.RoomFoodService;
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
class RoomFoodControllerTest {
    @Autowired
    private RoomFoodController roomFoodController;

    @MockBean
    private RoomFoodService roomFoodService;

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
    private RoomFood roomFood;
    List<RoomFood> roomFoodList = new ArrayList<RoomFood>();
    List<Object[]> list = new ArrayList<Object[]>();


    @BeforeEach
    void setUp() {
        mockMvc =  MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        roomFood = new RoomFood(1L, 1, FoodMenu.BEEF_STROGANOFF, DrinkMenu.BEER);
        roomFoodList.add(roomFood);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAllRoomFood() throws Exception {
        when(roomFoodController.findAllRoomFood()).thenReturn(roomFoodList);
        mockMvc.perform(get("/activities/room-food-services")
                .header("Authorization","admin"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void findRoomFoodById() throws Exception {
        when(roomFoodController.findRoomFoodById(1L)).thenReturn(roomFood);
        mockMvc.perform(get("/activities/room-food-services/"+1L)
                .header("Authorization","admin"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void filterRoomFoodByUserId() throws Exception {
        when(roomFoodController.filterRoomFoodByUserId(1L)).thenReturn(list);
        mockMvc.perform(get("/activities/room-food-services/filter/"+1L)
                .header("Authorization","admin"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void createRoomFoodRequest() throws Exception {
        RoomFoodViewModel roomFoodViewModel = new RoomFoodViewModel();
        roomFoodViewModel.setDrinkMenu(DrinkMenu.CAPPUCCINO);
        roomFoodViewModel.setFoodMenu(FoodMenu.FRESH_SALMON);
        roomFoodViewModel.setRoomId(2);
        roomFoodViewModel.setUserId(1L);

        when(roomFoodController.createRoomFoodRequest(roomFoodViewModel)).thenReturn(roomFood);
        mockMvc.perform(post("/activities/room-food-services")
                .header("Authorization","admin")
                .content(objectMapper.writeValueAsString(roomFoodViewModel))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void deliverRoomFood() throws Exception {
        mockMvc.perform(patch("/activities/room-food-services/deliver/"+1L)
                .header("Authorization","admin"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void updateFoodMenu() throws Exception {
        UpdateRoomFoodMenuDto updateRoomFoodMenuDto = new UpdateRoomFoodMenuDto(1L, FoodMenu.BEEF_STROGANOFF);
        mockMvc.perform(patch("/activities/room-food-services/update-food")
                .header("Authorization","admin")
                .content(objectMapper.writeValueAsString(updateRoomFoodMenuDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void updateDrinkMenu() throws Exception {
        UpdateDrinkMenuDto updateDrinkMenuDto = new UpdateDrinkMenuDto(1L, DrinkMenu.BEER);
        mockMvc.perform(patch("/activities/room-food-services/update-drink")
                .header("Authorization","admin")
                .content(objectMapper.writeValueAsString(updateDrinkMenuDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void removeRoomFoodRequest() throws Exception {
        mockMvc.perform(delete("/activities/room-food-services/"+1L)
                .header("Authorization","admin"))
                .andExpect(status().is2xxSuccessful());
    }
}
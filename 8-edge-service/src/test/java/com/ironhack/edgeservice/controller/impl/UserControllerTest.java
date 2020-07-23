package com.ironhack.edgeservice.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.edgeservice.client.*;
import com.ironhack.edgeservice.model.classes.Account;
import com.ironhack.edgeservice.model.classes.Address;
import com.ironhack.edgeservice.model.classes.Money;
import com.ironhack.edgeservice.model.entities.users.Admin;
import com.ironhack.edgeservice.model.entities.users.Basic;
import com.ironhack.edgeservice.model.entities.users.Premium;
import com.ironhack.edgeservice.model.enums.RoomType;
import com.ironhack.edgeservice.model.viewModel.BasicAndPremiumViewModel;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class UserControllerTest {
    @Autowired
    UserController userController;

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
    private Address address;
    private LocalDate birthDate = LocalDate.of(1995, 8, 19);
    private Admin admin;
    private List<Admin> adminList = new ArrayList<Admin>();
    private Account account = new Account();
    private List<Basic> basicList = new ArrayList<Basic>();
    private BasicAndPremiumViewModel basicAndPremiumViewModel;
    private Premium premium;
    private List<Premium> premiumList = new ArrayList<Premium>();

    @BeforeEach
    void setUp() {
        mockMvc =  MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        address = new Address();
        address.setCity("Madrid");
        address.setCountry("Spain");
        address.setPostalCode("8099");
        address.setStreet("Rua Vila");
        account.setBalance(new Money(new BigDecimal("9.99")));
        premium = new Premium("Ana Martins", "almsasantos", "pass", "987654321", "almsasantos@gmail.com", birthDate, address, account);
        premiumList.add(premium);
        basic = new Basic("Ana Martins", "alms", "pass", "987654321", "almsasantos@gmail.com", birthDate, address, account);
        basicList.add(basic);
        admin = new Admin("Ana Santos", "admin", "pass");
        adminList.add(admin);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createNewAdmin() throws Exception {
        when(userClient.createNewAdmin(admin)).thenReturn(admin);
        mockMvc.perform(post("/users/admins")
                .content(objectMapper.writeValueAsString(admin))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void findAllBasicUser() throws Exception {
        when(userClient.findAllBasicUser()).thenReturn(basicList);
        mockMvc.perform(get("/users/basics")
                .header("Authorization","admin"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void findBasicUserById() throws Exception {
        when(userClient.findBasicUserById(1L)).thenReturn(basic);
        mockMvc.perform(get("/users/basics/"+1L)
                .header("Authorization","admin"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void createBasicUser() throws Exception {
        basicAndPremiumViewModel = new BasicAndPremiumViewModel();
        basicAndPremiumViewModel.setBalance(new BigDecimal("9.99"));
        basicAndPremiumViewModel.setCity("Madrid");
        basicAndPremiumViewModel.setCountry("Spain");
        basicAndPremiumViewModel.setPostalCode("8099");
        basicAndPremiumViewModel.setStreet("Rua Vila");
        basicAndPremiumViewModel.setEmail("almsasantos@gmail.com");
        basicAndPremiumViewModel.setPhoneNumber("987654321");
        basicAndPremiumViewModel.setPassword("pass");
        basicAndPremiumViewModel.setUsername("alms");
        basicAndPremiumViewModel.setName("Ana Martins");

        when(userClient.createBasicUser(basicAndPremiumViewModel)).thenReturn(basic);
        mockMvc.perform(post("/users/basics")
                .content(objectMapper.writeValueAsString(basicAndPremiumViewModel))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void updateBasicRoomId() throws Exception {
        RoomReservationViewModel roomReservationViewModel = new RoomReservationViewModel();
        roomReservationViewModel.setNumberOfNights(1);
        roomReservationViewModel.setRoomId(1);
        roomReservationViewModel.setRoomType(RoomType.REGULAR_ROOM);
        roomReservationViewModel.setUserId(1L);
        roomReservationViewModel.setUserName("Ana Martins");
        reservationClient.newRoomReservation(roomReservationViewModel);

        mockMvc.perform(patch("/users/basics/update-room/"+ 1L+ "/"+1)
                .header("Authorization","admin"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void updateBasicBalance() throws Exception {
        mockMvc.perform(patch("/users/basics/update-balance/"+ 1L+ "/100")
                .header("Authorization","admin"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void updateBasicNumberOfStays() throws Exception {
        mockMvc.perform(patch("/users/basics/update-stays/"+ 1L+ "/"+2)
                .header("Authorization","admin"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void deleteBasicUser() throws Exception {
        mockMvc.perform(delete("/users/basics/"+ 1L)
                .header("Authorization","admin"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void findAllPremiumUsers() throws Exception {
        when(userClient.findAllPremiumUsers()).thenReturn(premiumList);
        mockMvc.perform(get("/users/premiums")
                .header("Authorization","admin"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void findPremiumUserById() throws Exception {
        when(userClient.findPremiumUserById(1L)).thenReturn(premium);
        mockMvc.perform(get("/users/premiums/"+1L)
                .header("Authorization","admin"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void createPremiumUser() throws Exception {
        basicAndPremiumViewModel = new BasicAndPremiumViewModel();
        basicAndPremiumViewModel.setBalance(new BigDecimal("9.99"));
        basicAndPremiumViewModel.setCity("Madrid");
        basicAndPremiumViewModel.setCountry("Spain");
        basicAndPremiumViewModel.setPostalCode("8099");
        basicAndPremiumViewModel.setStreet("Rua Vila");
        basicAndPremiumViewModel.setEmail("almsasantos@gmail.com");
        basicAndPremiumViewModel.setPhoneNumber("987654321");
        basicAndPremiumViewModel.setPassword("pass");
        basicAndPremiumViewModel.setUsername("almsasantos");
        basicAndPremiumViewModel.setName("Ana Martins");

        when(userClient.createPremiumUser(basicAndPremiumViewModel)).thenReturn(premium);
        mockMvc.perform(post("/users/premiums")
                .content(objectMapper.writeValueAsString(basicAndPremiumViewModel))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void updatePremiumRoomId() throws Exception {
        RoomReservationViewModel roomReservationViewModel = new RoomReservationViewModel();
        roomReservationViewModel.setNumberOfNights(1);
        roomReservationViewModel.setRoomId(1);
        roomReservationViewModel.setRoomType(RoomType.REGULAR_ROOM);
        roomReservationViewModel.setUserId(1L);
        roomReservationViewModel.setUserName("Ana Martins");
        reservationClient.newRoomReservation(roomReservationViewModel);

        mockMvc.perform(patch("/users/premiums/update-room/"+ 1L+ "/"+1)
                .header("Authorization","admin"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void updatePremiumBalance() throws Exception {
        mockMvc.perform(patch("/users/premiums/update-balance/"+ 1L+ "/100")
                .header("Authorization","admin"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void updatePremiumNumberOfStays() throws Exception {
        mockMvc.perform(patch("/users/premiums/update-stays/"+ 1L+ "/"+2)
                .header("Authorization","admin"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void deletePremiumUser() throws Exception {
        mockMvc.perform(delete("/users/premiums/"+ 1L)
                .header("Authorization","admin"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void findAdminById() throws Exception {
        when(userClient.findAdminById(1L)).thenReturn(admin);
        mockMvc.perform(get("/users/admins/"+1L)
                .header("Authorization","admin"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void findAllAdmin() throws Exception {
        when(userClient.findAllAdmin()).thenReturn(adminList);
        mockMvc.perform(get("/users/admins")
                .header("Authorization","admin"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void findBasicByUsername() throws Exception {
        when(userClient.findBasicByUsername("almsasantos")).thenReturn(1L);
        mockMvc.perform(get("/users/basics-username/almsasantos")
                .header("Authorization","admin"))
                .andExpect(status().is4xxClientError());

    }


    @Test
    void findPremiumByUsername() throws Exception {
        when(userClient.findPremiumByUsername("alms")).thenReturn(1L);
        mockMvc.perform(get("/users/premiums-username/alms")
                .header("Authorization","admin"))
                .andExpect(status().is4xxClientError());
    }
}
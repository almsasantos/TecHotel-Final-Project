package com.ironhack.invoiceservice.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.invoiceservice.client.ActivityClient;
import com.ironhack.invoiceservice.client.ReservationClient;
import com.ironhack.invoiceservice.client.RoomClient;
import com.ironhack.invoiceservice.client.UserClient;
import com.ironhack.invoiceservice.enums.InvoiceType;
import com.ironhack.invoiceservice.enums.RoomType;
import com.ironhack.invoiceservice.model.entities.Invoice;
import com.ironhack.invoiceservice.model.roomreservations.RoomReservation;
import com.ironhack.invoiceservice.model.users.Basic;
import com.ironhack.invoiceservice.model.viewmodel.BasicAndPremiumViewModel;
import com.ironhack.invoiceservice.model.viewmodel.InvoiceViewModel;
import com.ironhack.invoiceservice.model.viewmodel.RoomReservationViewModel;
import com.ironhack.invoiceservice.service.InvoiceService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class InvoiceControllerTest {
    @Autowired
    private InvoiceController invoiceController;

    @MockBean
    private InvoiceService invoiceService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private ActivityClient activityClient;

    @MockBean
    private ReservationClient reservationClient;

    @MockBean
    private RoomClient roomClient;

    @MockBean
    private UserClient userClient;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    private InvoiceViewModel invoiceViewModel;
    private BasicAndPremiumViewModel basicAndPremiumViewModel;
    private Invoice invoice;
    List<Invoice> list;
    private RoomReservationViewModel roomReservationViewModel;

    @BeforeEach
    void setUp() {
        mockMvc =  MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        basicAndPremiumViewModel = new BasicAndPremiumViewModel();
        basicAndPremiumViewModel.setUsername("alms");
        basicAndPremiumViewModel.setBalance(new BigDecimal("1000"));
        basicAndPremiumViewModel.setBirthDate(LocalDate.of(1995, 9, 19));
        basicAndPremiumViewModel.setCity("Madrid");
        basicAndPremiumViewModel.setCountry("Spain");
        basicAndPremiumViewModel.setEmail("almsassantos@gmail.com");
        basicAndPremiumViewModel.setName("Ana Martins");
        basicAndPremiumViewModel.setPassword("pass");
        basicAndPremiumViewModel.setPhoneNumber("987654321");
        basicAndPremiumViewModel.setStreet("Rua Vila");
        basicAndPremiumViewModel.setPostalCode("1998");
        Basic basic = userClient.createBasicUser(basicAndPremiumViewModel);

        roomReservationViewModel = new RoomReservationViewModel();
        roomReservationViewModel.setNumberOfNights(2);
        roomReservationViewModel.setRoomId(1);
        roomReservationViewModel.setRoomType(RoomType.REGULAR_ROOM);
        roomReservationViewModel.setUserId(1L);
        roomReservationViewModel.setUserName("alms");

        RoomReservation roomReservation = reservationClient.newRoomReservation(roomReservationViewModel);

        invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setUserId(1L);
        invoiceViewModel.setRoomId(1);
        invoiceViewModel.setInvoiceType(InvoiceType.HOTEL_ROOM_RESERVATION);
        invoiceViewModel.setPriceOwed(new BigDecimal("19.99"));

        invoice = new Invoice();
        list = Collections.singletonList(invoice);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAllInvoices() throws Exception {
        when(invoiceController.findAllInvoices()).thenReturn(list);
        mockMvc.perform(get("/invoices"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void createFinalInvoice() throws Exception {
        Invoice invoice1 = new Invoice();
        invoice1.setRoomId(roomReservationViewModel.getRoomId());
        invoice1.setInvoiceType(InvoiceType.END_OF_STAY_AT_THE_HOTEL);
        invoice1.setPriceOwed(new BigDecimal("5.99"));
        invoice1.setUserId(1L);
        invoice1.setName("Ana Martins");
        invoice1.setInvoiceId("1");
        when(invoiceController.createFinalInvoice(1L)).thenReturn(invoice1);
        mockMvc.perform(post("/invoices/"+1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void createInvoiceActivity() throws Exception {
        Invoice invoice1 = new Invoice();
        invoice1.setRoomId(roomReservationViewModel.getRoomId());
        invoice1.setInvoiceType(InvoiceType.POOL_RENT);
        invoice1.setPriceOwed(new BigDecimal("5.99"));
        invoice1.setUserId(1L);
        invoice1.setName("Ana Martins");
        invoice1.setInvoiceId("1");
        when(invoiceController.createInvoiceActivity(invoiceViewModel)).thenReturn(invoice1);
        mockMvc.perform(post("/invoices-activity")
                .content(objectMapper.writeValueAsString(invoiceViewModel))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}
package com.ironhack.edgeservice.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.edgeservice.client.*;
import com.ironhack.edgeservice.model.entities.invoices.Invoice;
import com.ironhack.edgeservice.model.enums.InvoiceType;
import com.ironhack.edgeservice.model.viewModel.InvoiceViewModel;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class InvoiceControllerTest {
    @Autowired
    InvoiceController invoiceController;

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
    private Invoice invoice;
    private List<Invoice> invoiceList = new ArrayList<Invoice>();

    @BeforeEach
    void setUp() {
        mockMvc =  MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        invoice = new Invoice(1L, "Ana Martins", 2, InvoiceType.ROOM_FOOD_SERVICE, new BigDecimal("9.99"));
        invoiceList.add(invoice);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findAllInvoices() throws Exception {
        when(invoiceClient.findAllInvoices()).thenReturn(invoiceList);
        mockMvc.perform(get("/invoices")
                .header("Authorization","admin"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void createFinalInvoice() throws Exception {
        when(invoiceClient.createFinalInvoice(1L)).thenReturn(invoice);
        mockMvc.perform(post("/invoices-final/"+1L)
                .header("Authorization","admin"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void createInvoiceActivity() throws Exception {
        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setInvoiceType(InvoiceType.END_OF_STAY_AT_THE_HOTEL);
        invoiceViewModel.setPriceOwed(new BigDecimal("109"));
        invoiceViewModel.setRoomId(1);
        invoiceViewModel.setUserId(1L);
        when(invoiceClient.createInvoiceActivity(invoiceViewModel)).thenReturn(invoice);
        mockMvc.perform(post("/invoices-activity")
                .header("Authorization","admin")
                .content(objectMapper.writeValueAsString(invoiceViewModel))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
}
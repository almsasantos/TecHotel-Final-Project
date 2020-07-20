package com.ironhack.edgeservice.controller.impl;

import com.ironhack.edgeservice.model.entities.invoices.Invoice;
import com.ironhack.edgeservice.model.viewModel.InvoiceViewModel;
import com.ironhack.edgeservice.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/invoices")
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> findAllInvoices(@RequestHeader(value = "Authorization") String authorizationHeader){
        return invoiceService.findAllInvoices(authorizationHeader);
    }

    @PostMapping("/invoices-final/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice createFinalInvoice(@PathVariable("id") Long userId, @RequestHeader(value = "Authorization") String authorizationHeader){
        return invoiceService.createFinalInvoice(userId, authorizationHeader);
    }

    @PostMapping("/invoices-activity")
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice createInvoiceActivity(@RequestBody @Valid InvoiceViewModel invoiceViewModel, @RequestHeader(value = "Authorization") String authorizationHeader){
        return invoiceService.createInvoiceActivity(invoiceViewModel, authorizationHeader);
    }
}

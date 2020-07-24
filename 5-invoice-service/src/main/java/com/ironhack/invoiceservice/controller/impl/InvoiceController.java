package com.ironhack.invoiceservice.controller.impl;

import com.ironhack.invoiceservice.controller.interfaces.IInvoiceController;
import com.ironhack.invoiceservice.model.entities.Invoice;
import com.ironhack.invoiceservice.model.viewmodel.InvoiceViewModel;
import com.ironhack.invoiceservice.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class InvoiceController implements IInvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    /**
     * Find All Invoices
     * @return a list of invoices
     */
    @GetMapping("/invoices")
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> findAllInvoices(){
        return invoiceService.findAll();
    }

    /**
     * Create a Final Invoice
     * @param userId receives a Long with userId
     * @return an Invoice
     */
    @PostMapping("/invoices-final/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice createFinalInvoice(@PathVariable("id") Long userId){
        return invoiceService.createFinalInvoice(userId);
    }

    /**
     * Create Invoice based on activity
     * @param invoiceViewModel receives an Invoice View Model
     * @return an Invoice created
     */
    @PostMapping("/invoices-activity")
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice createInvoiceActivity(@RequestBody @Valid InvoiceViewModel invoiceViewModel) {
        return invoiceService.createInvoiceActivity(invoiceViewModel);
    }

    @GetMapping("/invoices/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> findInvoiceByUserId(@PathVariable("userId") Long userId){
        return invoiceService.findInvoiceByUserId(userId);
    }
}

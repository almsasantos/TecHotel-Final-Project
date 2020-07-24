package com.ironhack.edgeservice.controller.impl;

import com.ironhack.edgeservice.controller.interfaces.IInvoiceController;
import com.ironhack.edgeservice.model.entities.invoices.Invoice;
import com.ironhack.edgeservice.model.viewModel.InvoiceViewModel;
import com.ironhack.edgeservice.service.InvoiceService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Invoice Controller")
@RestController
@RequestMapping("/")
public class InvoiceController implements IInvoiceController {
    @Autowired
    private InvoiceService invoiceService;
    /**
     * Find All Invoices
     * @return a list of invoices
     */
    @GetMapping("/invoices")
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> findAllInvoices(@RequestHeader(value = "Authorization") String authorizationHeader){
        return invoiceService.findAllInvoices(authorizationHeader);
    }
    /**
     * Create a Final Invoice
     * @param userId receives a Long with userId
     * @return an Invoice
     */
    @PostMapping("/invoices-final/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice createFinalInvoice(@PathVariable("id") Long userId, @RequestHeader(value = "Authorization") String authorizationHeader){
        return invoiceService.createFinalInvoice(userId, authorizationHeader);
    }
    /**
     * Create Invoice based on activity
     * @param invoiceViewModel receives an Invoice View Model
     * @return an Invoice created
     */
    @PostMapping("/invoices-activity")
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice createInvoiceActivity(@RequestBody @Valid InvoiceViewModel invoiceViewModel, @RequestHeader(value = "Authorization") String authorizationHeader){
        return invoiceService.createInvoiceActivity(invoiceViewModel, authorizationHeader);
    }

    @GetMapping("/invoices/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> findInvoiceByUserId(@PathVariable("userId") Long userId,  @RequestHeader(value = "Authorization") String authorizationHeader){
        return invoiceService.findInvoiceByUserId(userId, authorizationHeader);
    }
}

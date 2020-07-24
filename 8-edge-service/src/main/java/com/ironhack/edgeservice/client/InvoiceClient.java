package com.ironhack.edgeservice.client;

import com.ironhack.edgeservice.model.entities.invoices.Invoice;
import com.ironhack.edgeservice.model.viewModel.InvoiceViewModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Invoice Client
 */
@FeignClient(name = "invoice-service")
public interface InvoiceClient {
    /**
     * Find All Invoices
     * @return a list of invoices
     */
    @GetMapping("/invoices")
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> findAllInvoices();
    /**
     * Create a Final Invoice
     * @param userId receives a Long with userId
     * @return an Invoice
     */
    @PostMapping("/invoices-final/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice createFinalInvoice(@PathVariable("id") Long userId);
    /**
     * Create Invoice based on activity
     * @param invoiceViewModel receives an Invoice View Model
     * @return an Invoice created
     */
    @PostMapping("/invoices-activity")
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice createInvoiceActivity(@RequestBody @Valid InvoiceViewModel invoiceViewModel);

    @GetMapping("/invoices/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<Invoice> findInvoiceByUserId(@PathVariable("userId") Long userId);
}

package com.ironhack.edgeservice.controller.interfaces;

import com.ironhack.edgeservice.model.entities.invoices.Invoice;
import com.ironhack.edgeservice.model.viewModel.InvoiceViewModel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.validation.Valid;
import java.util.List;

/**
 * Invoice Controller Interface
 */
public interface IInvoiceController {
    /**
     * Find All Invoices
     * @return a list of invoices
     */
    public List<Invoice> findAllInvoices(@RequestHeader(value = "Authorization") String authorizationHeader);
    /**
     * Create a Final Invoice
     * @param userId receives a Long with userId
     * @return an Invoice
     */
    public Invoice createFinalInvoice(@PathVariable("id") Long userId, @RequestHeader(value = "Authorization") String authorizationHeader);
    /**
     * Create Invoice based on activity
     * @param invoiceViewModel receives an Invoice View Model
     * @return an Invoice created
     */
    public Invoice createInvoiceActivity(@RequestBody @Valid InvoiceViewModel invoiceViewModel, @RequestHeader(value = "Authorization") String authorizationHeader);
}

package com.ironhack.invoiceservice.controller.interfaces;

import com.ironhack.invoiceservice.model.entities.Invoice;
import com.ironhack.invoiceservice.model.viewmodel.InvoiceViewModel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface IInvoiceController {
    /**
     * Find All Invoices
     * @return a list of invoices
     */
    public List<Invoice> findAllInvoices();
    /**
     * Create a Final Invoice
     * @param userId receives a Long with userId
     * @return an Invoice
     */
    public Invoice createFinalInvoice(@PathVariable("id") Long userId);
    /**
     * Create Invoice based on activity
     * @param invoiceViewModel receives an Invoice View Model
     * @return an Invoice created
     */
    public Invoice createInvoiceActivity(@RequestBody @Valid InvoiceViewModel invoiceViewModel);

    public List<Invoice> findInvoiceByUserId(@PathVariable("userId") Long userId);
}

package com.ironhack.invoiceservice.controller.interfaces;

import com.ironhack.invoiceservice.model.entities.Invoice;
import com.ironhack.invoiceservice.model.viewmodel.InvoiceViewModel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface IInvoiceController {
    public List<Invoice> findAllInvoices();

    public Invoice createFinalInvoice(@PathVariable("id") Long userId);

    public Invoice createInvoiceActivity(@RequestBody @Valid InvoiceViewModel invoiceViewModel);
}

package com.ironhack.reservationservice.client;

import com.ironhack.reservationservice.model.invoices.Invoice;
import com.ironhack.reservationservice.model.viewmodel.InvoiceViewModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

@FeignClient(name = "invoice-service")
public interface InvoiceClient {
    @PostMapping("/invoices-activity")
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice createInvoiceActivity(@RequestBody @Valid InvoiceViewModel invoiceViewModel);
}

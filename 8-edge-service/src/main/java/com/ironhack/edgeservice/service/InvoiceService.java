package com.ironhack.edgeservice.service;

import com.ironhack.edgeservice.client.InvoiceClient;
import com.ironhack.edgeservice.model.entities.invoices.Invoice;
import com.ironhack.edgeservice.model.viewModel.InvoiceViewModel;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceClient invoiceClient;

    @Autowired
    private SecurityService securityService;

    private static final Logger LOGGER = LogManager.getLogger(InvoiceService.class);

    /**
     * Find All Invoices
     * @return a list of invoices
     */
    @HystrixCommand(fallbackMethod = "findAllInvoicesFail", ignoreExceptions = ResponseStatusException.class)
    public List<Invoice> findAllInvoices(String authorizationHeader){
        LOGGER.info("Find All Invoices");
        securityService.isUser(authorizationHeader);
        return invoiceClient.findAllInvoices(); }

    /**
     * Create a Final Invoice
     * @param userId receives a Long with userId
     * @return an Invoice
     */
    @HystrixCommand(fallbackMethod = "createFinalInvoiceFail", ignoreExceptions = ResponseStatusException.class)
    public Invoice createFinalInvoice(Long userId, String authorizationHeader){
        LOGGER.info("Create new Invoice from End of Stay");
        securityService.isUser(authorizationHeader);
        return invoiceClient.createFinalInvoice(userId); }

    /**
     * Create Invoice based on activity
     * @param invoiceViewModel receives an Invoice View Model
     * @return an Invoice created
     */
    @HystrixCommand(fallbackMethod = "createInvoiceActivityFail", ignoreExceptions = ResponseStatusException.class)
    public Invoice createInvoiceActivity(InvoiceViewModel invoiceViewModel, String authorizationHeader){
        LOGGER.info("Create new Invoice from Activity");
        securityService.isUser(authorizationHeader);
        return invoiceClient.createInvoiceActivity(invoiceViewModel); }

    public List<Invoice> findAllInvoicesFail(String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to find all Invoices");
        return null; }

    public Invoice createFinalInvoiceFail(Long userId, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to create new Invoice from End of Stay");
        return null; }

    public Invoice createInvoiceActivityFail(InvoiceViewModel invoiceViewModel, String authorizationHeader){
        LOGGER.warn("[WARN] It wasn't possible to create new Invoice from Activity");
        return null; }
}

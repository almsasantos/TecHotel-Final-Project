package com.ironhack.invoiceservice.repository;

import com.ironhack.invoiceservice.model.entities.Invoice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Invoice Repository
 */
@Repository
public interface InvoiceRepository extends MongoRepository<Invoice, Long> {
}

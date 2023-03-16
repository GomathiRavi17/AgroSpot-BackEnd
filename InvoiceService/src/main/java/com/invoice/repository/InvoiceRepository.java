package com.invoice.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.invoice.model.Invoice;

@Repository
public interface InvoiceRepository extends MongoRepository<Invoice,String>{
 
}

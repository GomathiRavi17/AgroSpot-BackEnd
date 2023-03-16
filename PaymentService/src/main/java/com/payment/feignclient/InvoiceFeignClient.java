package com.payment.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.payment.exception.InvoiceNotFoundException;
import com.payment.model.Invoice;

@FeignClient(name="invoice-service",url="http://localhost:1007/invoice")
public interface InvoiceFeignClient {

	@PostMapping("/addInvoice")
	public ResponseEntity<Invoice> addInvoice(@RequestBody Invoice invoice, @RequestHeader("Authorization") String token) throws InvoiceNotFoundException;
}

package com.farmer.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.farmer.exception.InvoiceNotFoundException;
import com.farmer.model.Invoice;

@FeignClient(name = "invoice-service", url = "http://localhost:1007/invoice")
public interface InvoiceFeignClient {
	@GetMapping("/")
	public ResponseEntity<List<Invoice>> showAllInvoice(@RequestHeader("Authorization") String token);

	@GetMapping("/{id}")
	public ResponseEntity<Invoice> showInvoiceById(@PathVariable("id") String id,
			@RequestHeader("Authorization") String token) throws InvoiceNotFoundException;

	@PostMapping("/addInvoice")
	public ResponseEntity<Invoice> addInvoice(@RequestBody Invoice invoice,
			@RequestHeader("Authorization") String token) throws InvoiceNotFoundException;

	@PutMapping("/updateInvoice")
	public ResponseEntity<Invoice> updateInvoice(@RequestBody Invoice invoice,
			@RequestHeader("Authorization") String token) throws InvoiceNotFoundException;

	@DeleteMapping("/deleteInvoice/{id}")
	public ResponseEntity<String> deleteInvoice(@PathVariable("id") String id,@RequestHeader("Authorization") String token) throws InvoiceNotFoundException;
}

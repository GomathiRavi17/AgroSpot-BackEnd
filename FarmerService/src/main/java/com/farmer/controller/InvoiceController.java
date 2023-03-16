package com.farmer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.farmer.exception.InvoiceNotFoundException;
import com.farmer.feignclient.InvoiceFeignClient;
import com.farmer.model.Invoice;

@RestController
@RequestMapping("/farmer")
public class InvoiceController {

	@Autowired
	private InvoiceFeignClient farmerFeignClient;

	@GetMapping("/invoice")
	public ResponseEntity<List<Invoice>> showAllInvoices(@RequestHeader("Authorization") String token) {
		return farmerFeignClient.showAllInvoice(token);
	}

	@GetMapping("/invoice/{id}")
	public ResponseEntity<Invoice> showAInvoiceById(@PathVariable("id") String id,
			@RequestHeader("Authorization") String token) throws InvoiceNotFoundException {
		return farmerFeignClient.showInvoiceById(id, token);
	}
}

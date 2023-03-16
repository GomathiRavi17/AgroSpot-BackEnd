package com.dealer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.dealer.exception.InvoiceNotFoundException;
import com.dealer.feignclient.InvoiceFeignClient;
import com.dealer.model.Invoice;

import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/dealer")
public class DealerInvoiceController {

	@Autowired
	private InvoiceFeignClient farmerFeignClient;

	@GetMapping("/invoice")
	public ResponseEntity<List<Invoice>> showAllInvoices(@RequestHeader("Authorization") String token) {
		return farmerFeignClient.showAllInvoice(token);
	}

	@PostMapping("/addInvoice")
	public ResponseEntity<Invoice> addInvoice(@RequestBody Invoice f, @RequestHeader("Authorization") String token) throws InvoiceNotFoundException {
		return farmerFeignClient.addInvoice(f, token);
	}

	@GetMapping("/invoice/{id}")
	public ResponseEntity<Invoice> showInvoiceById(@PathVariable("id") String id,@RequestHeader("Authorization") String token) throws InvoiceNotFoundException {
		return farmerFeignClient.showInvoiceById(id, token);
	}


}

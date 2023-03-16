package com.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.admin.exception.InvoiceNotFoundException;
import com.admin.feignClient.InvoiceFeignClient;
import com.admin.model.Invoice;

@RestController
@RequestMapping("/admin")
public class AdminInvoiceController {

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

	@PutMapping("/updateInvoice")
	public ResponseEntity<Invoice> updateInvoice(@RequestBody Invoice farmer, @RequestHeader("Authorization") String token) throws InvoiceNotFoundException {
		return farmerFeignClient.updateInvoice(farmer, token);
	}

	@DeleteMapping("/deleteInvoice/{id}")
	public ResponseEntity<String> deleteInvoice(@PathVariable("id") String id, @RequestHeader("Authorization") String token) throws InvoiceNotFoundException {
		return farmerFeignClient.deleteInvoice(id, token);
	}

}

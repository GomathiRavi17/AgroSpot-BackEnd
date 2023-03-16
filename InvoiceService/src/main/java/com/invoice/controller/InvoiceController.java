package com.invoice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.invoice.exception.InvoiceNotFoundException;
import com.invoice.model.Invoice;
import com.invoice.service.InvoiceAuthService;
import com.invoice.service.InvoiceServiceImpl;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

	@Autowired
	private InvoiceServiceImpl service;
	
	@Autowired
	private InvoiceAuthService authService;

	Logger log = LoggerFactory.getLogger(InvoiceController.class);

	@GetMapping("/")
	public ResponseEntity<List<Invoice>> showAllInvoice(@RequestHeader("Authorization") String token) {
		try {
			if (authService.isSessionValid(token)) {
				List<Invoice> invoices = service.showAllInvoice();
				if (invoices.isEmpty()) {
					return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
				}
				log.debug("Invoices are {}", invoices);
				return new ResponseEntity<>(invoices, HttpStatus.OK);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Invoice> showInvoiceById(@PathVariable("id") String id,@RequestHeader("Authorization") String token) throws InvoiceNotFoundException {
		try {
			if (authService.isSessionValid(token)) {
		Invoice invoice = service.showInvoiceById(id);
		if (invoice != null) {
			log.debug("Invoice : {}", invoice);
			return new ResponseEntity<>(invoice, HttpStatus.OK);
		} else
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}

	}

	@PostMapping("/addInvoice")
	public ResponseEntity<Invoice> addInvoice(@RequestBody Invoice invoice,@RequestHeader("Authorization") String token) throws InvoiceNotFoundException {
		try {
			if (authService.isSessionValid(token)) {
		Invoice i = service.addInvoice(invoice);
		if (i != null) {
			log.debug("Invoice : {}", i);
			return new ResponseEntity<>(i, HttpStatus.CREATED);
		}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}
	}

	@PutMapping("/updateInvoice")
	public ResponseEntity<Invoice> updateInvoice(@RequestBody Invoice invoice,@RequestHeader("Authorization") String token) throws InvoiceNotFoundException {
		try {
			if (authService.isSessionValid(token)) {
		Invoice i = service.updateInvoice(invoice);
		if (i != null) {
			log.debug("Invoice : {}", i);
			return new ResponseEntity<>(i, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}
	}

	@DeleteMapping("/deleteInvoice/{id}")
	public ResponseEntity<String> deleteInvoice(@PathVariable("id") String id,@RequestHeader("Authorization") String token) throws InvoiceNotFoundException {
		try {
			if (authService.isSessionValid(token)) {
		service.deleteInvoice(id);
		return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}
	}
}

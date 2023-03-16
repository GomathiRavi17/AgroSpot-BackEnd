package com.invoice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.invoice.controller.InvoiceController;
import com.invoice.exception.InvoiceNotFoundException;
import com.invoice.model.CropDetails;
import com.invoice.model.Invoice;
import com.invoice.service.InvoiceAuthService;
import com.invoice.service.InvoiceServiceImpl;

@SpringBootTest
public class InvoiceControllerTest {

	@Autowired
	private InvoiceController controller;

	@MockBean
	private InvoiceServiceImpl invoiceService;

	@MockBean
	private InvoiceAuthService authService;

	String token = "token";

	List<CropDetails> crops;

	@Test
	public void showAllInvoicesControllerTest() {

		List<Invoice> invoice = new ArrayList<>();

		Invoice i = new Invoice();

		i.setId("1");
		i.setDate(LocalDate.now());
		i.setQuantity(1);
		i.setDid("1");
		i.setTransId("123");
		i.setCrops(crops);
		i.setTotPrice("20");

		invoice.add(i);

		when(invoiceService.showAllInvoice()).thenReturn(invoice);
		when(authService.isSessionValid("token")).thenReturn(true);
		assertEquals(1, controller.showAllInvoice(token).getBody().size());
	}

	@Test
	public void ShowInvoiceByIdControllerTest() throws InvoiceNotFoundException {
		Invoice i = new Invoice();

		i.setId("1");
		i.setDate(LocalDate.now());
		i.setQuantity(1);
		i.setDid("1");
		i.setTransId("123");
		i.setCrops(crops);
		i.setTotPrice("20");
		
		when(invoiceService.showInvoiceById("1")).thenReturn(i);
		when(authService.isSessionValid("token")).thenReturn(true);
		assertEquals(i, controller.showInvoiceById("1", token).getBody());
	}

	@Test
	public void addInvoiceControllerTest() throws InvoiceNotFoundException {
		Invoice i = new Invoice();

		i.setId("1");
		i.setDate(LocalDate.now());
		i.setQuantity(1);
		i.setDid("1");
		i.setTransId("123");
		i.setCrops(crops);
		i.setTotPrice("20");

		when(invoiceService.addInvoice(i)).thenReturn(i);
		when(authService.isSessionValid("token")).thenReturn(true);
		assertEquals(i, controller.addInvoice(i, token).getBody());
	}

	@Test
	public void updateInvoiceControllerTest() throws InvoiceNotFoundException {
		Invoice i = new Invoice();

		i.setId("1");
		i.setDate(LocalDate.now());
		i.setQuantity(1);
		i.setDid("1");
		i.setTransId("123");
		i.setCrops(crops);
		i.setTotPrice("20");

		when(invoiceService.updateInvoice(i)).thenReturn(i);
		when(authService.isSessionValid("token")).thenReturn(true);
		assertEquals(i, controller.updateInvoice(i, token).getBody());
	}

	@Test
	public void deleteInvoiceControllerTest() throws InvoiceNotFoundException {
		Invoice i = new Invoice();

		i.setId("1");
		i.setDate(LocalDate.now());
		i.setQuantity(1);
		i.setDid("1");
		i.setTransId("123");
		i.setCrops(crops);
		i.setTotPrice("20");

		when(invoiceService.deleteInvoice("1")).thenReturn("Deleted Successfully");
		when(authService.isSessionValid("token")).thenReturn(true);
		assertEquals("Deleted Successfully", controller.deleteInvoice("1", token).getBody());
	}

}

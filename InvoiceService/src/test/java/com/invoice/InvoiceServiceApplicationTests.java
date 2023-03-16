package com.invoice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.invoice.exception.InvoiceNotFoundException;
import com.invoice.model.CropDetails;
import com.invoice.model.Invoice;
import com.invoice.repository.InvoiceRepository;
import com.invoice.service.InvoiceServiceImpl;

@SpringBootTest
class InvoiceServiceApplicationTests {

	@Autowired
	private InvoiceServiceImpl service;

	@MockBean
	private InvoiceRepository invoiceRepo;

	List<CropDetails> crops;

	@Test
	public void ShowAllInvoicesTest() {
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

		when(invoiceRepo.findAll()).thenReturn(invoice);
		assertEquals(1, service.showAllInvoice().size());
	}

	@Test
	public void ShowInvoiceByIdTest() throws InvoiceNotFoundException {
		Invoice i = new Invoice();

		i.setId("1");
		i.setDate(LocalDate.now());
		i.setQuantity(1);
		i.setDid("1");
		i.setTransId("123");
		i.setCrops(crops);
		i.setTotPrice("20");

		Optional<Invoice> invoice = Optional.of(i);

		when(invoiceRepo.findById("1")).thenReturn(invoice);
		assertEquals(i, service.showInvoiceById("1"));
	}

	@Test
	public void addInvoiceTest() throws InvoiceNotFoundException {
		Invoice i = new Invoice();

		i.setId("1");
		i.setDate(LocalDate.now());
		i.setQuantity(1);
		i.setDid("1");
		i.setTransId("123");
		i.setCrops(crops);
		i.setTotPrice("20");

		when(invoiceRepo.insert(i)).thenReturn(i);
		assertEquals(i, service.addInvoice(i));
	}

	@Test
	public void updateInvoiceTest() throws InvoiceNotFoundException {
		Invoice i1 = new Invoice();
		Invoice i2 = new Invoice();

		i1.setId("1");
		i1.setDate(LocalDate.now());
		i1.setQuantity(1);
		i1.setDid("1");
		i1.setTransId("123");
		i1.setCrops(crops);
		i1.setTotPrice("20");

		i2.setId("1");
		i2.setDate(LocalDate.now());
		i2.setQuantity(1);
		i2.setDid("1");
		i2.setTransId("123");
		i2.setCrops(crops);
		i2.setTotPrice("40");

		Optional<Invoice> invoice = Optional.of(i1);

		when(invoiceRepo.findById("1")).thenReturn(invoice);
		when(invoiceRepo.save(i1)).thenReturn(i2);
		assertEquals(i2, service.updateInvoice(i1));
	}

	@Test
	public void deleteInvoiceTest() throws InvoiceNotFoundException {
		Invoice i = new Invoice();

		i.setId("1");
		i.setDate(LocalDate.now());
		i.setQuantity(1);
		i.setDid("1");
		i.setTransId("123");
		i.setCrops(crops);
		i.setTotPrice("20");

		Optional<Invoice> invoice = Optional.of(i);
		when(invoiceRepo.findById("1")).thenReturn(invoice);
		assertEquals("Invoice with the id 1 Deleted Successfully!", service.deleteInvoice("1"));
	}

}

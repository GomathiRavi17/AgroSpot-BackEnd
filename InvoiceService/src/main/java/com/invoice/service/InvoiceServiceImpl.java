package com.invoice.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invoice.exception.InvoiceNotFoundException;
import com.invoice.model.Invoice;
import com.invoice.repository.InvoiceRepository;

@Service
public class InvoiceServiceImpl implements InvoiceService {

	@Autowired
	private InvoiceRepository invoiceRepo;

	Logger log = LoggerFactory.getLogger(InvoiceServiceImpl.class);

	@Override
	public List<Invoice> showAllInvoice() {
		log.info("Show All Invoice Method Started");
		List<Invoice> Invoice = invoiceRepo.findAll();
		log.debug("Invoice are {} ", Invoice);
		log.info("Show All Invoice Method Ended");
		return Invoice;
	}

	@Override
	public Invoice showInvoiceById(String id) throws InvoiceNotFoundException {
		log.info("Show Invoice By Id Method Started");
		return invoiceRepo.findById(id).orElseThrow(
				() -> new InvoiceNotFoundException("Invoice with the id " + id + " Doesn't Exists"));
	}

	@Override
	public Invoice addInvoice(Invoice bankDetails) throws InvoiceNotFoundException {
		log.info("Add invoice Method Started");
		Optional<Invoice> b = invoiceRepo.findById(bankDetails.getId());
		if (!b.isPresent()) {
			log.info("Add Invoice Method Ended");
			return invoiceRepo.insert(bankDetails);
		} else {
			return b.orElseThrow(() -> new InvoiceNotFoundException("Invoice Already Exists"));
		}

	}

	@Override
	public Invoice updateInvoice(Invoice invoice) throws InvoiceNotFoundException {
		log.info("Update Invoice Method Started");
		Optional<Invoice> b = invoiceRepo.findById(invoice.getId());
		if (!b.isPresent())
			return b.orElseThrow(() -> new InvoiceNotFoundException(
					"Invoice with the id " + invoice.getId() + " Doesn't Exists"));
		log.info("Update Invoice Method Ended");
		return invoiceRepo.save(invoice);

	}

	@Override
	public String deleteInvoice(String id) throws InvoiceNotFoundException {
		log.info("Delete Invoice Method Started");
		Optional<Invoice> d = invoiceRepo.findById(id);
		if (!d.isPresent()) {
			d.orElseThrow(() -> new InvoiceNotFoundException("Invoice with the id " + id + " Doesn't Exists"));
		} else {
			invoiceRepo.deleteById(id);
			log.debug("Deleted SuccessFully {}", d);
			log.info("Delete Invoice Method Ended");
		}
		return "Invoice with the id " + id + " Deleted Successfully!";
	}

}

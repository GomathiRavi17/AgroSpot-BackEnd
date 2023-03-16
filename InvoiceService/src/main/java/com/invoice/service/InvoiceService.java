package com.invoice.service;

import java.util.List;

import com.invoice.exception.InvoiceNotFoundException;
import com.invoice.model.Invoice;

public interface InvoiceService {
  public List<Invoice> showAllInvoice();
  public Invoice showInvoiceById(String id) throws InvoiceNotFoundException;
  public Invoice addInvoice(Invoice bankDetails) throws InvoiceNotFoundException;
  public Invoice updateInvoice(Invoice bankDetails) throws InvoiceNotFoundException;
  public String deleteInvoice(String id) throws InvoiceNotFoundException;
}

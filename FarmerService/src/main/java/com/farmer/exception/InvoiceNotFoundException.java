package com.farmer.exception;

@SuppressWarnings("serial")
public class InvoiceNotFoundException extends Exception {

	public InvoiceNotFoundException() {
		super();
	}

	public InvoiceNotFoundException(String message) {
		super(message);
	}

	public InvoiceNotFoundException(Throwable cause) {
		super(cause);
	}

}

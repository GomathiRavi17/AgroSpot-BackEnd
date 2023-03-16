package com.dealer.exception;

@SuppressWarnings("serial")
public class DealerNotFoundException extends Exception {

	public DealerNotFoundException() {
		super();
	}

	public DealerNotFoundException(String message) {
		super(message);
	}

	public DealerNotFoundException(Throwable cause) {
		super(cause);
	}

}

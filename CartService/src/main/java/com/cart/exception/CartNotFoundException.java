package com.cart.exception;

@SuppressWarnings("serial")
public class CartNotFoundException extends Exception {

	public CartNotFoundException() {
		super();
	}

	public CartNotFoundException(String message) {
		super(message);
	}

	public CartNotFoundException(Throwable cause) {
		super(cause);
	}

}

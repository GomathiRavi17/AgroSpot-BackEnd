package com.dealer.exception;

@SuppressWarnings("serial")
public class CartItemNotFoundException extends Exception {

	public CartItemNotFoundException() {
		super();
	}

	public CartItemNotFoundException(String message) {
		super(message);
	}

	public CartItemNotFoundException(Throwable cause) {
		super(cause);
	}

}

package com.dealer.exception;

@SuppressWarnings("serial")
public class CropNotFoundException extends Exception {

	public CropNotFoundException() {
		super();
	}

	public CropNotFoundException(String message) {
		super(message);
	}

	public CropNotFoundException(Throwable cause) {
		super(cause);
	}

}

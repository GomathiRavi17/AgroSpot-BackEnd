package com.invoice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invoice.feignclient.InvoiceAuthClient;
import com.invoice.model.AuthenticationResponse;

@Service
public class InvoiceAuthService {
	@Autowired
	InvoiceAuthClient invoiceAuthClient;

	public boolean isSessionValid(String token) {

		AuthenticationResponse authResponse = invoiceAuthClient.getValidity(token);
		if (authResponse == null) {
			throw new RuntimeException("Auth reponse returned as  NULL");
		}
		String role = authResponse.getRole().substring(5);
		System.out.println(authResponse);
		if(role.equals("FARMER"))
			return true;
		else if(role.equals("DEALER"))
			return true;
		else if(role.equals("ADMIN"))
			return false;
		else
			return false;

	}
}


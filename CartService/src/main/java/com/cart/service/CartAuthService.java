package com.cart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cart.feignclient.CartAuthClient;
import com.cart.model.AuthenticationResponse;

@Service
public class CartAuthService {
	@Autowired
	CartAuthClient cartAuthClient;

	public boolean isSessionValid(String token) {

		AuthenticationResponse authResponse = cartAuthClient.getValidity(token);
		if (authResponse == null) {
			throw new RuntimeException("Auth reponse returned as  NULL");
		}
		String role = authResponse.getRole().substring(5);
		if (role.equals("DEALER"))
			return true;
		else if(role.equals("ADMIN"))
			return true;
		else
			return false;
}
}

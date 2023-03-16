package com.dealer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dealer.feignclient.DealerAuthClient;
import com.dealer.model.AuthenticationResponse;

@Service
public class DealerAuthService {
	@Autowired
	DealerAuthClient dealerAuthClient;

	public boolean isSessionValid(String token) {

		AuthenticationResponse authResponse = dealerAuthClient.getValidity(token);
		if (authResponse == null) {
			throw new RuntimeException("Auth reponse returned as  NULL");
		}
		String role = authResponse.getRole().substring(5);
		if (role.equals("DEALER"))
			return true;
		else
			return false;

	}
}

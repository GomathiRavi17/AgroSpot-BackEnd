package com.farmer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmer.feignclient.FarmerAuthClient;
import com.farmer.model.AuthenticationResponse;

@Service
public class FarmerAdminAuthService {
	@Autowired
	FarmerAuthClient FarmerAuthClient;

	public boolean isSessionValid(String token) {

		AuthenticationResponse authResponse = FarmerAuthClient.getValidity(token);
		if (authResponse == null) {
			throw new RuntimeException("Auth reponse returned as  NULL");
		}
		String role = authResponse.getRole().substring(5);
		System.out.println(authResponse);
		if(role.equals("ADMIN"))
			return true;
		else
			return false;

	}
}

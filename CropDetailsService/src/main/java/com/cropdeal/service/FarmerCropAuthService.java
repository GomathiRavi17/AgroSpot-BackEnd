package com.cropdeal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cropdeal.feignclient.CropAuthClient;
import com.cropdeal.model.AuthenticationResponse;

@Service
public class FarmerCropAuthService {
	@Autowired
	CropAuthClient cropAuthClient;

	public boolean isSessionValid(String token) {

		AuthenticationResponse authResponse = cropAuthClient.getValidity(token);
		if (authResponse == null) {
			throw new RuntimeException("Auth reponse returned as  NULL");
		}
		String role = authResponse.getRole().substring(5);
		System.out.println(authResponse);
		if(role.equals("FARMER"))
			return true;
		else
			return false;

	}
}

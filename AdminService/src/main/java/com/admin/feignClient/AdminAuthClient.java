package com.admin.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.admin.model.AuthenticationResponse;

@FeignClient(name="authorization-service", url="http://localhost:1005/auth")
public interface AdminAuthClient {
	
	@GetMapping("/validate")
	public AuthenticationResponse getValidity(@RequestHeader("Authorization") String token);

}


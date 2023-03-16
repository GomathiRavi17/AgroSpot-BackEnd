package com.payment.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.payment.model.Cart;

@FeignClient(name="dealer-service",url="http://localhost:1002/dealer")
public interface DealerFeignClient {
	@GetMapping("/viewCart")
	public Cart getCart();
}

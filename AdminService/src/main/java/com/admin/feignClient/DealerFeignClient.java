package com.admin.feignClient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.admin.model.Dealer;
import com.admin.exception.DealerNotFoundException;

@FeignClient(name = "dealers-service", url = "http://localhost:1002/dealer")
public interface DealerFeignClient {
 
	@GetMapping("/")
	public ResponseEntity<List<Dealer>> showAllDealers(@RequestHeader("Authorization") String token);
	
	@GetMapping("/{id}")
	public ResponseEntity<Dealer> showDealerById(@PathVariable("id") int id,
			@RequestHeader("Authorization") String token) throws DealerNotFoundException;
	
	@PostMapping("/addDealer")
	public ResponseEntity<Dealer> addDealer(@RequestBody Dealer dealer, @RequestHeader("Authorization") String token)
			throws DealerNotFoundException;
	
	@PutMapping("/updateDealer")
	public ResponseEntity<Dealer> updateDealer(@RequestBody Dealer dealer, @RequestHeader("Authorization") String token)
			throws DealerNotFoundException;
	
	@DeleteMapping("/deleteDealer/{id}")
	public ResponseEntity<String> deleteDealer(@PathVariable("id") int id, @RequestHeader("Authorization") String token)
			throws DealerNotFoundException;
}

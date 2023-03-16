package com.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.admin.exception.DealerNotFoundException;
import com.admin.feignClient.DealerFeignClient;
import com.admin.model.Dealer;

@RestController
@RequestMapping("/admin")
public class AdminDealerController {

	@Autowired
	private DealerFeignClient dealerFeignClient;

	@GetMapping("/dealers")
	public ResponseEntity<List<Dealer>> showAllDealers(@RequestHeader("Authorization") String token) {
		return dealerFeignClient.showAllDealers(token);
	}

	@PostMapping("/addDealer")
	public ResponseEntity<Dealer> addDealer(@RequestBody Dealer d, @RequestHeader("Authorization") String token) throws DealerNotFoundException {
		return dealerFeignClient.addDealer(d,token);
	}

	@GetMapping("/dealer/{id}")
	public ResponseEntity<Dealer> showDealerById(@PathVariable("id") int id,@RequestHeader("Authorization") String token) throws DealerNotFoundException {
		return dealerFeignClient.showDealerById(id,token);
	}

	@PutMapping("/updateDealer")
	public ResponseEntity<Dealer> updateDealer(@RequestBody Dealer dealer,@RequestHeader("Authorization") String token) throws DealerNotFoundException {
		return dealerFeignClient.updateDealer(dealer, token);
	}

	@DeleteMapping("/deleteDealer/{id}")
	public ResponseEntity<String> deleteDealer(@PathVariable("id") int id,@RequestHeader("Authorization") String token) throws DealerNotFoundException {
       return dealerFeignClient.deleteDealer(id,token);
	}

}

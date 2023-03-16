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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.admin.exception.FarmerNotFoundException;
import com.admin.feignClient.FarmerFeignClient;
import com.admin.model.Farmer;

@RestController
@RequestMapping("/admin")
public class AdminFarmerController {

	@Autowired
	private FarmerFeignClient farmerFeignClient;

	@GetMapping("/farmers")
	public ResponseEntity<List<Farmer>> showAllFarmers(@RequestHeader("Authorization") String token) {
		return farmerFeignClient.showAllFarmers(token);
	}

	@PostMapping("/addFarmer")
	public ResponseEntity<Farmer> addFarmer(@RequestBody Farmer f, @RequestHeader("Authorization") String token) throws FarmerNotFoundException {
		return farmerFeignClient.addFarmer(f, token);
	}

	@GetMapping("/farmer/{id}")
	public ResponseEntity<Farmer> showFarmerById(@PathVariable("id") int id,@RequestHeader("Authorization") String token) throws FarmerNotFoundException {
		return farmerFeignClient.showFarmerById(id, token);
	}

	@PutMapping("/updateFarmer")
	public ResponseEntity<Farmer> updateFarmer(@RequestBody Farmer farmer, @RequestHeader("Authorization") String token) throws FarmerNotFoundException {
		return farmerFeignClient.updateFarmer(farmer, token);
	}

	@DeleteMapping("/deleteFarmer/{id}")
	public ResponseEntity<String> deleteFarmer(@PathVariable("id") int id, @RequestHeader("Authorization") String token) throws FarmerNotFoundException {
		return farmerFeignClient.deleteFarmer(id, token);
	}

}

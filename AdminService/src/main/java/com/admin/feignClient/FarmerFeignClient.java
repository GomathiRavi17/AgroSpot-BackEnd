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

import com.admin.exception.FarmerNotFoundException;
import com.admin.model.Farmer;

@FeignClient(name = "farmer-service", url = "http://localhost:1001/farmer")
public interface FarmerFeignClient {

	@GetMapping("/")
	public ResponseEntity<List<Farmer>> showAllFarmers(@RequestHeader("Authorization") String token);

	@GetMapping("/{id}")
	public ResponseEntity<Farmer> showFarmerById(@PathVariable("id") int id,
			@RequestHeader("Authorization") String token) throws FarmerNotFoundException;

	@PostMapping("/addFarmer")
	public ResponseEntity<Farmer> addFarmer(@RequestBody Farmer farmer, @RequestHeader("Authorization") String token)
			throws FarmerNotFoundException;

	@PutMapping("/updateFarmer")
	public ResponseEntity<Farmer> updateFarmer(@RequestBody Farmer farmer, @RequestHeader("Authorization") String token)
			throws FarmerNotFoundException;

	@DeleteMapping("/deleteFarmer/{id}")
	public ResponseEntity<String> deleteFarmer(@PathVariable("id") int id, @RequestHeader("Authorization") String token)
			throws FarmerNotFoundException;

}

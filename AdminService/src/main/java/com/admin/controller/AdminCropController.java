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

import com.admin.exception.CropNotFoundException;
import com.admin.feignClient.CropDetailFeignClient;
import com.admin.model.CropDetails;


@RestController
@RequestMapping("/admin")
public class AdminCropController {

	@Autowired
	private CropDetailFeignClient cropFeignClient;

	@GetMapping("/cropDetail")
	public ResponseEntity<List<CropDetails>> showAllCropDetails(@RequestHeader("Authorization") String token) {
		return cropFeignClient.showAllCropDetails(token);
	}

	@PostMapping("/addCropDetails")
	public ResponseEntity<CropDetails> addCropDetails(@RequestBody CropDetails crop, @RequestHeader("Authorization") String token) throws CropNotFoundException {
		return cropFeignClient.addCropDetails(crop, token);
	}

	@GetMapping("/cropDetails/{id}")
	public ResponseEntity<CropDetails> showCropDetailsById(@PathVariable("id") int id,@RequestHeader("Authorization") String token) throws CropNotFoundException {
		return cropFeignClient.showCropDetailsById(id, token);
	}

	@PutMapping("/updateCropDetails")
	public ResponseEntity<CropDetails> updateFarmer(@RequestBody CropDetails crop, @RequestHeader("Authorization") String token) throws CropNotFoundException {
		return cropFeignClient.updateCropDetails(crop, token);
	}

	@DeleteMapping("/deleteCropDetails/{id}")
	public ResponseEntity<String> deleteCropDetails(@PathVariable("id") int id, @RequestHeader("Authorization") String token) throws CropNotFoundException {
		return cropFeignClient.deleteCropDetails(id, token);
	}

}

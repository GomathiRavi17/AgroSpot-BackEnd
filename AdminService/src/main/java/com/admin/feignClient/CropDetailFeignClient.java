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

import com.admin.exception.CropNotFoundException;
import com.admin.model.CropDetails;

@FeignClient(name = "crop-details-service", url = "http://localhost:1003/cropDetails")
public interface CropDetailFeignClient {
	@GetMapping("/")
	public ResponseEntity<List<CropDetails>> showAllCropDetails(@RequestHeader("Authorization") String token);

	@GetMapping("/{id}")
	public ResponseEntity<CropDetails> showCropDetailsById(@PathVariable("id") int id,
			@RequestHeader("Authorization") String token) throws CropNotFoundException;

	@PostMapping("/addCropDetails")
	public ResponseEntity<CropDetails> addCropDetails(@RequestBody CropDetails cropDetails,
			@RequestHeader("Authorization") String token) throws CropNotFoundException;

	@PutMapping("/updateCropDetails")
	public ResponseEntity<CropDetails> updateCropDetails(@RequestBody CropDetails cropDetails,
			@RequestHeader("Authorization") String token) throws CropNotFoundException;

	@DeleteMapping("/deleteCropDetails/{id}")
	public ResponseEntity<String> deleteCropDetails(@PathVariable("id") int id,
			@RequestHeader("Authorization") String token) throws CropNotFoundException;
}

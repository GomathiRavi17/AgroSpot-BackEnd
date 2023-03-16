package com.dealer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dealer.feignclient.CropDetailFeignClient;
import com.dealer.model.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/dealer")
public class DealerCropController {

	@Autowired
	private CropDetailFeignClient cropClient;

	@GetMapping("/viewCrops")
	public ResponseEntity<List<CropDetails>> viewCropDetails(@RequestHeader("Authorization") String token) {

		return cropClient.showAllCropDetails(token);
	}

}

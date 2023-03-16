package com.cropdeal.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cropdeal.exception.CropNotFoundException;
import com.cropdeal.exception.SequenceException;
import com.cropdeal.model.CropDetails;
import com.cropdeal.service.AdminCropAuthService;
import com.cropdeal.service.CropDetailsServiceImpl;
import com.cropdeal.service.DealerCropAuthService;
import com.cropdeal.service.FarmerCropAuthService;
import com.cropdeal.service.SequenceDao;;

@RestController
@CrossOrigin()
@RequestMapping("/cropDetails")
public class CropDetailsController {

	@Autowired
	private CropDetailsServiceImpl service;

	Logger log = LoggerFactory.getLogger(CropDetailsController.class);

	@Autowired
	private AdminCropAuthService adminAuthService;
	
	@Autowired
	private FarmerCropAuthService farmerAuthService;
	
	@Autowired
	private DealerCropAuthService dealerAuthService;
	
	@Autowired
	private SequenceDao sequenceDao;

	@GetMapping("/")
	public ResponseEntity<List<CropDetails>> showAllCropDetails(
			@RequestHeader("Authorization") String token
			) {
		try {
			if (adminAuthService.isSessionValid(token) || farmerAuthService.isSessionValid(token) || dealerAuthService.isSessionValid(token)) {
				List<CropDetails> cropDetails = service.showAllCropDetails();
				if (cropDetails.isEmpty()) {
					return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
				}
				log.debug("Farmers are {}", cropDetails);
				return new ResponseEntity<>(cropDetails, HttpStatus.OK);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<CropDetails> showCropDetailsById(@PathVariable("id") int id
			,@RequestHeader("Authorization") String token
			) throws CropNotFoundException {
		try {
			if (adminAuthService.isSessionValid(token) || farmerAuthService.isSessionValid(token) || dealerAuthService.isSessionValid(token) ) {
				CropDetails cropDetails = service.showCropById(id);
				if (cropDetails != null) {
					log.debug("Crop Details: {}", cropDetails);
					return new ResponseEntity<>(cropDetails, HttpStatus.OK);
				} else
					return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}
	}

	@PostMapping("/addCropDetails")
	public ResponseEntity<CropDetails> addCropDetails(@Valid @RequestBody CropDetails cropDetails
			, @RequestHeader("Authorization") String token
			) throws CropNotFoundException, SequenceException {
		try {
			if (adminAuthService.isSessionValid(token) || farmerAuthService.isSessionValid(token)) {
		long sequenceid = sequenceDao.getNextSequenceId(cropDetails.getFname());
		System.out.println("*********" + sequenceid + "********");
		cropDetails.setCid(sequenceid);
		CropDetails crop = service.addCropDetails(cropDetails);
		if(crop!=null) {
			log.debug("Crop Details: {}",crop);
			return new ResponseEntity<>(crop, HttpStatus.OK);}
		else
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	  }throw new ResponseStatusException(HttpStatus.FORBIDDEN,"You are Unauthorized!...");}catch(

	ResponseStatusException e)
	{
		throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
	}
	}

	@PutMapping("/updateCropDetails")
	public ResponseEntity<CropDetails> updateCropDetails(@Valid @RequestBody CropDetails cropDetails
			, @RequestHeader("Authorization") String token
			) throws CropNotFoundException {
		try {
			if (adminAuthService.isSessionValid(token) || farmerAuthService.isSessionValid(token)) {
		CropDetails crop = service.updateCropDetails(cropDetails);
		if(crop!=null) {
			log.debug("Crop Details: {}",crop);
			return new ResponseEntity<>(crop, HttpStatus.CREATED);}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	  }throw new ResponseStatusException(HttpStatus.FORBIDDEN,"You are Unauthorized!...");}catch(

	ResponseStatusException e){throw new ResponseStatusException(HttpStatus.FORBIDDEN,"You are Unauthorized!...");
}
	  }

	@DeleteMapping("/deleteCropDetails/{id}")
	public ResponseEntity<String> deleteCropDetails(@PathVariable("id") long id
			, @RequestHeader("Authorization") String token
			) throws CropNotFoundException {
		try {
			if (adminAuthService.isSessionValid(token) || farmerAuthService.isSessionValid(token)) {
		service.deleteCropDetails(id);
		return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
	  }throw new ResponseStatusException(HttpStatus.FORBIDDEN,"You are Unauthorized!...");}catch(

	ResponseStatusException e){throw new ResponseStatusException(HttpStatus.FORBIDDEN,"You are Unauthorized!...");
}
		}

}

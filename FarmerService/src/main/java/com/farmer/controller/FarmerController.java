package com.farmer.controller;

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

import com.farmer.exception.FarmerNotFoundException;
import com.farmer.exception.SequenceException;
import com.farmer.model.Farmer;
import com.farmer.service.FarmerAdminAuthService;
import com.farmer.service.FarmerAuthService;
import com.farmer.service.FarmerServiceImpl;
import com.farmer.service.SequenceDao;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/farmer")
public class FarmerController {

	@Autowired
	private FarmerServiceImpl service;

	Logger log = LoggerFactory.getLogger(FarmerController.class);

	@Autowired
	private FarmerAdminAuthService adminAuthService;

	@Autowired
	private FarmerAuthService authService;

	@Autowired
	private SequenceDao sequenceDao;

	@GetMapping("/")
	public ResponseEntity<List<Farmer>> showAllFarmers(@RequestHeader("Authorization") String token) {
		try {
			if (adminAuthService.isSessionValid(token)) {
				List<Farmer> farmers = service.showAllFarmers();
				if (farmers.isEmpty()) {
					return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
				}
				log.debug("Farmers are {}", farmers);
				return new ResponseEntity<>(farmers, HttpStatus.OK);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Farmer> showFarmerById(@PathVariable("id") int id,
			@RequestHeader("Authorization") String token) throws FarmerNotFoundException {
		try {
			if (authService.isSessionValid(token) || adminAuthService.isSessionValid(token)) {
				Farmer farmer = service.showFarmerById(id);
				if (farmer != null) {
					log.debug("Farmer: {}", farmer);
					return new ResponseEntity<>(farmer, HttpStatus.OK);
				} else
					return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}
	}

	@PostMapping("/addFarmer")
	public ResponseEntity<Farmer> addFarmer(@Valid @RequestBody Farmer farmer,
			@RequestHeader("Authorization") String token) throws FarmerNotFoundException, SequenceException {
		try {
			if (authService.isSessionValid(token) || adminAuthService.isSessionValid(token)) {
				long sequenceid = sequenceDao.getNextSequenceId("hosting");
				System.out.println("*********" + sequenceid + "********");
				farmer.setFid(sequenceid);
				Farmer f = service.addFarmer(farmer);
				if (f != null) {
					log.debug("Farmer: {}", f);
					return new ResponseEntity<>(f, HttpStatus.CREATED);
				}
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}

	}

	@PutMapping("/updateFarmer")
	public ResponseEntity<Farmer> updateFarmer(@Valid @RequestBody Farmer farmer,
			@RequestHeader("Authorization") String token) throws FarmerNotFoundException {
		try {
			if (authService.isSessionValid(token) || adminAuthService.isSessionValid(token)) {
				Farmer f = service.updateFarmer(farmer);
				if (f != null) {
					log.debug("Farmer: {}", f);
					return new ResponseEntity<>(f, HttpStatus.OK);
				}
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}
	}

	@DeleteMapping("/deleteFarmer/{id}")
	public ResponseEntity<String> deleteFarmer(@PathVariable("id") long id,
			@RequestHeader("Authorization") String token) throws FarmerNotFoundException {
		try {
			if (adminAuthService.isSessionValid(token)) {
				service.deleteFarmer(id);
				return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<Farmer> getFarmerByName(@PathVariable("name") String name,
			@RequestHeader("Authorization") String token) throws FarmerNotFoundException {
		try {
			if (authService.isSessionValid(token) || adminAuthService.isSessionValid(token)) {
				Farmer f = service.getFarmerByName(name);
				if (f != null) {
					log.debug("Farmer: {}", f);
					return new ResponseEntity<>(f, HttpStatus.OK);
				}
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}
	}
}

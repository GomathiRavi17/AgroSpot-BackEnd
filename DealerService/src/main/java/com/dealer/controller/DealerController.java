package com.dealer.controller;

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

import com.dealer.exception.DealerNotFoundException;
import com.dealer.exception.SequenceException;
import com.dealer.model.Dealer;
import com.dealer.service.AdminAuthService;
import com.dealer.service.DealerAuthService;
import com.dealer.service.DealerServiceImpl;
import com.dealer.service.SequenceDao;


@CrossOrigin("*")
@RestController
@RequestMapping("/dealer")
public class DealerController {

	@Autowired
	private DealerServiceImpl service;

	@Autowired
	private DealerAuthService authService;
	
	@Autowired
	private AdminAuthService adminAuthService;

	@Autowired
	private SequenceDao sequenceDao;

	Logger log = LoggerFactory.getLogger(DealerController.class);

	@GetMapping("/")
	public ResponseEntity<List<Dealer>> showAllDealers(@RequestHeader("Authorization") String token) {
		try {
			if (adminAuthService.isSessionValid(token)) {
				List<Dealer> dealers = service.showAllDealers();
				if (dealers.isEmpty()) {
					return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
				}
				log.debug("Dealers are {}", dealers);
				return new ResponseEntity<>(dealers, HttpStatus.OK);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Dealer> showDealerById(@PathVariable("id") long id,
			@RequestHeader("Authorization") String token) throws DealerNotFoundException {
		try {
			if (authService.isSessionValid(token) || adminAuthService.isSessionValid(token)) {
				Dealer dealer = service.showDealerById(id);
				if (dealer != null) {
					log.debug("Dealer: {}", dealer);
					return new ResponseEntity<>(dealer, HttpStatus.OK);
				} else
					return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}
	}

	@PostMapping("/addDealer")
	public ResponseEntity<Dealer> addDealer(@Valid @RequestBody Dealer dealer,
			@RequestHeader("Authorization") String token) throws DealerNotFoundException, SequenceException {
		try {
			if (authService.isSessionValid(token) || adminAuthService.isSessionValid(token)) {
				long sequenceid = sequenceDao.getNextSequenceId("dealers");
				System.out.println("*********" + sequenceid + "********");
				dealer.setDid(sequenceid);
				Dealer d = service.addDealer(dealer);
				if (d != null) {
					log.debug("Dealer: {}", d);
					return new ResponseEntity<>(d, HttpStatus.CREATED);
				}
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}
	}

	@PutMapping("/updateDealer")
	public ResponseEntity<Dealer> updateDealer(@Valid @RequestBody Dealer dealer,
			@RequestHeader("Authorization") String token) throws DealerNotFoundException {
		try {
			if (authService.isSessionValid(token) || adminAuthService.isSessionValid(token)) {
				Dealer d = service.updateDealer(dealer);
				if (d != null) {
					log.debug("Crop Details: {}", d);
					return new ResponseEntity<>(d, HttpStatus.OK);
				}
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}

	}

	@DeleteMapping("/deleteDealer/{id}")
	public ResponseEntity<String> deleteDealer(@PathVariable("id") long id,
			@RequestHeader("Authorization") String token) throws DealerNotFoundException {
		try {
			if (authService.isSessionValid(token)) {
				service.deleteDealer(id);
				return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}

	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<Dealer> getFarmerByName(@PathVariable("name") String name,
			@RequestHeader("Authorization") String token) throws DealerNotFoundException {
		try {
			if (authService.isSessionValid(token) || adminAuthService.isSessionValid(token)) {
				Dealer d = service.getDealerByName(name);
				if (d != null) {
					log.debug("Dealer: {}", d);
					return new ResponseEntity<>(d, HttpStatus.OK);
				}
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}
	}

}

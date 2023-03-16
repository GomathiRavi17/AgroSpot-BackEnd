package com.cart.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.server.ResponseStatusException;

import com.cart.exception.CartNotFoundException;
import com.cart.model.Cart;
import com.cart.service.CartAuthService;
import com.cart.service.CartServiceImpl;


@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartServiceImpl service;
	
	@Autowired
	private CartAuthService authService;
	
	Logger log = LoggerFactory.getLogger(CartController.class);
	
	@GetMapping("/")
	public ResponseEntity<List<Cart>> showAllCarts(@RequestHeader("Authorization") String token){
		try {
			if (authService.isSessionValid(token)) {
		List<Cart> cartItems = service.showAllCarts();
		if(cartItems.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
		log.debug("Carts are {}",cartItems);
		return new ResponseEntity<>(cartItems, HttpStatus.OK);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cart> showCartById(@PathVariable("id") int id,@RequestHeader("Authorization") String token) throws CartNotFoundException {
		try {
			if (authService.isSessionValid(token)) {
		Cart order = service.showCartById(id);
		if(order!=null) {
			log.debug("Cart: {}",order);
			return new ResponseEntity<>(order, HttpStatus.OK);}
		else
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}
	}
	
	@PostMapping("/addCart")
	public ResponseEntity<Cart> addCart(@Valid @RequestBody Cart cartItem,@RequestHeader("Authorization") String token) throws CartNotFoundException {
		try {
			if (authService.isSessionValid(token)) {
		Cart o = service.addCart(cartItem);
		if(o!=null) {
			log.debug("Cart: {}",o);
			return new ResponseEntity<>(o, HttpStatus.CREATED);}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}
	}
	
	@PutMapping("/updateCart")
	public ResponseEntity<Cart> updateCart(@Valid @RequestBody Cart order,@RequestHeader("Authorization") String token) throws CartNotFoundException {
		try {
			if (authService.isSessionValid(token)) {
		Cart o = service.updateCart(order);
		if(o!=null) {
			log.debug("Cart: {}",o);
			return new ResponseEntity<>(o, HttpStatus.OK);}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}
		
	}
	
	@DeleteMapping("/deleteCart/{id}")
	public ResponseEntity<String> deleteCart(@PathVariable("id")int id,@RequestHeader("Authorization") String token)throws CartNotFoundException {
		try {
			if (authService.isSessionValid(token)) {
		service.deleteCart(id);
		return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
			}
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are Unauthorized!...");
		}
	}
}

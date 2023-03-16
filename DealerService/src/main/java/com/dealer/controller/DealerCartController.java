package com.dealer.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dealer.model.CropDetails;

@CrossOrigin("*")
@RestController
@RequestMapping("/dealer")
public class DealerCartController {

	@PostMapping("/viewTotalPrice")
	public double calTotalPrice(@RequestBody List<CropDetails> crop) {
		double totPrice = 0.0;
		double price = 0.0;

		for (CropDetails item : crop) {
			price = item.getPrice() * item.getQuantity();
			totPrice = totPrice + price;
		}
		return totPrice;
	}

}

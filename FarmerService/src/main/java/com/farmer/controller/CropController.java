package com.farmer.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmer.exception.CropNotFoundException;
import com.farmer.exception.FarmerNotFoundException;
import com.farmer.exception.SequenceException;
import com.farmer.feignclient.CropDetailFeignClient;
import com.farmer.model.CropDetails;
import com.farmer.model.Farmer;
import com.farmer.service.FarmerServiceImpl;

@CrossOrigin()
@RestController
@RequestMapping("/farmer")
public class CropController {

	@Autowired
	private CropDetailFeignClient cropClient;

	@Autowired
	private FarmerServiceImpl service;

	@PostMapping("/addCrop/{fname}")
	public Farmer addCrop(@PathVariable("fname") String name, @Valid @RequestBody CropDetails crop,
			@RequestHeader("Authorization") String token)
			throws CropNotFoundException, FarmerNotFoundException, SequenceException {
		crop.setFname(name);
		List<CropDetails> posts = service.getFarmerByName(name).getCropDetails();
		if (posts == null) {
			posts = new ArrayList<>();
		}
		CropDetails result = cropClient.addCropDetails(crop, token).getBody();
		posts.add(result);
		Farmer f = service.getFarmerByName(name);
		f.setCropDetails(posts);
		return service.updateFarmer(f);
	}

	@PutMapping("/updateCrop/{fname}")
	public Farmer updateCrop(@PathVariable("fname") String name, @Valid @RequestBody CropDetails crop,
			@RequestHeader("Authorization") String token) throws FarmerNotFoundException, CropNotFoundException {
         List<CropDetails> posts = service.getFarmerByName(name).getCropDetails();
         int index=0;
         for(int i=0; i<posts.size();i++) {
        	 if(posts.get(i).getCid() == crop.getCid()) {
        		 index = i;
        		 break;
        	 }
         }
         posts.set(index, crop);
         Farmer f = service.getFarmerByName(name);
         f.setCropDetails(posts);
         cropClient.updateCropDetails(crop, token);
         return service.updateFarmer(f);
	}
	
	@PutMapping("/deleteCrop/{fname}")
	public Farmer deleteCrop(@PathVariable("fname") String name, @RequestBody CropDetails crop, @RequestHeader("Authorization") String token) throws FarmerNotFoundException, CropNotFoundException {
		 List<CropDetails> posts = service.getFarmerByName(name).getCropDetails();
		 int index=0;
         for(int i=0; i<posts.size();i++) {
        	 if(posts.get(i).getCid() == crop.getCid()) {
        		 index = i;																										
        		 break;
        	 }
         }
         posts.remove(index);
         Farmer f = service.getFarmerByName(name);
         f.setCropDetails(posts);
         cropClient.deleteCropDetails(crop.getCid(), token);
         return service.updateFarmer(f);
	}

}

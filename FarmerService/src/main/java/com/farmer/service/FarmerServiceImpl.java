package com.farmer.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmer.exception.FarmerNotFoundException;
import com.farmer.exception.SequenceException;
import com.farmer.model.Farmer;
import com.farmer.repository.FarmerRepository;

@Service
public class FarmerServiceImpl implements FarmerService {

	@Autowired
	private FarmerRepository farmerRepo;
	
	

	Logger log = LoggerFactory.getLogger(FarmerServiceImpl.class);

	@Override
	public List<Farmer> showAllFarmers() {
		log.info("Show All Farmers Method Started");
		List<Farmer> farmers = farmerRepo.findAll();
		log.debug("Farmers are {} ", farmers);
		log.info("Show All Farmers Method Ended");
		return farmers;
	}

	@Override
	public Farmer showFarmerById(long id) throws FarmerNotFoundException {
		log.info("Show Farmer By Id Method Started");
		return farmerRepo.findById(id).orElseThrow(() -> new FarmerNotFoundException("Farmer with the id "+id + " Doesn't Exists"));
	}

	@Override
	public Farmer addFarmer(Farmer farmer) throws FarmerNotFoundException, SequenceException {
		log.info("Add Farmer Method Started");
		Optional<Farmer> f = farmerRepo.findById(farmer.getFid());
		if (!f.isPresent()) {
			
			
			farmer.setCropDetails(null);
			log.info("Add Farmer Method saved data successfully {} ",farmer);
			return farmerRepo.insert(farmer);}
		else {
			return f.orElseThrow(() -> new FarmerNotFoundException("Farmer Already Exists"));}
	}

	@Override
	public Farmer updateFarmer(Farmer farmer) throws FarmerNotFoundException {
		log.info("Update Farmer Method Started");
		Optional<Farmer> f = farmerRepo.findById(farmer.getFid());
		if (!f.isPresent())
			return f.orElseThrow(() -> new FarmerNotFoundException("Farmer with the id "+farmer.getFid() + " Doesn't Exists"));
		log.info("Update Farmer Method Ended");
		return farmerRepo.save(farmer);
	}

	@Override
	public String deleteFarmer(long id) throws FarmerNotFoundException {
		log.info("Delete Farmer Method Started");
		Optional<Farmer> f = farmerRepo.findById(id);
		if (!f.isPresent()) {
			f.orElseThrow(() -> new FarmerNotFoundException("Farmer with the id "+id+ " Doesn't Exists"));}
		else {
		  farmerRepo.deleteById(id);
		  log.debug("Deleted SuccessFully {}", f);
		  log.info("Delete Farmer Method Ended");}
		return "Farmer with the id "+id+" Deleted Successfully!";
	}

	@Override
	public Farmer getFarmerByName(String name) throws FarmerNotFoundException {
		log.info("Get Farmer By Name Method Started");
		return farmerRepo.findByName(name).orElseThrow(() -> new FarmerNotFoundException("Farmer with the Name "+name + " Doesn't Exists"));
	}

}

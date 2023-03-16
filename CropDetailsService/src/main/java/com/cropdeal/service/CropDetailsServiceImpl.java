package com.cropdeal.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cropdeal.exception.CropNotFoundException;
import com.cropdeal.model.CropDetails;
import com.cropdeal.repository.CropDetailsRepository;


@Service
public class CropDetailsServiceImpl implements CropDetailsService {

	@Autowired
	private CropDetailsRepository cropDetailsRepo;
	
	Logger log = LoggerFactory.getLogger(CropDetailsServiceImpl.class);
	
	@Override
	public List<CropDetails> showAllCropDetails() {
		log.info("Show All Crop Details Method Started");
		List<CropDetails> cropDetails = cropDetailsRepo.findAll();
		log.debug("dealers are {} ", cropDetails);
		log.info("Show All Crop Details Method Ended");
		return cropDetails;
	}

	@Override
	public CropDetails showCropById(long id) throws CropNotFoundException {
		log.info("Show Crop By Id Method Started");
		return cropDetailsRepo.findById(id).orElseThrow(() 
				-> new CropNotFoundException("Crop with the id " + id + " Doesn't Exists"));
		 
	}

	@Override
	public CropDetails addCropDetails(CropDetails cropDetails) throws CropNotFoundException{
		log.info("Add CropDetails Method Started");
		Optional<CropDetails> crop = cropDetailsRepo.findById(cropDetails.getCid());
		if (!crop.isPresent()) {
			log.info("Add CropDetails Method Ended");
			return cropDetailsRepo.insert(cropDetails);
		} else {
			return crop.orElseThrow(() -> new CropNotFoundException("Dealer Already Exists"));
		}
	}

	@Override
	public CropDetails updateCropDetails(CropDetails cropDetails)throws CropNotFoundException {
		log.info("Update CropDetails Method Started");
		Optional<CropDetails> crop = cropDetailsRepo.findById(cropDetails.getCid());
		if (!crop.isPresent())
			return crop.orElseThrow(
					() -> new CropNotFoundException("Farmer with the id " + cropDetails.getCid() + " Doesn't Exists"));
		log.info("Update CropDetails Method Ended");
		return cropDetailsRepo.save(cropDetails);
	}

	@Override
	public String deleteCropDetails(long id)throws CropNotFoundException {
		log.info("Delete Crop Details Method Started");
		Optional<CropDetails> cropDetails = cropDetailsRepo.findById(id);
		if (!cropDetails.isPresent()) {
			cropDetails.orElseThrow(() -> new CropNotFoundException("Dealer with the id " + id + " Doesn't Exists"));
		} else {
			cropDetailsRepo.deleteById(id);
			log.debug("Deleted SuccessFully {}", cropDetails);
			log.info("Delete Dealer Method Ended");
		}
		return "Crop with the id " + id + " Deleted Successfully!";
	}

}

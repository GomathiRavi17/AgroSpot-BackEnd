 package com.cropdeal.service;

import java.util.List;

import com.cropdeal.exception.CropNotFoundException;
import com.cropdeal.model.CropDetails;

public interface CropDetailsService {
	public List<CropDetails> showAllCropDetails()throws CropNotFoundException;
	public CropDetails showCropById(long id) throws CropNotFoundException;
	public CropDetails addCropDetails(CropDetails cropDetails) throws CropNotFoundException;
	public CropDetails updateCropDetails(CropDetails cropDetails)throws CropNotFoundException;
	public String deleteCropDetails(long id) throws CropNotFoundException;
}

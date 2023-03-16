package com.farmer.service;

import java.util.List;


import com.farmer.exception.FarmerNotFoundException;
import com.farmer.exception.SequenceException;
import com.farmer.model.Farmer;


public interface FarmerService{
	public List<Farmer> showAllFarmers();

	public Farmer showFarmerById(long id) throws FarmerNotFoundException;

	public Farmer addFarmer(Farmer farmer) throws FarmerNotFoundException, SequenceException;

	public Farmer updateFarmer(Farmer farmer)throws FarmerNotFoundException;

	public String deleteFarmer(long id) throws FarmerNotFoundException;
	
    public Farmer getFarmerByName(String name) throws FarmerNotFoundException;

}

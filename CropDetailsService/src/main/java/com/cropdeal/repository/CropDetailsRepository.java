package com.cropdeal.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cropdeal.model.CropDetails;

@Repository
public interface CropDetailsRepository extends MongoRepository<CropDetails,Long>
{
	
}

package com.farmer.repository;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.farmer.model.Farmer;

@Repository
public interface FarmerRepository extends MongoRepository<Farmer, Long>{
    public Optional<Farmer> findByName(String name);
}

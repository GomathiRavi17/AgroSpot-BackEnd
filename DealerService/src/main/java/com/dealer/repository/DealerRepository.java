package com.dealer.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.dealer.model.Dealer;


@Repository
public interface DealerRepository extends MongoRepository<Dealer,Long>{
	 public Optional<Dealer> findByName(String name);
}

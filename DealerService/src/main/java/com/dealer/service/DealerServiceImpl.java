package com.dealer.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dealer.exception.DealerNotFoundException;
import com.dealer.model.Dealer;
import com.dealer.repository.DealerRepository;


@Service
public class DealerServiceImpl implements DealerService {

	@Autowired
	private DealerRepository dealerRepo;

	Logger log = LoggerFactory.getLogger(DealerServiceImpl.class);

	@Override
	public List<Dealer> showAllDealers() {
		log.info("Show All Dealers Method Started");
		List<Dealer> dealers = dealerRepo.findAll();
		log.debug("dealers are {} ", dealers);
		log.info("Show All dealers Method Ended");
		return dealers;
	}

	@Override
	public Dealer showDealerById(long id) throws DealerNotFoundException {
		log.info("Show Dealer By Id Method Started");
		return dealerRepo.findById(id)
				.orElseThrow(() -> new DealerNotFoundException("Dealer with the id " + id + " Doesn't Exists"));

	}

	@Override
	public Dealer addDealer(Dealer dealer) throws DealerNotFoundException {
		log.info("Add Dealer Method Started");
		Optional<Dealer> d = dealerRepo.findById(dealer.getDid());
		if (!d.isPresent()) {
			log.info("Add Dealer Method Ended");
			return dealerRepo.insert(dealer);
		} else {
			return d.orElseThrow(() -> new DealerNotFoundException("Dealer Already Exists"));
		}

	}

	@Override
	public Dealer updateDealer(Dealer dealer) throws DealerNotFoundException {
		log.info("Update Dealer Method Started");
		Optional<Dealer> d = dealerRepo.findById(dealer.getDid());
		if (!d.isPresent())
			return d.orElseThrow(
					() -> new DealerNotFoundException("Farmer with the id " + dealer.getDid() + " Doesn't Exists"));
		log.info("Update Dealer Method Ended");
		return dealerRepo.save(dealer);
	}

	@Override
	public String deleteDealer(long id) throws DealerNotFoundException {
		log.info("Delete Dealer Method Started");
		Optional<Dealer> d = dealerRepo.findById(id);
		if (!d.isPresent()) {
			d.orElseThrow(() -> new DealerNotFoundException("Dealer with the id " + id + " Doesn't Exists"));
		} else {
			dealerRepo.deleteById(id);
			log.debug("Deleted SuccessFully {}", d);
			log.info("Delete Dealer Method Ended");
		}
		return "Dealer with the id " + id + " Deleted Successfully!";

	}

	@Override
	public Dealer getDealerByName(String name) throws DealerNotFoundException {
		log.info("Get Dealer By Name Method Started");
		return dealerRepo.findByName(name).orElseThrow(() -> new DealerNotFoundException("Dealer with the Name "+name + " Doesn't Exists"));
	}

}

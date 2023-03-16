package com.dealer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.dealer.exception.DealerNotFoundException;
import com.dealer.model.Address;
import com.dealer.model.Dealer;
import com.dealer.repository.DealerRepository;
import com.dealer.service.DealerServiceImpl;

@SpringBootTest
class DealerServiceApplicationTests {
	@Autowired
	private DealerServiceImpl service;

	@MockBean
	private DealerRepository dealerRepo;

	@Test
	public void ShowAllDealersTest() {
		List<Dealer> dealers = new ArrayList<>();

		Dealer d = new Dealer();

		d.setDid(1);
		d.setName("bhuvi");
		d.setImage("image");
		d.setEmail("bhuvi@gmail.com");
		d.setAddress(new Address("5", "135th Street", "mr nagar", "chennai", "tamil nadu", "123456"));
		d.setContact("12345");
		d.setAbout("abc");

		dealers.add(d);

		when(dealerRepo.findAll()).thenReturn(dealers);
		assertEquals(1, service.showAllDealers().size());
	}

	@Test
	public void ShowDealerByIdTest() throws DealerNotFoundException {
		Dealer d = new Dealer();

		d.setDid(1);
		d.setName("bhuvi");
		d.setImage("image");
		d.setEmail("bhuvi@gmail.com");
		d.setAddress(new Address("5", "135th Street", "mr nagar", "chennai", "tamil nadu", "123456"));
		d.setContact("12345");
		d.setAbout("abc");

		Optional<Dealer> dealer = Optional.of(d);

		when(dealerRepo.findById(1l)).thenReturn(dealer);
		assertEquals(d, service.showDealerById(1));
	}

	@Test
	public void addDealerTest() throws DealerNotFoundException {
		Dealer d = new Dealer();

		d.setDid(1);
		d.setName("bhuvi");
		d.setImage("image");
		d.setEmail("bhuvi@gmail.com");
		d.setAddress(new Address("5", "135th Street", "mr nagar", "chennai", "tamil nadu", "123456"));
		d.setContact("12345");
		d.setAbout("abc");

		when(dealerRepo.insert(d)).thenReturn(d);
		assertEquals(d, service.addDealer(d));
	}

	@Test
	public void updateDealerTest() throws DealerNotFoundException {
		Dealer d1 = new Dealer();
		Dealer d2 = new Dealer();

		d1.setDid(1);
		d1.setName("vasavi");
		d1.setImage("image");
		d1.setEmail("vasavi@gmail.com");
		d1.setAddress(new Address("5", "135th Street", "mr nagar", "chennai", "tamil nadu", "123456"));
		d1.setContact("12345");
		d1.setAbout("abc");

		d2.setDid(1);
		d2.setName("vasavi");
		d2.setImage("image");
		d2.setEmail("vasavi@gmail.com");
		d2.setAddress(new Address("6", "135th Street", "mr nagar", "chennai", "tamil nadu", "123456"));
		d2.setContact("12345");
		d2.setAbout("abc");

		Optional<Dealer> dealer = Optional.of(d1);

		when(dealerRepo.findById(1l)).thenReturn(dealer);
		when(dealerRepo.save(d1)).thenReturn(d2);
		assertEquals(d2, service.updateDealer(d1));
	}

	@Test
	public void deleteDealerTest() throws DealerNotFoundException {
		Dealer d = new Dealer();

		d.setDid(1);
		d.setName("vasavi");
		d.setImage("image");
		d.setEmail("vasavi@gmail.com");
		d.setAddress(new Address("5", "135th Street", "mr nagar", "chennai", "tamil nadu", "123456"));
		d.setContact("12345");
		d.setAbout("abc");
		
		Optional<Dealer> dealer = Optional.of(d);
		when(dealerRepo.findById(1l)).thenReturn(dealer);
		assertEquals("Dealer with the id 1 Deleted Successfully!", service.deleteDealer(1));
	}

}

package com.dealer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.dealer.controller.DealerController;
import com.dealer.exception.DealerNotFoundException;
import com.dealer.exception.SequenceException;
import com.dealer.model.Address;
import com.dealer.model.Dealer;
import com.dealer.service.DealerAuthService;
import com.dealer.service.DealerServiceImpl;

@SpringBootTest
public class DealerControllerTest {

	@Autowired
	private DealerController controller;

	@MockBean
	private DealerServiceImpl dealerService;

	@MockBean
	private DealerAuthService authService;

	String token = "token";

	@Test
	public void showAllDealersControllerTest() {

		List<Dealer> dealers = new ArrayList<>();

		Dealer d = new Dealer();

		d.setDid(1);
		d.setName("vasavi");
		d.setImage("image");
		d.setEmail("vasavi@gmail.com");
		d.setAddress(new Address("5", "135th Street", "mr nagar", "chennai", "tamil nadu", "123456"));
		d.setContact("12345");
		d.setAbout("abc");

		dealers.add(d);

		when(dealerService.showAllDealers()).thenReturn(dealers);
		when(authService.isSessionValid("token")).thenReturn(true);
		assertEquals(1, controller.showAllDealers(token).getBody().size());
	}

	@Test
	public void ShowDealerByIdControllerTest() throws DealerNotFoundException {
		Dealer d = new Dealer();
		d.setDid(1);
		d.setName("mona");
		d.setImage("image");
		d.setEmail("mona@gmail.com");
		d.setAddress(new Address("5", "135th Street", "mr nagar", "chennai", "tamil nadu", "123456"));
		d.setContact("12345");
		d.setAbout("abc");

		when(dealerService.showDealerById(1)).thenReturn(d);
		when(authService.isSessionValid("token")).thenReturn(true);
		assertEquals(d, controller.showDealerById(1,token).getBody());
	}

	@Test
	public void addDealerControllerTest() throws DealerNotFoundException, SequenceException {
		Dealer d = new Dealer();

		d.setDid(1);
		d.setName("vasavi");
		d.setImage("image");
		d.setEmail("vasavi@gmail.com");
		d.setAddress(new Address("5", "135th Street", "mr nagar", "chennai", "tamil nadu", "123456"));
		d.setContact("12345");
		d.setAbout("abc");

		when(dealerService.addDealer(d)).thenReturn(d);
		when(authService.isSessionValid("token")).thenReturn(true);
		assertEquals(d, controller.addDealer(d,token).getBody());
	}

	@Test
	public void updateDealerControllerTest() throws DealerNotFoundException {
		Dealer d = new Dealer();

		d.setDid(1);
		d.setName("vasavi");
		d.setImage("image");
		d.setEmail("vasavi@gmail.com");
		d.setAddress(new Address("5", "135th Street", "mr nagar", "chennai", "tamil nadu", "123456"));
		d.setContact("12345");
		d.setAbout("abc");

		when(dealerService.updateDealer(d)).thenReturn(d);
		when(authService.isSessionValid("token")).thenReturn(true);
		assertEquals(d, controller.updateDealer(d,token).getBody());
	}

	@Test
	public void deleteDealerControllerTest() throws DealerNotFoundException {
		Dealer d = new Dealer();

		d.setDid(1);
		d.setName("vasavi");
		d.setImage("image");
		d.setEmail("vasavi@gmail.com");
		d.setAddress(new Address("5", "135th Street", "mr nagar", "chennai", "tamil nadu", "123456"));
		d.setContact("12345");
		d.setAbout("abc");
		

		when(dealerService.deleteDealer(1)).thenReturn("Deleted Successfully");
		when(authService.isSessionValid("token")).thenReturn(true);
		assertEquals("Deleted Successfully", controller.deleteDealer(1,token).getBody());
	}

}

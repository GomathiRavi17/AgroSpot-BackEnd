package com.farmer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.farmer.controller.FarmerController;
import com.farmer.exception.FarmerNotFoundException;
import com.farmer.exception.SequenceException;
import com.farmer.model.Address;
import com.farmer.model.CropDetails;
import com.farmer.model.Farmer;
import com.farmer.service.FarmerAdminAuthService;
import com.farmer.service.FarmerServiceImpl;

@SpringBootTest
public class FarmerControllerTest {

	@Autowired
	private FarmerController controller;

	@MockBean
	private FarmerServiceImpl farmerService;
	
	@MockBean
	private FarmerAdminAuthService authService;

	List<CropDetails> crops = new ArrayList<>();

	String token = "token";

	@Test
	public void showAllFarmersControllerTest() {

		List<Farmer> farmers = new ArrayList<>();

		Farmer f = new Farmer();

		f.setFid(1);
		f.setName("vasavi");
		f.setImage("image");
		f.setEmail("vasavi@gmail.com");
		f.setAddress(new Address("5", "135th Street", "mr nagar", "chennai", "tamil nadu", "123456"));
		f.setContact("12345");
		f.setAbout("abc");
		f.setCropDetails(crops);

		farmers.add(f);

		when(farmerService.showAllFarmers()).thenReturn(farmers);
		when(authService.isSessionValid("token")).thenReturn(true);
		assertEquals(1, controller.showAllFarmers(token).getBody().size());
	}

	@Test
	public void ShowFarmerByIdControllerTest() throws FarmerNotFoundException {
		Farmer f = new Farmer();
		f.setFid(1);
		f.setName("mona");
		f.setImage("image");
		f.setEmail("mona@gmail.com");
		f.setAddress(new Address("5", "135th Street", "mr nagar", "chennai", "tamil nadu", "123456"));
		f.setContact("12345");
		f.setAbout("abc");
		f.setCropDetails(crops);

		when(farmerService.showFarmerById(1)).thenReturn(f);
		when(authService.isSessionValid("token")).thenReturn(true);
		assertEquals(f, controller.showFarmerById(1,token).getBody());
	}

	@Test
	public void addFarmerControllerTest() throws FarmerNotFoundException, SequenceException {
		Farmer f = new Farmer();

		f.setFid(1);
		f.setName("vasavi");
		f.setImage("image");
		f.setEmail("vasavi@gmail.com");
		f.setAddress(new Address("5", "135th Street", "mr nagar", "chennai", "tamil nadu", "123456"));
		f.setContact("12345");
		f.setAbout("abc");
		f.setCropDetails(crops);

		when(farmerService.addFarmer(f)).thenReturn(f);
		when(authService.isSessionValid("token")).thenReturn(true);
		assertEquals(f, controller.addFarmer(f,token).getBody());
	}

	@Test
	public void updateFarmerControllerTest() throws FarmerNotFoundException {
		Farmer f = new Farmer();

		f.setFid(1);
		f.setName("vasavi");
		f.setImage("image");
		f.setEmail("vasavi@gmail.com");
		f.setAddress(new Address("5", "135th Street", "mr nagar", "chennai", "tamil nadu", "123456"));
		f.setContact("12345");
		f.setAbout("abc");
		f.setCropDetails(crops);

		when(farmerService.updateFarmer(f)).thenReturn(f);
		when(authService.isSessionValid("token")).thenReturn(true);
		assertEquals(f, controller.updateFarmer(f,token).getBody());
	}

	@Test
	public void deleteFarmerControllerTest() throws FarmerNotFoundException {
		Farmer f = new Farmer();

		f.setFid(1);
		f.setName("vasavi");
		f.setImage("image");
		f.setEmail("vasavi@gmail.com");
		f.setAddress(new Address("5", "135th Street", "mr nagar", "chennai", "tamil nadu", "123456"));
		f.setContact("12345");
		f.setAbout("abc");
		f.setCropDetails(crops);

		when(farmerService.deleteFarmer(1)).thenReturn("Deleted Successfully");
		when(authService.isSessionValid("token")).thenReturn(true);
		assertEquals("Deleted Successfully", controller.deleteFarmer(1,token).getBody());
	}

}

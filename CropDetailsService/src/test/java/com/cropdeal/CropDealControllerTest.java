package com.cropdeal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cropdeal.controller.CropDetailsController;
import com.cropdeal.exception.CropNotFoundException;
import com.cropdeal.exception.SequenceException;
import com.cropdeal.model.CropDetails;
import com.cropdeal.service.AdminCropAuthService;
import com.cropdeal.service.CropDetailsServiceImpl;

@SpringBootTest
public class CropDealControllerTest {
	@Autowired
	private CropDetailsController controller;

	@MockBean
	private CropDetailsServiceImpl cropService;

	@MockBean
	private AdminCropAuthService authService;

	List<CropDetails> crops = new ArrayList<>();

	String token = "token";

	@Test
	public void showAllCropDetailsControllerTest() {

		List<CropDetails> crops = new ArrayList<>();

		CropDetails c = new CropDetails();

		c.setCid(1);
		c.setFname("somu");
		c.setName("carrot");
		c.setImage("image");
		c.setCropType("vegetable");
		c.setDesc("abc");
		c.setPrice(40.5);
		c.setQuantity(10);
		c.setAvailable(true);
		c.setLocation("chennai");
		c.setContact("12345");

		crops.add(c);

		when(cropService.showAllCropDetails()).thenReturn(crops);
		when(authService.isSessionValid("token")).thenReturn(true);
		assertEquals(1, controller.showAllCropDetails(token).getBody().size());
	}

	@Test
	public void ShowCropDetailsByIdControllerTest() throws CropNotFoundException {
		CropDetails c = new CropDetails();

		c.setCid(1);
		c.setFname("somu");
		c.setName("carrot");
		c.setImage("image");
		c.setCropType("vegetable");
		c.setDesc("abc");
		c.setPrice(40.5);
		c.setQuantity(10);
		c.setAvailable(true);
		c.setLocation("chennai");
		c.setContact("12345");

		when(cropService.showCropById(1)).thenReturn(c);
		when(authService.isSessionValid("token")).thenReturn(true);
		assertEquals(c, controller.showCropDetailsById(1,token).getBody());
	}

	@Test
	public void addCropDetailsControllerTest() throws CropNotFoundException, SequenceException {
		CropDetails c = new CropDetails();

		c.setCid(1);
		c.setFname("somu");
		c.setName("carrot");
		c.setImage("image");
		c.setCropType("vegetable");
		c.setDesc("abc");
		c.setPrice(40.5);
		c.setQuantity(10);
		c.setAvailable(true);
		c.setLocation("chennai");
		c.setContact("12345");

		when(cropService.addCropDetails(c)).thenReturn(c);
		when(authService.isSessionValid("token")).thenReturn(true);
		assertEquals(c, controller.addCropDetails(c,token).getBody());
	}

	@Test
	public void updateCropDetailsControllerTest() throws CropNotFoundException {
		CropDetails c = new CropDetails();

		c.setCid(1);
		c.setFname("somu");
		c.setName("carrot");
		c.setImage("image");
		c.setCropType("vegetable");
		c.setDesc("abc");
		c.setPrice(40.5);
		c.setQuantity(10);
		c.setAvailable(true);
		c.setLocation("chennai");
		c.setContact("12345");

		when(cropService.updateCropDetails(c)).thenReturn(c);
		when(authService.isSessionValid("token")).thenReturn(true);
		assertEquals(c, controller.updateCropDetails(c,token).getBody());
	}

	@Test
	public void deleteCropDetailsControllerTest() throws CropNotFoundException {
		CropDetails c = new CropDetails();

		c.setCid(1);
		c.setFname("somu");
		c.setName("carrot");
		c.setImage("image");
		c.setCropType("vegetable");
		c.setDesc("abc");
		c.setPrice(40.5);
		c.setQuantity(10);
		c.setAvailable(true);
		c.setLocation("chennai");
		c.setContact("12345");

		when(cropService.deleteCropDetails(1)).thenReturn("Deleted Successfully");
		when(authService.isSessionValid("token")).thenReturn(true);
		assertEquals("Deleted Successfully", controller.deleteCropDetails(1,token).getBody());
	}

}

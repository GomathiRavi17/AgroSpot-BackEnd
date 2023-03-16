package com.cropdeal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cropdeal.exception.CropNotFoundException;
import com.cropdeal.model.CropDetails;
import com.cropdeal.repository.CropDetailsRepository;
import com.cropdeal.service.CropDetailsServiceImpl;

@SpringBootTest
class CropDetailsApplicationTests {

	@Autowired
	private CropDetailsServiceImpl service;

	@MockBean
	private CropDetailsRepository cropRepo;

	@Test
	public void ShowAllCropDetailsTest() {
		List<CropDetails> cropdetails = new ArrayList<>();

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

		cropdetails.add(c);

		when(cropRepo.findAll()).thenReturn(cropdetails);
		assertEquals(1, service.showAllCropDetails().size());
	}

	@Test
	public void ShowCropDetailsByIdTest() throws CropNotFoundException {
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

		Optional<CropDetails> crop = Optional.of(c);

		when(cropRepo.findById(1l)).thenReturn(crop);
		assertEquals(c, service.showCropById(1));
	}

	@Test
	public void addCropDetailsTest() throws CropNotFoundException {

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

		when(cropRepo.insert(c)).thenReturn(c);
		assertEquals(c, service.addCropDetails(c));
	}

	@Test
	public void updateCropDetailsTest() throws CropNotFoundException {
		CropDetails c1 = new CropDetails();
		CropDetails c2 = new CropDetails();

		c1.setCid(1);
		c1.setFname("somu");
		c1.setName("carrot");
		c1.setImage("image");
		c1.setCropType("vegetable");
		c1.setDesc("abc");
		c1.setPrice(40.5);
		c1.setQuantity(10);
		c1.setAvailable(true);
		c1.setLocation("chennai");
		c1.setContact("12345");

		c2.setCid(1);
		c2.setFname("somu");
		c2.setName("carrot");
		c2.setImage("image");
		c2.setCropType("vegetable");
		c2.setDesc("abcdef");
		c2.setPrice(40.5);
		c2.setQuantity(10);
		c2.setAvailable(true);
		c2.setLocation("chennai");
		c2.setContact("12345");

		Optional<CropDetails> crop = Optional.of(c1);

		when(cropRepo.findById(1l)).thenReturn(crop);
		when(cropRepo.save(c2)).thenReturn(c2);
		assertEquals(c2, service.updateCropDetails(c2));
	}

	@Test
	public void deleteCropDetailsTest() throws CropNotFoundException {
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
		
		Optional<CropDetails> crop = Optional.of(c);
		when(cropRepo.findById(1l)).thenReturn(crop);
		assertEquals("Crop with the id 1 Deleted Successfully!", service.deleteCropDetails(1));
	}

}

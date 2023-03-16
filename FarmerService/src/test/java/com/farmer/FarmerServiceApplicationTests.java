package com.farmer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.farmer.exception.FarmerNotFoundException;
import com.farmer.exception.SequenceException;
import com.farmer.model.Address;
import com.farmer.model.CropDetails;
import com.farmer.model.Farmer;
import com.farmer.repository.FarmerRepository;
import com.farmer.service.FarmerServiceImpl;

@SpringBootTest
class FarmerServiceApplicationTests {

	@Autowired
	private FarmerServiceImpl service;

	@MockBean
	private FarmerRepository farmerRepo;

	List<CropDetails> crops;

	@Test
	public void ShowAllFarmersServiceTest() {
		List<Farmer> farmers = new ArrayList<>();

		Farmer f = new Farmer();

		f.setFid(1);
		f.setName("mona");
		f.setImage("image");
		f.setEmail("mona@gmail.com");
		f.setAddress(new Address("5", "135th Street", "mr nagar", "chennai", "tamil nadu", "123456"));
		f.setContact("12345");
		f.setAbout("abc");
		f.setCropDetails(crops);

		farmers.add(f);

		when(farmerRepo.findAll()).thenReturn(farmers);
		assertEquals(1, service.showAllFarmers().size());
	}

	@Test
	public void ShowFarmerByIdServiceTest() throws FarmerNotFoundException {
		Farmer f = new Farmer();
		f.setFid(1);
		f.setName("mona");
		f.setImage("image");
		f.setEmail("mona@gmail.com");
		f.setAddress(new Address("5", "135th Street", "mr nagar", "chennai", "tamil nadu", "123456"));
		f.setContact("12345");
		f.setAbout("abc");
		f.setCropDetails(crops);

		Optional<Farmer> farmer = Optional.of(f);

		when(farmerRepo.findById(1l)).thenReturn(farmer);
		assertEquals(f, service.showFarmerById(1));
	}

	@Test
	public void addFarmerServiceTest() throws FarmerNotFoundException, SequenceException {
		Farmer f = new Farmer();

		f.setFid(1);
		f.setName("vasavi");
		f.setImage("image");
		f.setEmail("vasavi@gmail.com");
		f.setAddress(new Address("5", "135th Street", "mr nagar", "chennai", "tamil nadu", "123456"));
		f.setContact("12345");
		f.setAbout("abc");
		f.setCropDetails(crops);

		when(farmerRepo.insert(f)).thenReturn(f);
		assertEquals(f, service.addFarmer(f));
	}

	@Test
	public void updateFarmerServiceTest() throws FarmerNotFoundException {
		Farmer f1 = new Farmer();
		Farmer f2 = new Farmer();

		f1.setFid(1);
		f1.setName("vasavi");
		f1.setImage("image");
		f1.setEmail("vasavi@gmail.com");
		f1.setAddress(new Address("5", "135th Street", "mr nagar", "chennai", "tamil nadu", "123456"));
		f1.setContact("12345");
		f1.setAbout("abc");
		f1.setCropDetails(crops);

		f2.setFid(1);
		f2.setName("vasavi");
		f2.setImage("image");
		f2.setEmail("vasavi@gmail.com");
		f2.setAddress(new Address("6", "135th Street", "mr nagar", "chennai", "tamil nadu", "123456"));
		f2.setContact("12345");
		f2.setAbout("abc");
		f2.setCropDetails(crops);

		Optional<Farmer> farmer = Optional.of(f1);

		when(farmerRepo.findById(1l)).thenReturn(farmer);
		when(farmerRepo.save(f2)).thenReturn(f2);
		assertEquals(f2, service.updateFarmer(f2));
	}

	@Test
	public void deleteFarmerServiceTest() throws FarmerNotFoundException {
		Farmer f = new Farmer();

		f.setFid(1);
		f.setName("vasavi");
		f.setImage("image");
		f.setEmail("vasavi@gmail.com");
		f.setAddress(new Address("5", "135th Street", "mr nagar", "chennai", "tamil nadu", "123456"));
		f.setContact("12345");
		f.setAbout("abc");
		f.setCropDetails(crops);

		Optional<Farmer> farmer = Optional.of(f);
		when(farmerRepo.findById(1l)).thenReturn(farmer);
		assertEquals("Farmer with the id 1 Deleted Successfully!", service.deleteFarmer(1));
	}

}

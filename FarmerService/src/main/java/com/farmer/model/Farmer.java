package com.farmer.model;

import java.util.List;

//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.SequenceGenerator;
//import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "farmers")
public class Farmer {
	
//	@SequenceGenerator(name="id_generator", sequenceName = "id_seq", initialValue = 6,allocationSize=50)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
	@Id
//	@NotNull(message = "Id is Required")
//	@Min(value = 1, message = "Id should not be Zero and Negative value")
	private long fid;
	@NotBlank(message = "Name should not be Empty")
	private String name;
	@NotBlank(message = "Image Url should not be Empty")
	private String image;
	@NotBlank(message = "Email should not be Empty")
	private String email;
	@NotNull(message = "Address should not be Empty")
	private Address address;
	@NotBlank(message = "Contact should not be Empty")
	private String contact;
	@NotBlank(message = "About should not be Empty")
	private String about;
	private List<CropDetails> cropDetails;

	public Farmer() {
		super();
	}

	public Farmer(long fid, String name, String image, String email, Address address, String contact, String about,
			List<CropDetails> cropDetails) {
		super();
		this.fid = fid;
		this.name = name;
		this.image = image;
		this.email = email;
		this.address = address;
		this.contact = contact;
		this.about = about;
		this.cropDetails = cropDetails;
	}

	public long getFid() {
		return fid;
	}

	public void setFid(long l) {
		this.fid = l;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public List<CropDetails> getCropDetails() {
		return cropDetails;
	}

	public void setCropDetails(List<CropDetails> cropDetails) {
		this.cropDetails = cropDetails;
	}

	@Override
	public String toString() {
		return "Farmer [fid=" + fid + ", name=" + name + ", image=" + image + ", email=" + email + ", address="
				+ address + ", contact=" + contact + ", about=" + about + ", cropDetails=" + cropDetails + "]";
	}

}

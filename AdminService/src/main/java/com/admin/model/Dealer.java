package com.admin.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "dealers")
public class Dealer {
	@Id
	@NotNull(message="Id is Required")
	@Min(value=1, message="Id should not be Zero and Negative value")
	private int did;
	@NotBlank(message="Name should not be Empty")
	private String name;
	@NotBlank(message="Image Url should not be Empty")
	private String image;
	@NotBlank(message="Email should not be Empty")
	private String email;
	@NotNull(message="Address should not be Empty")
	private Address address;
	@NotBlank(message="Contact should not be Empty")
	private String contact;
	@NotBlank(message="About should not be Empty")
	private String about;

	public Dealer() {
		super();
	}

	public Dealer(int did, String name, String image, String email, Address address, String contact, String about) {
		super();
		this.did = did;
		this.name = name;
		this.image = image;
		this.email = email;
		this.address = address;
		this.contact = contact;
		this.about = about;
	}

	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
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

	@Override
	public String toString() {
		return "Dealer [did=" + did + ", name=" + name + ", image=" + image + ", email=" + email + ", address="
				+ address + ", contact=" + contact + ", about=" + about + "]";
	}

}

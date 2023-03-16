package com.invoice.model;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="cropdetails")
public class CropDetails {
	@Id
	@NotNull(message="Crop Id is Required")
	@Min(value=1, message="Id should not be Zero and Negative value")
	private int cid;
	@NotNull(message="Farmer Id is Required")
	@Min(value=1, message="Id should not be Zero and Negative value")
	private int fid;
	@NotBlank(message="Name should not be Empty")
	private String name;
	@NotBlank(message="Image Url should not be Empty")
	private String image;
	@NotBlank(message="Crop Type should not be Empty")
	private String cropType;
	@NotBlank(message="Description should not be Empty")
	private String desc;
	@NotNull(message="Price is Required")
	private double price;
	@NotNull(message="Quantity is Required")
	private int quantity;
	@AssertTrue
	private boolean available;
	@NotBlank(message=" should not be Empty")
	private String location;
	@NotBlank(message="Contact should not be Empty")
	private String contact;

	public CropDetails() {
		super();
	}

	public CropDetails(int cid, int fid, String name, String image, String cropType, String desc, double price,
			int quantity, boolean available, String location, String contact) {
		super();
		this.cid = cid;
		this.fid = fid;
		this.name = name;
		this.image = image;
		this.cropType = cropType;
		this.desc = desc;
		this.price = price;
		this.quantity = quantity;
		this.available = available;
		this.location = location;
		this.contact = contact;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
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

	public String getCropType() {
		return cropType;
	}

	public void setCropType(String cropType) {
		this.cropType = cropType;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Override
	public String toString() {
		return "CropDetails [cid=" + cid + ", fid=" + fid + ", name=" + name + ", image=" + image + ", cropType="
				+ cropType + ", desc=" + desc + ", price=" + price + ", quantity=" + quantity + ", available="
				+ available + ", location=" + location + ", contact=" + contact + "]";
	}

	

}

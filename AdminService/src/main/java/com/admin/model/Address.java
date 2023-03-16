package com.admin.model;

public class Address {
	private String houseNo;
	private String street;
	private String city;
	private String district;
	private String state;
	private String pincode;

	public Address() {
		super();
	}

	public Address(String houseNo, String street, String city, String district, String state, String pincode) {
		super();
		this.houseNo = houseNo;
		this.street = street;
		this.city = city;
		this.district = district;
		this.state = state;
		this.pincode = pincode;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String country) {
		this.pincode = country;
	}

	@Override
	public String toString() {
		return "Address [houseNo=" + houseNo + ", street=" + street + ", city=" + city + ", district=" + district
				+ ", state=" + state + ", pincode=" + pincode + "]";
	}

}

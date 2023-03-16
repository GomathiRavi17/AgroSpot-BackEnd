package com.farmer.model;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "invoice")
public class Invoice {
	@Id
	@NotBlank(message = "Invoice Number is Required")
	@Min(value = 1, message = "Invoice Number should not be Zero and Negative value")
	private String id;
	@NotBlank(message = "Dealer Id is Required")
	private String did;
	@NotNull(message = "Date is Required")
	private LocalDate date;
	@NotBlank(message = "Total Price is Required")
	private String totPrice;
	@NotNull(message = "Quantity is Required")
	private int quantity;
	@NotBlank(message = "Transaction Id is Required")
	private String transId;
	@NotNull(message = "Crop List is Required")
	private List<CropDetails> crops;

	public Invoice() {
		super();
	}

	public Invoice(String id, String did, LocalDate date, String totPrice, int quantity, String transId,
			List<CropDetails> crops) {
		super();
		this.id = id;
		this.did = did;
		this.date = date;
		this.totPrice = totPrice;
		this.quantity = quantity;
		this.transId = transId;
		this.crops = crops;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public List<CropDetails> getCrops() {
		return crops;
	}

	public void setCrops(List<CropDetails> crops) {
		this.crops = crops;
	}

	public String getTotPrice() {
		return totPrice;
	}

	public void setTotPrice(String totPrice) {
		this.totPrice = totPrice;
	}

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	@Override
	public String toString() {
		return "Invoice [id=" + id + ", did=" + did + ", date=" + date + ", totPrice=" + totPrice + ", quantity="
				+ quantity + ", transId=" + transId + ", crops=" + crops + "]";
	}

}

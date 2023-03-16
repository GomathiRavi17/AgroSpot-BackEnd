package com.payment.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CartItem {
	@NotNull(message = " Id is Required")
	@Min(value = 1, message = "Id should not be Zero and Negative value")
	private int itemId;
	@NotNull(message = "Dealer Id is Required")
	@Min(value = 1, message = "Id should not be Zero and Negative value")
	private int did;
	@NotNull(message = "Dealer Id is Required")
	private CropDetails crop;
	@NotNull(message = "Quantity is Required")
	@Min(value = 1, message = "Quantity should not be Zero and Negative value")
	private int qty;

	public CartItem() {
		super();
	}

	public CartItem(int itemId, int did, CropDetails crop, int qty) {
		super();
		this.itemId = itemId;
		this.did = did;
		this.crop = crop;
		this.qty = qty;
	}

	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
	}

	public CropDetails getCrop() {
		return crop;
	}

	public void setCrop(CropDetails crop) {
		this.crop = crop;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	@Override
	public String toString() {
		return "CartItem [itemId=" + itemId + ", did=" + did + ", crop=" + crop + ", qty=" + qty + "]";
	}

}

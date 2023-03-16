package com.cart.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cart")
public class Cart {
	@Id
	private int did;
	private List<CartItem> items = new ArrayList<>();

	public Cart() {
		super();
	}

	public Cart(int did) {
		super();
		this.did = did;
	}

	public void addItem(CartItem cartItem) {
		items.add(cartItem);
	}

	public List<CartItem> getItem() {
		return items;
	}

	public void removeItem(CartItem item) {
		items.remove(item);
	}

	public void clearItems() {
		items.clear();
	}

	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
	}
}

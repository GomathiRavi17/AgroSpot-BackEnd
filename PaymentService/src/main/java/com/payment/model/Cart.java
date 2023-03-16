package com.payment.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	private List<CartItem> items = new ArrayList<>();

	public Cart() {
		super();
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
}

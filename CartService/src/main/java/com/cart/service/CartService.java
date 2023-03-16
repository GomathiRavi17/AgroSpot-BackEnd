package com.cart.service;

import java.util.List;
import com.cart.exception.CartNotFoundException;
import com.cart.model.Cart;


public interface CartService {
  public List<Cart> showAllCarts();
  public Cart showCartById(int id) throws CartNotFoundException;
  public Cart addCart(Cart cart) throws CartNotFoundException;
  public Cart updateCart(Cart cart) throws CartNotFoundException;
  public String deleteCart(int id) throws CartNotFoundException;
}

package com.cart.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cart.exception.CartNotFoundException;
import com.cart.model.Cart;
import com.cart.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepo;

	Logger log = LoggerFactory.getLogger(CartServiceImpl.class);

	@Override
	public List<Cart> showAllCarts() {
		log.info("Show All Carts Method Started");
		List<Cart> carts = cartRepo.findAll();
		log.debug("Farmers are {} ", carts);
		log.info("Show All Carts Method Ended");
		return carts;
	}

	@Override
	public Cart showCartById(int id) throws CartNotFoundException {
		log.info("Show Cart By Id Method Started");
		return cartRepo.findById(id)
				.orElseThrow(() -> new CartNotFoundException("Cart with the id " + id + " Doesn't Exists"));
	}

	@Override
	public Cart addCart(Cart cart) throws CartNotFoundException {
		log.info("Add Cart Method Started");
		Optional<Cart> c = cartRepo.findById(cart.getDid());
		if (!c.isPresent()) {
			return cartRepo.insert(cart);
		} else {
			log.info("Add Cart Method Ended");
			return c.orElseThrow(() -> new CartNotFoundException("Cart Already Exists"));
		}
	}

	@Override
	public Cart updateCart(Cart Cart) throws CartNotFoundException {
		log.info("Update Cart Method Started");
		Optional<Cart> c = cartRepo.findById(Cart.getDid());
		if (!c.isPresent())
			return c.orElseThrow(
					() -> new CartNotFoundException("Cart with the id " + Cart.getDid() + " Doesn't Exists"));
		log.info("Update Cart Method Ended");
		return cartRepo.save(Cart);
	}

	@Override
	public String deleteCart(int id) throws CartNotFoundException {
		log.info("Delete Cart Method Started");
		Optional<Cart> c = cartRepo.findById(id);
		if (!c.isPresent()) {
			c.orElseThrow(() -> new CartNotFoundException("Cart with the id " + id + " Doesn't Exists"));
		} else {
			cartRepo.deleteById(id);
			log.debug("Deleted SuccessFully {}", c);
			log.info("Delete Cart Method Ended");
		}
		return "Cart with the id " + id + " Deleted Successfully!";
	}

}

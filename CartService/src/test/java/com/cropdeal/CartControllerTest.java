//package com.cropdeal;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import com.cart.CartServiceApplication;
//import com.cart.controller.CartController;
//import com.cart.exception.CartNotFoundException;
//import com.cart.model.CartItem;
//import com.cart.model.CropDetails;
//import com.cart.service.CartAuthService;
//import com.cart.service.CartServiceImpl;
//
//@SpringBootTest(classes=CartServiceApplication.class)
//public class CartControllerTest {
//	@Autowired
//	private CartController controller;
//
//	@MockBean
//	private CartServiceImpl cartService;
//
//	@MockBean
//	private CartAuthService authService;
//
//	List<CartItem> crops = new ArrayList<>();
//
//	String token = "token";
//
//	CartItem item;
//
//	@BeforeEach
//	public void setupAll() {
//		item = new CartItem();
//	}
//
//	@AfterEach
//	public void clear() {
//		item = null;
//	}
//
//	@Test
//	public void showAllCartItemControllerTest() {
//
//		List<CartItem> cartItems = new ArrayList<>();
//
//		CropDetails c = new CropDetails();
//
//		c.setCid(1);
//		c.setFid(1);
//		c.setName("carrot");
//		c.setImage("image");
//		c.setCropType("vegetable");
//		c.setDesc("abc");
//		c.setPrice(40.5);
//		c.setQuantity(10);
//		c.setAvailable(true);
//		c.setLocation("chennai");
//		c.setContact("12345");
//
//		item.setItemId(1);
//		item.setCrop(c);
//		item.setQty(2);
//
//		cartItems.add(item);
//
//		when(cartService.showAllCarts()).thenReturn(cartItems);
//		when(authService.isSessionValid("token")).thenReturn(true);
//		assertEquals(1, controller.showAllCartItems(token).getBody().size());
//	}
//
//	@Test
//	public void ShowCartItemByIdControllerTest() throws CartItemNotFoundException {
//		CropDetails c = new CropDetails();
//
//		c.setCid(1);
//		c.setFid(1);
//		c.setName("carrot");
//		c.setImage("image");
//		c.setCropType("vegetable");
//		c.setDesc("abc");
//		c.setPrice(40.5);
//		c.setQuantity(10);
//		c.setAvailable(true);
//		c.setLocation("chennai");
//		c.setContact("12345");
//
//		item.setDid(1);
//		item.setCrop(c);
//		item.setQty(2);
//
//		when(cartService.showCartItemById(1)).thenReturn(item);
//		when(authService.isSessionValid("token")).thenReturn(true);
//		assertEquals(item, controller.showCartItemById(1, token).getBody());
//	}
//
//	@Test
//	public void addCartItemControllerTest() throws CartItemNotFoundException {
//
//		CropDetails c = new CropDetails();
//
//		c.setCid(1);
//		c.setFid(1);
//		c.setName("carrot");
//		c.setImage("image");
//		c.setCropType("vegetable");
//		c.setDesc("abc");
//		c.setPrice(40.5);
//		c.setQuantity(10);
//		c.setAvailable(true);
//		c.setLocation("chennai");
//		c.setContact("12345");
//
//		item.setDid(1);
//		item.setCrop(c);
//		item.setQty(2);
//
//		when(cartService.addCartItem(item)).thenReturn(item);
//		when(authService.isSessionValid("token")).thenReturn(true);
//		assertEquals(item, controller.addCartItem(item, token).getBody());
//	}
//
//	@Test
//	public void updateCartItemControllerTest() throws CartItemNotFoundException {
//		CropDetails c = new CropDetails();
//
//		c.setCid(1);
//		c.setFid(1);
//		c.setName("carrot");
//		c.setImage("image");
//		c.setCropType("vegetable");
//		c.setDesc("abc");
//		c.setPrice(40.5);
//		c.setQuantity(10);
//		c.setAvailable(true);
//		c.setLocation("chennai");
//		c.setContact("12345");
//
//		item.setDid(1);
//		item.setCrop(c);
//		item.setQty(2);
//
//		when(cartService.updateCart(item)).thenReturn(item);
//		when(authService.isSessionValid("token")).thenReturn(true);
//		assertEquals(item, controller.updateCartItem(item, token).getBody());
//	}
//
//	@Test
//	public void deleteCartItemControllerTest() throws CartItemNotFoundException {
//		CropDetails c = new CropDetails();
//
//		c.setCid(1);
//		c.setFid(1);
//		c.setName("carrot");
//		c.setImage("image");
//		c.setCropType("vegetable");
//		c.setDesc("abc");
//		c.setPrice(40.5);
//		c.setQuantity(10);
//		c.setAvailable(true);
//		c.setLocation("chennai");
//		c.setContact("12345");
//
//		item.setDid(1);
//		item.setCrop(c);
//		item.setQty(2);
//
//		when(cartService.deleteCartItem(1)).thenReturn("Deleted Successfully");
//		when(authService.isSessionValid("token")).thenReturn(true);
//		assertEquals("Deleted Successfully", controller.deleteCartItem(1, token).getBody());
//	}
//
//}

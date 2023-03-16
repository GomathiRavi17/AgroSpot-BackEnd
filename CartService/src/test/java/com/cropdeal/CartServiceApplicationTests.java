//package com.cropdeal;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import com.cart.CartServiceApplication;
//import com.cart.exception.CartItemNotFoundException;
//import com.cart.model.CartItem;
//import com.cart.repository.CartItemRepository;
//import com.cart.service.CartServiceImpl;
//import com.cart.model.CropDetails;
//
//@SpringBootTest(classes = CartServiceApplication.class)
//class CartServiceApplicationTests {
//
//	@Autowired
//	private CartServiceImpl service;
//
//	@MockBean
//	private CartItemRepository cartRepo;
//	
//	List<CartItem> crops;
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
//	public void ShowAllCartItemsServiceTest() {
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
//		item.setDid(1);
//		item.setCrop(c);
//		item.setQty(2);
//
//		cartItems.add(item);
//
//		when(cartRepo.findAll()).thenReturn(cartItems);
//		assertEquals(1, service.showAllCartItems().size());
//	}
//
//	@Test
//	public void ShowCartItemByIdServiceTest() throws CartItemNotFoundException {
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
//		Optional<CartItem> cartItem = Optional.of(item);
//
//		when(cartRepo.findById(1)).thenReturn(cartItem);
//
//		assertEquals(item, service.showCartItemById(1));
//	}
//
//	@Test
//	public void addCartItemServiceTest() throws CartItemNotFoundException {
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
//		Optional<CartItem> cartItem = Optional.of(item);
//
//		when(cartRepo.findById(1)).thenReturn(cartItem);
//
//		when(cartRepo.insert(item)).thenReturn(item);
//
//		assertEquals(item, service.addCartItem(item));
//	}
//
//	@Test
//	public void updateCartItemServiceTest() throws CartItemNotFoundException {
//
//		CartItem item1 = new CartItem();
//		CartItem item2 = new CartItem();
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
//		item1.setDid(1);
//		item1.setCrop(c);
//		item1.setQty(2);
//
//		item2.setDid(1);
//		item2.setCrop(c);
//		item2.setQty(2);
//
//		Optional<CartItem> cartItem = Optional.of(item1);
//
//		when(cartRepo.findById(1)).thenReturn(cartItem);
//		when(cartRepo.save(item2)).thenReturn(item2);
//		assertEquals(item2, service.updateCartItem(item2));
//	}
//
//	@Test
//	public void deleteCartItemServiceTest() throws CartItemNotFoundException {
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
//		Optional<CartItem> cartItem = Optional.of(item);
//		when(cartRepo.findById(1)).thenReturn(cartItem);
//		assertEquals("Cart Item with the id 1 Deleted Successfully!", service.deleteCartItem(1));
//	}
//
//}

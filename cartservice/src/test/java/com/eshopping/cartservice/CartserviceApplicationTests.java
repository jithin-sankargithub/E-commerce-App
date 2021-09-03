package com.eshopping.cartservice;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.eshopping.cartservice.model.Cart;
import com.eshopping.cartservice.model.CartItem;
import com.eshopping.cartservice.repository.CartRepository;
import com.eshopping.cartservice.repository.OrderRepository;
import com.eshopping.cartservice.service.CartServiceImpl;
import com.eshopping.cartservice.service.OrderServiceImpl;


@SpringBootTest
class CartserviceApplicationTests {

	@Autowired
	private CartServiceImpl cartServiceImpl;
	
	@Autowired
	private OrderServiceImpl orderServiceImpl;
	
	@MockBean
	CartRepository cartRepository;
	
	@MockBean
	OrderRepository orderRepository;
	
	@Test
	public void createCartTest() {
		List<CartItem> cartItems = new ArrayList<>();
		cartItems.add(new CartItem("6783738","earphone","Electronics","new earphone",800,"earphone.jpg",2));
		cartItems.add(new CartItem("8484848","shirt","Fashion","Denim",700,"shirt.jpg",2));
		Cart cart = new Cart("282282","232332",cartItems,1500);
		cartServiceImpl.createNewCart(cart);
		verify(cartRepository,times(1)).save(cart);
	}
	
	@Test
	public void findCartByUserIdTest(){
		List<CartItem> cartItems = new ArrayList<>();
		cartItems.add(new CartItem("6783738","earphone","Electronics","new earphone",800,"earphone.jpg",2));
		cartItems.add(new CartItem("8484848","shirt","Fashion","Denim",700,"shirt.jpg",2));
		Optional<Cart> cart = Optional.of(new Cart("282282","232332",cartItems,1500));
		String cartId = "282282";
		when(cartRepository.findCartByUserId(cartId)).thenReturn(cart);
	}
	
	@Test
	public void findOrdersByUserId() {
	}

}

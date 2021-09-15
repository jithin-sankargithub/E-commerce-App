package com.eshopping.cartservice.service;

import java.util.Optional;

import org.springframework.http.ResponseEntity;


import com.eshopping.cartservice.model.Cart;
import com.eshopping.cartservice.model.OrderCheckout;

public interface CartService {
	
	public void createNewCart(Cart cart);
	public Optional<Cart> findCartByUserId(String userId);
	public ResponseEntity<String> checkout(OrderCheckout orderCheckout);


}

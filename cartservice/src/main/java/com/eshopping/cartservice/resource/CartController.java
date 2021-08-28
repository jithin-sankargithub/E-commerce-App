package com.eshopping.cartservice.resource;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eshopping.cartservice.exception.PaymentException;
import com.eshopping.cartservice.model.Cart;
import com.eshopping.cartservice.model.OrderCheckout;
import com.eshopping.cartservice.service.CartServiceImpl;



@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "*")
public class CartController {
	
	@Autowired
	CartServiceImpl cartServiceImpl;
	
	@PostMapping("/newcart")
	public void createNewCart(@RequestBody Cart cart) {
		System.out.println(cart);
		cartServiceImpl.createNewCart(cart);
		
	}
	
	@GetMapping("/getcartbyuserid/{userId}")
	public Optional<Cart> findCartByUserId(@PathVariable("userId") String userId) {
		return cartServiceImpl.findCartByUserId(userId);
	}
	
	@PostMapping("/checkout")
	public ResponseEntity<String> checkout(@RequestBody OrderCheckout orderCheckout) throws PaymentException{
		return cartServiceImpl.checkout(orderCheckout);
	}

}

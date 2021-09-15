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


import com.eshopping.cartservice.model.Cart;
import com.eshopping.cartservice.model.OrderCheckout;
import com.eshopping.cartservice.service.CartServiceImpl;

import io.swagger.annotations.ApiOperation;



@RestController
@RequestMapping("/cart")
//@CrossOrigin(origins = "*")
public class CartController {
	
	@Autowired
	CartServiceImpl cartServiceImpl;
	
	@PostMapping("/newcart")
	@ApiOperation(value = "Creating a user cart",
	notes = "Creates a cart for the customer")
	public void createNewCart(@RequestBody Cart cart) {
		System.out.println(cart);
		cartServiceImpl.createNewCart(cart);
		
	}
	
	@GetMapping("/getcartbyuserid/{userId}")
	@ApiOperation(value = "Retrieve a cart by userId",
	notes = "Retrieve the cart of the user by providing the userId")
	public Optional<Cart> findCartByUserId(@PathVariable("userId") String userId) {
		return cartServiceImpl.findCartByUserId(userId);
	}
	
	@PostMapping("/checkout")
	@ApiOperation(value = "Proceeding to checkout",
	notes = "Customer can confirm the order and proceed to checkout")
	public ResponseEntity<String> confirmOrder(@RequestBody OrderCheckout orderCheckout){
		return cartServiceImpl.checkout(orderCheckout);
	}

}

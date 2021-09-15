package com.eshopping.cartservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import com.eshopping.cartservice.model.Cart;
import com.eshopping.cartservice.model.Order;
import com.eshopping.cartservice.model.OrderCheckout;
import com.eshopping.cartservice.model.Transaction;
import com.eshopping.cartservice.repository.CartRepository;
@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	private OrderServiceImpl orderService;
	
	@Autowired
	RestTemplate restTemplate;


	@Override
	public void createNewCart(Cart cart) {
		Optional<Cart> customerCart = this.findCartByUserId(cart.getUserId());
		if(customerCart.isPresent()) {
			Cart existingCart = customerCart.get();
			cart.setId(existingCart.getId());
			cartRepository.save(cart);
		}
		cartRepository.save(cart);
		
	}

	@Override
	public Optional<Cart> findCartByUserId(String userId) {
		return cartRepository.findCartByUserId(userId);
	}
	
	@Override
	public ResponseEntity<String> checkout(OrderCheckout orderCheckout){
		Optional<Cart> userCart = this.findCartByUserId(orderCheckout.getUserId());
		System.out.println(orderCheckout.getPaymentType());
		if(userCart.isPresent()) {
			Cart cart = userCart.get();
			Order order = new Order(cart,orderCheckout);
			if(order.getPaymentType().equals("WALLET")) {
  				 String transactionId = this.processPayment(order.getUserId(), order.getTotalPrice());
  				 order.setTransactionId(transactionId);
			}
			orderService.addOrder(order);
			cartRepository.deleteById(cart.getId());
			return ResponseEntity.ok().body("Successful Order");
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart Unavailable");
		}
		
	}
	
	public String processPayment(String sender, double amount) {
		
		Transaction transaction = new Transaction();
		transaction.setSender(sender);
		transaction.setAmount(amount);
		transaction.setReceiver("EShoppingZone");
		
		String uri = "http://wallet-service/wallet/transfer";
		ResponseEntity<String> response = restTemplate.postForEntity(uri, transaction, String.class);
		String res = response.getBody();
		return res;
    	
		
	}

	



}

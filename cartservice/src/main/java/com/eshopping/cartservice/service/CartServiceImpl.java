package com.eshopping.cartservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.eshopping.cartservice.exception.PaymentException;
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
		cartRepository.save(cart);
		
	}

	@Override
	public Optional<Cart> findCartByUserId(String userId) {
		return cartRepository.findCartByUserId(userId);
	}
	
	public String processPayment(String sender, double amount) throws PaymentException {
		
		Transaction transaction = new Transaction();
		transaction.setSender(sender);
		transaction.setAmount(amount);
		transaction.setReceiver("ShoppingZone");
		try {
		String uri = "http://wallet-service/wallet/transfer";
		ResponseEntity<String> response = restTemplate.postForEntity(uri, transaction, String.class);
		String res = response.getBody();
		return res;
		}catch (HttpClientErrorException | HttpServerErrorException httpClientOrServerExc) {
			throw new PaymentException(httpClientOrServerExc.getResponseBodyAsString());
		}catch (Exception e) {
			throw new PaymentException("Wallet Unavailable");
		}
		
	}

	@Override
	public ResponseEntity<String> checkout(OrderCheckout orderCheckout) throws PaymentException {
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



}

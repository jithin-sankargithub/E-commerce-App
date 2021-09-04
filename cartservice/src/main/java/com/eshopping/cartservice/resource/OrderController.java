package com.eshopping.cartservice.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eshopping.cartservice.model.Order;
import com.eshopping.cartservice.service.OrderServiceImpl;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderServiceImpl orderServiceImpl;
	
	@GetMapping("/orders/{userId}")
	public List<Order> findUserOrders(@PathVariable("userId") String userId){
		return orderServiceImpl.findAllUserOrders(userId);
	}
	


}

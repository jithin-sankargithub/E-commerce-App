package com.eshopping.cartservice.service;

import java.util.List;
import java.util.Optional;

import com.eshopping.cartservice.model.Order;

public interface OrderService {
	
	public List<Order> findAllUserOrders(String userId);
	public void addOrder(Order order);

	

}

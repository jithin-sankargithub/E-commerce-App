package com.eshopping.cartservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eshopping.cartservice.model.Order;
import com.eshopping.cartservice.repository.OrderRepository;
@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orderRepository;
	

	@Override
	public List<Order> findAllUserOrders(String userId) {
		return orderRepository.findOrdersByUserId(userId);
	}


	@Override
	public void addOrder(Order order) {
		System.out.println(order);
		orderRepository.save(order);
		
	}

	
	
		
	

}

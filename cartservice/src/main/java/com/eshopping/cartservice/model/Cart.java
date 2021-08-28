package com.eshopping.cartservice.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection="Cart")
public class Cart {
	
	@Id
	private String id;
	private String userId;
	private List<CartItem> cartItems;
	private double totalPrice;
	
	public Cart() {
		
	}
	
	
	public Cart(String id, String userId, List<CartItem> cartItems,double totalPrice) {
		super();
		this.id = id;
		this.userId = userId;
		this.cartItems = cartItems;
		this.totalPrice=totalPrice;
	}


	public double getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public List<CartItem> getCartItems() {
		return cartItems;
	}


	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}


	@Override
	public String toString() {
		return "Cart [id=" + id + ", userId=" + userId + ", cartItems=" + cartItems + ", totalPrice=" + totalPrice
				+ "]";
	}


	


}

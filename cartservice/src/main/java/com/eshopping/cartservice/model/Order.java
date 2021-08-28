package com.eshopping.cartservice.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="ShoppingZoneOrders")
public class Order {
	
	@Id
	private String id;
	private String userId;
	private List<CartItem> cartItems;
	private double totalPrice;
	private Address address;
	private String paymentType;
	private String transactionId;
	
	public Order() {
		
	}
	
	public Order(Cart cart,OrderCheckout orderCheckout) {
		this.id="";
		this.userId=cart.getUserId();
		this.cartItems=cart.getCartItems();
		this.totalPrice=cart.getTotalPrice();
		this.address=orderCheckout.getAddress();
		this.paymentType=orderCheckout.getPaymentType();
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

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", userId=" + userId + ", cartItems=" + cartItems + ", totalPrice=" + totalPrice
				+ ", address=" + address + ", paymentType=" + paymentType + ", transactionId=" + transactionId + "]";
	}
	
	
	
	
	

}

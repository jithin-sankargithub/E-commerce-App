package com.eshopping.cartservice.model;

public class OrderCheckout {
	
	private String userId;
	private String paymentType;
	private Address address;
	
	public OrderCheckout() {
		
	}
	
	public OrderCheckout(String userId, String paymentType, Address address) {
		super();
		this.userId = userId;
		this.paymentType = paymentType;
		this.address = address;
	}

	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getPaymentType() {
		return paymentType;
	}
	
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "OrderCheckout [userId=" + userId + ", paymentType=" + paymentType + ", address=" + address + "]";
	}
	
	
	


}

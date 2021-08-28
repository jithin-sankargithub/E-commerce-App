package com.shopping.walletservice.model;

public class WalletRecharge {
	
	private String userId;
	private double amount;
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "WalletRecharge [userId=" + userId + ", amount=" + amount + "]";
	}
	
	

}

package com.shopping.walletservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModelProperty;

@Document(collection="ShoppingZoneWallet")
public class Wallet {
	
	@Id
	private String id;
	@ApiModelProperty(notes = "The userId of the wallet owner")
	private String userId;
	@ApiModelProperty(notes = "The balance available in wallet")
	private double balance;
	
	
	
	public Wallet(String id, String userId, double balance) {
		super();
		this.id = id;
		this.userId = userId;
		this.balance = balance;
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
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Wallet [id=" + id + ", userId=" + userId + ", balance=" + balance + "]";
	}
	
	

}

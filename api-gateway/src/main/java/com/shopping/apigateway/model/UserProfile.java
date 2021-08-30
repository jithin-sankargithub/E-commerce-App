package com.shopping.apigateway.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
public class UserProfile {
	
	private String userName;
	private String password;
	private String email;
	private String role;
	
	public UserProfile() {
		
	}
    public UserProfile(String userName, String password, String email, String role) {
		super();
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.role = role;
	}




	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}
	
	
	
	
	

}

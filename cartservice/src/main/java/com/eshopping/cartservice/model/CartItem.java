package com.eshopping.cartservice.model;

public class CartItem {
	
	private String productId;
	private String name;
	private String category;
	private String description;
	private double price;
	private String image;
	private int quantity;
	
	
	public CartItem() {
		
	}


	public CartItem(String productId, String name, String category, String description, double price, String image,
			int quantity) {
		super();
		this.productId = productId;
		this.name = name;
		this.category = category;
		this.description = description;
		this.price = price;
		this.image = image;
		this.quantity=quantity;
	}


	public String getProductId() {
		return productId;
	}


	public void setProductId(String productId) {
		this.productId = productId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	@Override
	public String toString() {
		return "CartItem [productId=" + productId + ", name=" + name + ", category=" + category + ", description="
				+ description + ", price=" + price + ", image=" + image + ", quantity=" + quantity + "]";
	}
	


	
	
	
	

}

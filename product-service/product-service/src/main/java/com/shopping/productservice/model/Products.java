package com.shopping.productservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(description = "Details about the product")
@Document(collection="Products")
public class Products {
	
	@Id
	private String id;
	@ApiModelProperty(notes = "The name of the product")
	private String name;
	@ApiModelProperty(notes = "The category of the product")
	private String category;
	@ApiModelProperty(notes = "The description of the product")
	private String description;
	@ApiModelProperty(notes = "The price of the product")
	private double price;
	@ApiModelProperty(notes = "The image of the product")
	private String image;
	
	
	public Products(String id, String name, String category, String description, double price, String image) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.description = description;
		this.price = price;
		this.image = image;
	}


	public Products() {
		
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
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


	@Override
	public String toString() {
		return "Products [id=" + id + ", name=" + name + ", category=" + category + ", description=" + description
				+ ", price=" + price + ", image=" + image + "]";
	}
	
	
	
	
	
	

}

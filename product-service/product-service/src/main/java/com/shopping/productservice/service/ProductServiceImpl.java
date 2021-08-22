package com.shopping.productservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.productservice.model.Products;
import com.shopping.productservice.service.repository.ProductRepository;

@Service
public class ProductServiceImpl {
	
	@Autowired
	private ProductRepository productRepository;
	
	public void addProduct(Products product) {
		  productRepository.save(product);
	}
	
	public Products getProductById(String id) {
		return productRepository.findById(id).get();
	}
	
	public List<Products> getAllProducts(){
		 return productRepository.findAll();
	}
	public void updateProduct(Products product) {
		 productRepository.save(product);
	}
	
	public void deleteProduct(String id) {
		productRepository.deleteById(id);
	}
	
	public Products findProductByName(String name) {
		return productRepository.findProductByName(name);
	}
	
	public List<Products> findProductsByCategory(String category){
		return productRepository.findProductByCategory(category);
	}
	

}

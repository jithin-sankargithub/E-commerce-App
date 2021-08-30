package com.shopping.productservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.shopping.productservice.model.Products;
import com.shopping.productservice.service.ProductServiceImpl;
import com.shopping.productservice.service.repository.ProductRepository;

@SpringBootTest
class ProductServiceApplicationTests {
	
    @Autowired
	private ProductServiceImpl productServiceImpl;
    
    @MockBean
    private ProductRepository productRepository;
    
    @Test
    public void getAllProductsTest(){
    	when(productRepository.findAll()).thenReturn(Stream
    			.of(new Products("321","Tshirt","Fashion","Fine Tshirt",900,"tshirt.jpg"), 
    					new Products("453","GoPro","Electronics","Ne new goPro",9900,"gopro.jpg")).collect(Collectors.toList()));
    	
    	assertEquals(2, productServiceImpl.getAllProducts().size());
    }
    
    @Test
    public void getProductsByCategory() {
    	String category = "Electronics";
    	when(productRepository.findProductByCategory(category)).thenReturn(Stream
    			.of(new Products("321","Tshirt","Fashion","Fine Tshirt",900,"tshirt.jpg"), 
    					new Products("453","GoPro","Electronics","Ne new goPro",9900,"gopro.jpg")).collect(Collectors.toList()));
    	assertEquals(2, productServiceImpl.findProductsByCategory(category).size());
    }
    
    @Test
    public void addProductTest() {
    	Products product = new Products("999","Alice in wonderland","Books","The evergreen one",700,"alice in wonderland.jpg");
    	productServiceImpl.addProduct(product);
    	verify(productRepository,times(1)).save(product);
    	
    }
    @Test
    public void deleteProductTest() {
    	String id = "999";
    	productServiceImpl.deleteProduct(id);
    	verify(productRepository,times(1)).deleteById(id);
    	
    }
}

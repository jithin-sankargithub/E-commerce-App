package com.shopping.productservice.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.productservice.model.Products;
import com.shopping.productservice.service.ProductServiceImpl;

@RestController
@CrossOrigin(origins = "*")
public class ProductsController {
	
	@Autowired
	private ProductServiceImpl productService;
	
	
	@PostMapping("/addproduct")
	public ResponseEntity<Products> addProduct(@RequestBody Products product) {
		try {
		   productService.addProduct(product);
		return new ResponseEntity<Products>(HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<Products>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("getallproducts")
	public ResponseEntity<?> findAllProducts(){
		try {
		List<Products> prod = productService.getAllProducts();
		return new ResponseEntity<>(prod,HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getproduct/{id}")
	public ResponseEntity<?> getProductById(@PathVariable("id") String id) {
		try {
			Products prod = productService.getProductById(id);
			return new ResponseEntity<>(prod, HttpStatus.OK);
			}catch (Exception e) {
				
				return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	@GetMapping("/getproductbyname/{name}")
	public ResponseEntity<?> getProductByName(@PathVariable("name") String name) {
		try {
		Products prod = productService.findProductByName(name);
		return new ResponseEntity<>(prod, HttpStatus.OK);
		}catch (Exception e) {
			
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	@GetMapping("/getproductbycategory/{category}")
	public ResponseEntity<?> getProductByCategory(@PathVariable("category") String category){
		try {
			List<Products> prod= productService.findProductsByCategory(category);
			return new ResponseEntity<>(prod, HttpStatus.OK);
			}catch (Exception e) {
				
				return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	@PutMapping("/updateproduct")
	public void updateProduct(@RequestBody Products product) {
		 productService.updateProduct(product);
	}
	
	@DeleteMapping("/deleteproduct/{id}")
	public void deleteProduct(@PathVariable("id") String id) {
		productService.deleteProduct(id);
	}
	


}

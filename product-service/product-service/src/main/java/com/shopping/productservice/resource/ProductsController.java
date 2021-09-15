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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.productservice.model.Products;
import com.shopping.productservice.service.ProductServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/product")
//@CrossOrigin(origins = "*")
public class ProductsController {
	
	@Autowired
	private ProductServiceImpl productService;
	
	
	@PostMapping("/addproduct")
	@ApiOperation(value = "Add a products",
	notes = "Add a product in to the list")
	public ResponseEntity<Products> addProduct(@RequestBody Products product) {
		try {
		   productService.addProduct(product);
		return new ResponseEntity<Products>(HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<Products>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("getallproducts")
	@ApiOperation(value = "Find All products",
	notes = "Receive all the products available")
	public ResponseEntity<?> findAllProducts(){
		try {
		List<Products> prod = productService.getAllProducts();
		return new ResponseEntity<>(prod,HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@GetMapping("/getproductbycategory/{category}")
	@ApiOperation(value = "Find All products by their category",
	notes = "Provide a category to get all the products based on that category",
	response = Products.class)
	public ResponseEntity<?> getProductByCategory(@ApiParam(value = "Category for the products you need to retrieve")
			@PathVariable("category") String category){
		try {
			List<Products> prod= productService.findProductsByCategory(category);
			return new ResponseEntity<>(prod, HttpStatus.OK);
			}catch (Exception e) {
				
				return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
			}
	}
	
	
	
	@DeleteMapping("/deleteproduct/{id}")
	@ApiOperation(value = "Delete a product",
	notes = "Delete a particular product by providing the id of the product")
	public void deleteProduct(@PathVariable("id") String id) {
		productService.deleteProduct(id);
	}
	
	@GetMapping("/getproductbyid/{id}")
	public Optional<Products> getProductById(@PathVariable("id") String id) { 
		return  productService.getProductById(id);
	}
	@PutMapping("/updateproduct")
	public void updateProduct(@RequestBody Products product) {
		productService.updateProduct(product);
	}
	


}

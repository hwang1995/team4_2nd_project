package com.team4.webapp.api;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team4.webapp.dto.ProductDetailsDTO;

@RestController
@RequestMapping("/api/products")
public class ProductsController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductsController.class);
	
	@GetMapping("")
	public Map<String, Object> getProductList(@RequestParam(defaultValue = "1") int pageNo){
		
		return null;
	}
	
	@GetMapping("/{product_id}")
	public ProductDetailsDTO getProduct(@PathVariable Long product_id) {
		return null;
	}
	
	@PostMapping("")
	public void addProduct(@RequestBody Map<String, Object> productInfo) {
		
	}
	
	@PutMapping("")
	public void modifyProduct(@RequestBody Map<String, Object> productInfo) {
		
	}
	
	@DeleteMapping("/{product_id}")
	public void deleteProduct(@PathVariable Long product_id) {
		
	}

}

package com.team4.webapp.api;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team4.webapp.dto.OrdersDTO;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {
	
	private static final Logger logger = LoggerFactory.getLogger(OrdersController.class);
	
	@GetMapping("")
	public Map<String, Object> getOrdersList(@RequestParam(defaultValue = "1") int pageNo, Long orderId, String delivery){
		return null;
		
	}
	
	@GetMapping("/{order_id}")
	public Map<String, Object> getOrderInfo(@PathVariable Long order_id){
		
		return null;
	}
	
	@PutMapping("/{order_id}")
	public Map<String, Object> modifyOrder(@RequestBody OrdersDTO orderInfo){
		return null;
	}
	
	

}

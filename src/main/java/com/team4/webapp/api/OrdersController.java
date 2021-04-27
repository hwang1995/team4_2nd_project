package com.team4.webapp.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team4.webapp.dto.OrdersDTO;
import com.team4.webapp.dto.Pager;
import com.team4.webapp.services.OrderServiceImpl;

@RestController
@RequestMapping("/api/orders")
public class OrdersController {
	@Autowired
	private OrderServiceImpl orderService;
	
	private static final Logger logger = LoggerFactory.getLogger(OrdersController.class);
	
	/**
	 * 전체검색, 주문번호로 검색, 배송상태로 검색시 주문리스트를 전달하기 위한 컨트롤러
	 * @param pageNo
	 * @param orderId 주문번호
	 * @param delivery 배송상태
	 * @return Map<String, Object> (Pager, List<OrdersDTO>)
	 */
	@GetMapping("")
	public Map<String, Object> getOrdersList(@RequestParam(defaultValue = "1") int pageNo, Long orderId, String delivery){
		int totalRows = 0;
		Pager pager;
		List<OrdersDTO> list = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		if(orderId==null && delivery==null) {
			//전체주문
			totalRows = orderService.getTotalOrdersCount();
			pager = new Pager(10, 5, totalRows, pageNo);
			list = orderService.getOrdersList(pager);
		} else if(delivery==null) {
			//주문번호로 검색
			totalRows = 1;
			pager = new Pager(10, 5, totalRows, pageNo);
			list = orderService.getOrdersListByOrderId(pager, orderId);
		} else{
			//배송상태로 검색
			totalRows = orderService.getByDeliveryOrdersCount(delivery);
			pager = new Pager(10, 5, totalRows, pageNo);
			list = orderService.getOrdersListByDelivery(pager, delivery);
		}
		map.put("pager", pager);
		map.put("orders", list);
		return map;
	}
	
	/**
	 * 주문번호를 통해 주문상세정보를 전달하기 위한 컨트롤러
	 * @param order_id
	 * @return Map<String, Object>
	 */
	@GetMapping("/{order_id}")
	public Map<String, Object> getOrderInfo(@PathVariable Long order_id){
		Map<String, Object> map = orderService.getOrderInfo(order_id);
		return map;
	}
	
	/**
	 * 주문정보수정 내용을 전달하기 위한 컨트롤러
	 * @param orderInfo 수정전 주문정보
	 * @return Map<String, Object> (OrdersDTO)
	 */
	@PutMapping("")
	public Map<String, Object> modifyOrder(@RequestBody OrdersDTO orderInfo){
		OrdersDTO order = orderService.modifyOrder(orderInfo);
		Map<String, Object> map = new HashMap<>();
		map.put("order", order);
		return map;
	}
	
	

}

package com.team4.webapp.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team4.webapp.dto.OrderDateSumDTO;
import com.team4.webapp.services.AccountServiceImpl;
import com.team4.webapp.services.DashboardService;
import com.team4.webapp.services.OrderServiceImpl;
import com.team4.webapp.services.ProductServiceImpl;
import com.team4.webapp.services.QnaServiceImpl;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
	
	@Autowired
	private AccountServiceImpl accountService;
	@Autowired
	private QnaServiceImpl qnaService;
	@Autowired
	private OrderServiceImpl orderService;
	@Autowired
	private ProductServiceImpl productService;
	@Autowired
	private DashboardService dashboardService;
	
	@GetMapping("")
	public Map<String, Object> getDashboardData() {
		// 회원 수를 가져온다.
		int totalMembers = accountService.getTotalMemberRows();
		
		// Q&A 답변 대기중을 가져온다.
		int totalQnaWaiting = qnaService.getCountByAnswer("답변중");
		
		// Q&A 답변 완료를 가져온다.
		int totalQnaFinished = qnaService.getCountByFinishedAnswer("답변중");
		
		// 상품 수를 가져온다.
		int totalProducts = productService.getCount();
		
		// 배송 준비중을 가져온다.
		int totalDeliveryWaiting = orderService.getByDeliveryOrdersCount("DELIVERY_PENDING");

		// 배송 완료를 가져온다.
		int totalDeliveryFinished = orderService.getByDeliveryOrdersCount("DELIVERY_COMPLETED");
		
		List<OrderDateSumDTO> chart = dashboardService.getDashboardChart();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalMembers", totalMembers);
		map.put("totalQnaWaiting", totalQnaWaiting);
		map.put("totalQnaFinished", totalQnaFinished);
		map.put("totalProducts", totalProducts);
		map.put("totalDeliveryWaiting", totalDeliveryWaiting);
		map.put("totalDeliveryFinished", totalDeliveryFinished);
		map.put("chart", chart);
		return map;
	}

}

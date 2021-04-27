package com.team4.webapp.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team4.webapp.dao.OrderlistsDAO;
import com.team4.webapp.dao.OrdersDAO;
import com.team4.webapp.dao.ProductsDAO;
import com.team4.webapp.dto.MyPageDTO;
import com.team4.webapp.dto.OrderDateSumDTO;
import com.team4.webapp.dto.OrderlistsDTO;
import com.team4.webapp.dto.OrdersDTO;
import com.team4.webapp.dto.ProductsDTO;

@Service
public class DashboardService {
	
	@Autowired
	private OrdersDAO ordersDAO;
	
	@Autowired
	private OrderlistsDAO orderlistsDAO;
	
	@Autowired
	private ProductsDAO productsDAO;
	
	/**
	 * 서비스 목적
	 * - 차트를 만들기 위해 주문날짜와
	 * - 일별 총합계를 구할 수 있도록 제공하는 서비스
	 * @return List<OrderDateSumDTO>
	 */
	public List<OrderDateSumDTO> getDashboardChart() {
		List<OrderDateSumDTO> dateSums = new ArrayList<>();
		List<OrderDateSumDTO> orderDateSums = new ArrayList<>();
		List<OrdersDTO> orders = ordersDAO.selectOrdersList();
		
		// 1. OrderDateSumDTO는 주문날짜, 총합계를 필드로 가지고 있음.
		// 2. 주문날짜를 OrderDateSumDTO에 세팅해줌.
		// 3. 주문번호로 주문리스트 정보를 받아옴.
		for(OrdersDTO order : orders) {
			List<MyPageDTO> orderInfoList = new ArrayList<MyPageDTO>();
			OrderDateSumDTO orderDateSum = new OrderDateSumDTO();
			orderDateSum.setOrder_date(order.getOrder_date());
			List<OrderlistsDTO> orderlists = orderlistsDAO.selectByOrderId(order.getOrder_id());
			
			//1.MyPageDTO는 OrderList와 Product에 대한 정보를 가지고 있음.
			//2.주문리스트의 상품정보를 받아옴.
			//3.MyPageDTO에 OrderList와 Product 정보를 세팅해줌.
			//4.MyPageDTO를 리스트로 추가해줌.
			for(OrderlistsDTO orderlist : orderlists) {
				ProductsDTO products = productsDAO.selectByProductId(orderlist.getProduct_id());
				MyPageDTO orderInfo = new MyPageDTO();
				orderInfo.setOrderInfo(orderlist);
				orderInfo.setProductsInfo(products);
				orderInfoList.add(orderInfo);
			}
			
			//1.각 주문별로 총합계를 구함.
			//2.총합계를 OrderDateSumDTO에 세팅해줌.
			//3.OrderDateSumDTO 리스트에 OrderDateSumDTO를 추가해줌.
			long totalPrice = 0;
			for(MyPageDTO list : orderInfoList) {
				long tempPrice = (long) list.getProduct_quantity() * (long) list.getProduct_price();
				totalPrice += tempPrice;
			}
			
			orderDateSum.setSum_price(totalPrice);
			orderDateSums.add(orderDateSum);

		}
		
		// 1. 만들어진 OrderDateSumDTO 리스트로 새로운 OrderDateSumDTO 리스트를 세팅해줌.
		// 2. orderDate를 OrderDateSumDTO 리스트의 첫번째 것으로 세팅함.
		Date orderDate = new Date();
		orderDate = orderDateSums.get(0).getOrder_date();
		long total = 0;
		
		// 1. 만약 orderDate의 날짜와 OrderDateSumDTO의 값의 날짜가 같다면, 총합계에 더해줌.
		// 2. 같지 않다면, orderDate와 총합계를 최종 OrderDateSumDTO 리스트에 추가해줌.
		// 3. orderDate와 총합계를 새로운 것으로 세팅하고 반복문 진행.
		for(OrderDateSumDTO dateSum : orderDateSums) {
			if(dateSum.getOrder_date().getYear() == orderDate.getYear()
					&& dateSum.getOrder_date().getMonth() == orderDate.getMonth()
					&& dateSum.getOrder_date().getDate() == orderDate.getDate()) {
				total += dateSum.getSum_price();
			} else {
				OrderDateSumDTO ds = new OrderDateSumDTO();
				ds.setOrder_date(orderDate);
				ds.setSum_price(total);
				dateSums.add(ds);

				orderDate = dateSum.getOrder_date();
				total = dateSum.getSum_price();
			}
		}
		// 1. 마지막 orderDate와 총합계를 최종 OrderDateSumDTO 리스트에 추가해줌.
		// 2. 최종 OrderDateSumDTO 리스트를 리턴해줌.
		OrderDateSumDTO ds = new OrderDateSumDTO();
		ds.setOrder_date(orderDate);
		ds.setSum_price(total);
		dateSums.add(ds);
		
		return dateSums;
	}
}

package com.team4.webapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.team4.webapp.dto.OrdersDTO;
import com.team4.webapp.dto.Pager;

@Mapper
public interface OrdersDAO {
	public List<OrdersDTO> selectOrdersList();
	public OrdersDTO selectByOrderId(Long order_id);
	public int insertOrders(OrdersDTO carts);
	public int updateOrders(OrdersDTO carts);
	public int deleteByOrderId(Long order_id);
	public List<OrdersDTO> selectByMemberId(Long member_id);
	public List<OrdersDTO> selectByPage(Pager pager);
	public int countOrders(Long member_id);
	
	public int count();
	public List<OrdersDTO> selectAllByPage(Pager pager);
	public List<OrdersDTO> selectByPageAndOrderId(@Param("pager") Pager pager, @Param("order_id") Long order_id);
	public List<OrdersDTO> selectByPageAndDelivery(@Param("pager") Pager pager, @Param("order_delivery_status") String order_delivery_status);
}

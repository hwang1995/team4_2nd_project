package com.team4.webapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.team4.webapp.dto.OrderlistsDTO;

@Mapper
public interface OrderlistsDAO {
	public List<OrderlistsDTO> selectOrderlistsList();
	public int insertOrderlists(OrderlistsDTO orderlistsdto);
	public OrderlistsDTO selectByOrderlistId(Long orderlist_id);
	public int updateOrderlists(OrderlistsDTO orderlistsdto);
	public int deleteByOrderlistId(Long orderlist_id);
	public List<OrderlistsDTO> selectByOrderId(Long order_id);
}

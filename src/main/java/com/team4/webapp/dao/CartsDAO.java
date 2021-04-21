package com.team4.webapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.team4.webapp.dto.CartsDTO;

@Mapper
public interface CartsDAO {
	public List<CartsDTO> selectCartsList();
	public CartsDTO selectByCartId(Long cart_id);
	public int insertCarts(CartsDTO carts);
	public int updateCarts(CartsDTO carts);
	public int deleteByCartId(Long cart_id);
	public int deleteByMemberId(Long member_id);
	public List<CartsDTO> selectCartListByMemberId(Long member_id);
	
}

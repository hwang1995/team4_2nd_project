package com.team4.webapp.dto;

import java.util.Date;
import java.util.List;

public class MyPageListDTO {
	private Long order_id;
	private Date order_date;
	private List<MyPageDTO> myPageList;
	
	public Long getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}
	public List<MyPageDTO> getMyPageList() {
		return myPageList;
	}
	public void setMyPageList(List<MyPageDTO> myPageList) {
		this.myPageList = myPageList;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	@Override
	public String toString() {
		return "MyPageListDTO [order_id=" + order_id + ", order_date=" + order_date + ", myPageList=" + myPageList
				+ "]";
	}
	
}

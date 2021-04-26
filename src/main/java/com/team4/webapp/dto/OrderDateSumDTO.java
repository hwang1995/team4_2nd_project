package com.team4.webapp.dto;

import java.util.Date;

public class OrderDateSumDTO {
	private Date order_date;
	private Long sum_price;
	
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	public Long getSum_price() {
		return sum_price;
	}
	public void setSum_price(Long sum_price) {
		this.sum_price = sum_price;
	}
	
	@Override
	public String toString() {
		return "OrderDateSum [order_date=" + order_date + ", sum_price=" + sum_price + "]";
	}
}

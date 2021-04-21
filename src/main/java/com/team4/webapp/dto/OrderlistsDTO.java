package com.team4.webapp.dto;

public class OrderlistsDTO {
	private Long orderlist_id;
	private int orderlist_quantity;
	private String product_color;
	private String product_size;
	private Long order_id;
	private Long product_id;
	
	// Getters & Setters
	public Long getOrderlist_id() {
		return orderlist_id;
	}
	public void setOrderlist_id(Long orderlist_id) {
		this.orderlist_id = orderlist_id;
	}
	public int getOrderlist_quantity() {
		return orderlist_quantity;
	}
	public void setOrderlist_quantity(int orderlist_quantity) {
		this.orderlist_quantity = orderlist_quantity;
	}
	public String getProduct_color() {
		return product_color;
	}
	public void setProduct_color(String product_color) {
		this.product_color = product_color;
	}
	public String getProduct_size() {
		return product_size;
	}
	public void setProduct_size(String product_size) {
		this.product_size = product_size;
	}
	public Long getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}
	public Long getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}
	
	// For Testing purpose -> DTO의 값을 한번에 보고 싶을 때 사용
	@Override
	public String toString() {
		return "OrderlistsDTO [orderlist_id=" + orderlist_id + ", orderlist_quantity=" + orderlist_quantity
				+ ", product_color=" + product_color + ", product_size=" + product_size + ", order_id=" + order_id
				+ ", product_id=" + product_id + "]";
	}
	
	
	public void setCheckoutInfo(CheckoutListDTO checkout) {
		this.orderlist_quantity = checkout.getProduct_quantity();
		this.product_color = checkout.getProduct_color();
		this.product_size = checkout.getProduct_size();
		this.product_id = checkout.getProduct_id();
		
	}
	
	
	

}

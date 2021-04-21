package com.team4.webapp.dto;

public class PreOrdersDTO {
	private Long product_id;
	private String product_color;
	private String product_size;
	private int product_quantity;
	
	// Getters & Setters
	public Long getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
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
	public int getProduct_quantity() {
		return product_quantity;
	}
	public void setProduct_quantity(int product_quantity) {
		this.product_quantity = product_quantity;
	}
	
	// For testing purpose
	@Override
	public String toString() {
		return "preOrderDTO [product_id=" + product_id + ", product_color=" + product_color + ", product_size="
				+ product_size + ", product_quantity=" + product_quantity + "]";
	}
	
	public void setCartInfo(CartsDTO cart) {
		this.product_id = cart.getProduct_id();
		this.product_color = cart.getProduct_color();
		this.product_size = cart.getProduct_size();
		this.product_quantity = cart.getCart_quantity();
		
	}
	
	
	
	

}

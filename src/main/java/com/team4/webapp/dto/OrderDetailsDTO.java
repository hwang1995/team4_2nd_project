package com.team4.webapp.dto;

public class OrderDetailsDTO {
	// Orderlists DTO의 값을 Mapping
	private Long orderlist_id;
	private int orderlist_quantity;
	private String product_color;
	private String product_size;
	private Long order_id;
	private Long product_id;
	
	// ProductsDTO의 값을 Mapping
	private String product_name;
	private int product_price;
	private String product_image;
	private String product_content;
	private Long subcategory_id;
	
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
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public int getProduct_price() {
		return product_price;
	}
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
	public String getProduct_image() {
		return product_image;
	}
	public void setProduct_image(String product_image) {
		this.product_image = product_image;
	}
	public String getProduct_content() {
		return product_content;
	}
	public void setProduct_content(String product_content) {
		this.product_content = product_content;
	}
	public Long getSubcategory_id() {
		return subcategory_id;
	}
	public void setSubcategory_id(Long subcategory_id) {
		this.subcategory_id = subcategory_id;
	}
	
	// 연관관계 편의 메서드
	public void setOrderlistInfo(OrderlistsDTO orderlists) {
		this.orderlist_id = orderlists.getOrder_id();
		this.orderlist_quantity = orderlists.getOrderlist_quantity();
		this.product_color = orderlists.getProduct_color();
		this.product_size = orderlists.getProduct_size();
		this.order_id = orderlists.getOrder_id();
		this.product_id = orderlists.getProduct_id();
	}
	
	public void setProductInfo(ProductsDTO product) {
		this.product_name = product.getProduct_name();
		this.product_price = product.getProduct_price();
		this.product_image = product.getProduct_image();
		this.product_content = product.getProduct_content();
		this.subcategory_id = product.getSubcategory_id();
	}
	
	// For Testing purpose -> DTO의 값을 한번에 보고 싶을 때 사용
	@Override
	public String toString() {
		return "OrderDetailsDTO [orderlist_id=" + orderlist_id + ", orderlist_quantity=" + orderlist_quantity
				+ ", product_color=" + product_color + ", product_size=" + product_size + ", order_id=" + order_id
				+ ", product_id=" + product_id + ", product_name=" + product_name + ", product_price=" + product_price
				+ ", product_image=" + product_image + ", product_content=" + product_content + ", subcategory_id="
				+ subcategory_id + "]";
	}
	
	
	
	
	

}

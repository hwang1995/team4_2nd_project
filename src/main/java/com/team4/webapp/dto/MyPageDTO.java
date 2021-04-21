package com.team4.webapp.dto;


public class MyPageDTO {
	private String product_image;
	private Long product_id;
	private String product_name;
	private String product_color;
	private String product_size;
	private int product_quantity;
	private int product_price;
	
	public String getProduct_image() {
		return product_image;
	}
	public void setProduct_image(String product_image) {
		this.product_image = product_image;
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
	public int getProduct_price() {
		return product_price;
	}
	public void setProduct_price(int product_price) {
		this.product_price = product_price;
	}
	
	public void setProductsInfo(ProductsDTO product) {
		this.product_id = product.getProduct_id();
		this.product_name = product.getProduct_name();
		this.product_price = product.getProduct_price();
		this.product_image = product.getProduct_image();
	}
	
	public void setOrderInfo(OrderlistsDTO orderlist) {
		this.product_quantity = orderlist.getOrderlist_quantity();
		this.product_color = orderlist.getProduct_color();
		this.product_size = orderlist.getProduct_size();
		this.product_id = orderlist.getProduct_id();
	}
	@Override
	public String toString() {
		return "MyPageDTO [product_image=" + product_image + ", product_id=" + product_id + ", product_name="
				+ product_name + ", product_color=" + product_color + ", product_size=" + product_size
				+ ", product_quantity=" + product_quantity + ", product_price=" + product_price + "]";
	}
	
	
}

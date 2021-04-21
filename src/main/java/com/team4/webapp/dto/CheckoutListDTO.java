package com.team4.webapp.dto;

public class CheckoutListDTO {
	// ProductsDTO에서 비롯
	private Long product_id;
	private String product_name;
	private int product_price;
	private String product_image;
	
	// PreOrdersDTO에서 비롯
	private String product_color;
	private String product_size;
	private int product_quantity;
	
	// Getters and Setters
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
		return "CheckoutListDTO [product_id=" + product_id + ", product_name=" + product_name + ", product_price="
				+ product_price + ", product_image=" + product_image + ", product_color=" + product_color
				+ ", product_size=" + product_size + ", product_quantity=" + product_quantity + "]";
	}
	
	public void setProductsInfo(ProductsDTO product) {
		this.product_id = product.getProduct_id();
		this.product_name = product.getProduct_name();
		this.product_price = product.getProduct_price();
		this.product_image = product.getProduct_image();
	}
	
	public void setPreOrdersInfo(PreOrdersDTO preorder) {
		this.product_color = preorder.getProduct_color();
		this.product_size = preorder.getProduct_size();
		this.product_quantity = preorder.getProduct_quantity();
	}
	
	public long calcPrice(CheckoutListDTO checkout) {
		long tempPrice = (long) checkout.getProduct_quantity() * (long) checkout.getProduct_price();
		return tempPrice;
	}
	
	
	
	
	
	
	

}

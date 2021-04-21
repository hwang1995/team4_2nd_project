package com.team4.webapp.dto;

public class CartListDTO {
	private Long cart_id;
	private int cart_quantity;
	private String product_color;
	private String product_size;
	private Long member_id;
	private Long product_id;
	private String product_image_name;
	private String product_name;
	private int product_price;
	
	// Getters & Setters
	public Long getCart_id() {
		return cart_id;
	}
	public void setCart_id(Long cart_id) {
		this.cart_id = cart_id;
	}
	public int getCart_quantity() {
		return cart_quantity;
	}
	public void setCart_quantity(int cart_quantity) {
		this.cart_quantity = cart_quantity;
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
	public Long getMember_id() {
		return member_id;
	}
	public void setMember_id(Long member_id) {
		this.member_id = member_id;
	}
	public Long getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}
	public String getProduct_image_name() {
		return product_image_name;
	}
	public void setProduct_image_name(String product_image_name) {
		this.product_image_name = product_image_name;
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
	
	// For Testing purpose -> DTO의 값을 한번에 보고 싶을 때 사용
	@Override
	public String toString() {
		return "CartListDTO [cart_id=" + cart_id + ", cart_quantity=" + cart_quantity + ", product_color="
				+ product_color + ", product_size=" + product_size + ", member_id=" + member_id + ", product_id="
				+ product_id + ", product_image_name=" + product_image_name + ", product_name=" + product_name
				+ ", product_price=" + product_price + "]";
	}
	
	public void setCartsInfo(CartsDTO cart) {
		this.cart_id = cart.getCart_id();
		this.cart_quantity = cart.getCart_quantity();
		this.product_color = cart.getProduct_color();
		this.product_size = cart.getProduct_size();
		this.product_id = cart.getProduct_id();
		this.member_id = cart.getMember_id();
	}
	
	public void setProductInfo(ProductsDTO product) {
		this.product_image_name = product.getProduct_image();
		this.product_name = product.getProduct_name();
		this.product_price = product.getProduct_price();
	}
	
	public long calcPrice(CartListDTO cart) {
		long tempPrice = (long) cart.getCart_quantity() * (long) cart.getProduct_price();
		return tempPrice;
	}
}

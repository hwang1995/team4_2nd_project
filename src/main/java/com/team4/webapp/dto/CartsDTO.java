package com.team4.webapp.dto;

public class CartsDTO {
	private Long cart_id;
	private int cart_quantity;
	private String product_color;
	private String product_size;
	private Long member_id;
	private Long product_id;
	
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
	
	// For Testing purpose -> DTO의 값을 한번에 보고 싶을 때 사용
	@Override
	public String toString() {
		return "CartsDTO [cart_id=" + cart_id + ", cart_quantity=" + cart_quantity + ", product_color=" + product_color
				+ ", product_size=" + product_size + ", member_id=" + member_id + ", product_id=" + product_id + "]";
	}
	
	
	public void setMemberInfo(MembersDTO memberInfo) {
		this.member_id = memberInfo.getMember_id();
		
	}
	
	public void setCartInfo(PreOrdersDTO cartInfo) {
		this.cart_quantity = cartInfo.getProduct_quantity();
		this.product_color = cartInfo.getProduct_color();
		this.product_size = cartInfo.getProduct_size();
		this.product_id = cartInfo.getProduct_id();
		
	}
	

}

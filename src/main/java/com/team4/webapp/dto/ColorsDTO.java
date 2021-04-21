package com.team4.webapp.dto;

public class ColorsDTO {
	private Long color_id;
	private String color_name;
	private Long product_id;
	
	// Getters & Setters
	public Long getColor_id() {
		return color_id;
	}
	public void setColor_id(Long color_id) {
		this.color_id = color_id;
	}
	public String getColor_name() {
		return color_name;
	}
	public void setColor_name(String color_name) {
		this.color_name = color_name;
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
		return "Colors [color_id=" + color_id + ", color_name=" + color_name + ", product_id=" + product_id + "]";
	}
	
	
	

}

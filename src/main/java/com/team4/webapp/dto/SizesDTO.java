package com.team4.webapp.dto;

public class SizesDTO {
	private Long size_id;
	private String size_name;
	private Long product_id;
	
	// Getters & Setters
	public Long getSize_id() {
		return size_id;
	}
	public void setSize_id(Long size_id) {
		this.size_id = size_id;
	}
	public String getSize_name() {
		return size_name;
	}
	public void setSize_name(String size_name) {
		this.size_name = size_name;
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
		return "SizesDTO [size_id=" + size_id + ", size_name=" + size_name + ", product_id=" + product_id + "]";
	}
	
	

}

package com.team4.webapp.dto;

public class ProductCategoryDTO {
	private Long subcategory_id;
	private String subcategory_name;
	private Long category_id;
	private String category_name;
	
	// Getters & Setters
	public Long getSubcategory_id() {
		return subcategory_id;
	}
	public void setSubcategory_id(Long subcategory_id) {
		this.subcategory_id = subcategory_id;
	}
	public String getSubcategory_name() {
		return subcategory_name;
	}
	public void setSubcategory_name(String subcategory_name) {
		this.subcategory_name = subcategory_name;
	}
	public Long getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	
	// For Testing purpose -> DTO의 값을 한번에 보고 싶을 때 사용
	@Override
	public String toString() {
		return "ProductCategoryDTO [subcategory_id=" + subcategory_id + ", subcategory_name=" + subcategory_name
				+ ", category_id=" + category_id + ", category_name=" + category_name + "]";
	}
	
	
	
	

}

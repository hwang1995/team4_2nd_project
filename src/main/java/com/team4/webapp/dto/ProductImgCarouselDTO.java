package com.team4.webapp.dto;

public class ProductImgCarouselDTO {
	
	private Long product_img_id;
	private String product_img_category;
	private String product_img_name;
	private Long product_id;
	
	//Getter & Setter
	public Long getProduct_img_id() {
		return product_img_id;
	}
	public void setProduct_img_id(Long product_img_id) {
		this.product_img_id = product_img_id;
	}
	public String getProduct_img_category() {
		return product_img_category;
	}
	public void setProduct_img_category(String product_img_category) {
		this.product_img_category = product_img_category;
	}
	public String getProduct_img_name() {
		return product_img_name;
	}
	public void setProduct_img_name(String product_img_name) {
		this.product_img_name = product_img_name;
	}
	public Long getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}
	
	
	@Override
	public String toString() {
		return "ProductImgCarouselDTO [product_img_id=" + product_img_id + ", product_img_category="
				+ product_img_category + ", product_img_name=" + product_img_name + ", product_id=" + product_id + "]";
	}

}

package com.team4.webapp.dto;

public class ProductsDTO {
	private Long product_id;
	private String product_name;
	private int product_price;
	private String product_image;
	private String product_content;
	private String product_subcontent;
	private Long subcategory_id;
	
	// Getters & Setters
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

	public String getProduct_subcontent() {
		return product_subcontent;
	}

	public void setProduct_subcontent(String product_subcontent) {
		this.product_subcontent = product_subcontent;
	}

	public Long getSubcategory_id() {
		return subcategory_id;
	}

	public void setSubcategory_id(Long subcategory_id) {
		this.subcategory_id = subcategory_id;
	}
	
	// For Testing purpose -> DTO의 값을 한번에 보고 싶을 때 사용
	@Override
	public String toString() {
		return "ProductsDTO [product_id=" + product_id + ", product_name=" + product_name + ", product_price="
				+ product_price + ", product_image=" + product_image + ", product_content=" + product_content
				+ ", product_subcontent=" + product_subcontent + ", subcategory_id=" + subcategory_id + "]";
	}

	public void setImagePath() {
		String filePath = "/webapp/image?path=" + this.getProduct_image();
		this.product_image = filePath;
	}
	
	

}

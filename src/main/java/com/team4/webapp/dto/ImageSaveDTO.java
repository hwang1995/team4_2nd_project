package com.team4.webapp.dto;

public class ImageSaveDTO {
	private Long product_id;
	private String type;
	private String filename;
	private String base64;
	
	public Long getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getBase64() {
		return base64;
	}
	public void setBase64(String base64) {
		this.base64 = base64;
	}
	@Override
	public String toString() {
		return "ImageSaveDTO [product_id=" + product_id + ", type=" + type + ", filename=" + filename + ", base64="
				+ base64 + "]";
	}
	
	
	


}

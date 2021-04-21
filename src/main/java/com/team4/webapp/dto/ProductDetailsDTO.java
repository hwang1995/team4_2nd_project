package com.team4.webapp.dto;

import java.util.List;

public class ProductDetailsDTO {
	private Long product_id;
	private String product_name;
	private int product_price;
	private String product_content;
	private String product_subcontent;
	private String product_image;
	private List<ProductImgCarouselDTO> product_imgs_carousel_list;
	private List<ProductImgDetailDTO> product_imgs_detail_list;
	private List<SizesDTO> product_sizes_list;
	private List<ColorsDTO> product_colors_list;

	//Getter & Setter
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
	public String getProduct_image() {
		return product_image;
	}
	public void setProduct_image(String product_image) {
		this.product_image = product_image;
	}
	public List<ProductImgCarouselDTO> getProduct_imgs_carousel_list() {
		return product_imgs_carousel_list;
	}
	public void setProduct_imgs_carousel_list(List<ProductImgCarouselDTO> product_imgs_carousel_list) {
		this.product_imgs_carousel_list = product_imgs_carousel_list;
	}
	public List<ProductImgDetailDTO> getProduct_imgs_detail_list() {
		return product_imgs_detail_list;
	}
	public void setProduct_imgs_detail_list(List<ProductImgDetailDTO> product_imgs_detail_list) {
		this.product_imgs_detail_list = product_imgs_detail_list;
	}
	public List<SizesDTO> getProduct_sizes_list() {
		return product_sizes_list;
	}
	public void setProduct_sizes_list(List<SizesDTO> product_sizes_list) {
		this.product_sizes_list = product_sizes_list;
	}
	public List<ColorsDTO> getProduct_colors_list() {
		return product_colors_list;
	}
	public void setProduct_colors_list(List<ColorsDTO> product_colors_list) {
		this.product_colors_list = product_colors_list;
	}
	public void setProductDetailsInfo(ProductsDTO product, List<ProductImgCarouselDTO> carouselList
			,List<ProductImgDetailDTO> detailList, List<ColorsDTO> colorsList
			,List<SizesDTO> sizesList) {

		this.product_id= product.getProduct_id();
		this.product_name = product.getProduct_name();
		this.product_price = product.getProduct_price();
		this.product_content = product.getProduct_content();
		this.product_subcontent = product.getProduct_subcontent();
		this.product_imgs_carousel_list = carouselList;
		this.product_imgs_detail_list = detailList;
		this.product_colors_list = colorsList;
		this.product_sizes_list = sizesList;
	}
	@Override
	public String toString() {
		return "ProductDetailsDTO [product_id=" + product_id + ", product_name=" + product_name + ", product_price="
				+ product_price + ", product_content=" + product_content + ", product_subcontent=" + product_subcontent
				+ ", product_image=" + product_image + ", product_imgs_carousel_list=" + product_imgs_carousel_list
				+ ", product_imgs_detail_list=" + product_imgs_detail_list + ", product_sizes_list="
				+ product_sizes_list + ", product_colors_list=" + product_colors_list + "]";
	}
}

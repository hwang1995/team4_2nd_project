package com.team4.webapp.services;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.springframework.web.multipart.MultipartFile;

import com.team4.webapp.dto.ImageSaveDTO;
import com.team4.webapp.dto.Pager;
import com.team4.webapp.dto.ProductDetailsDTO;

import com.team4.webapp.dto.ProductsDTO;


public interface IProductService {
	/**
	 * 모든 사용자들이 웹 사이트의 모든 상품을 보기 위해 제공하는 인터페이스
	 * @param String order = "DESC", "HIGH", "LOW"
	 * @return List<List<ProductsDTO>>
	 */
	List<List<ProductsDTO>> showAllProduct(String order);
	
	/**
	 * 모든 사용자들이 해당 카테고리의 상품을 보기 위해 제공하는 인터페이스
	 * @param String subcategory = "Jacket, Coat, Cardigan", "Knit, Shirt, Tee", "Pants, Skirt"
	 * @param String order = "desc", "high", "low"
	 * @return List<ProductsDTO>
	 */
	List<ProductsDTO> showProductByCategory(String subcategory, String order);
	
	/**
	 * 모든 사용자들이 상품의 상세 정보를 보기 위해 제공하는 인터페이스
	 * @param product_id
	 * @return ProductDetailsDTO
	 */
	ProductDetailsDTO showProductDetail(Long product_id);
	
	/**
	 * 상품을 삽입하기 위해 제공하는 인터페이스
	 * @param ProductsDTO product
	 * @return boolean
	 */
	boolean addProduct(ProductsDTO product, JSONArray colors, JSONArray sizes);
	
	/**
	 * 상품을 삭제하기 위해 제공하는 인터페이스
	 * @param Long product_id
	 * @return int (영향받은 행의 수를 알기 위해)
	 */
	boolean removeProduct(Long product_id);
	
	/**
	 * 상품의 목록을 가져오기 위한 인터페이스
	 * @param Pager pager
	 * @return List<ProductsDTO>
	 */
	List<ProductsDTO> getProductLists(Pager pager);
	
	/**
	 * 상품의 목록을 해당 카테고리에 맞게 가져오기 위한 인터페이스
	 * @param Pager pager
	 * @param Long subcategory_id
	 * @return List<ProductsDTO>
	 */
	List<ProductsDTO> getProductListsBySubCategory(Pager pager, Long subcategory_id);
	
	/**
	 * 페이저를 구현하기 위해서 상품 테이블의 총 행수를 구하기 위한 인터페이스
	 * @return int
	 */
	int getCount();
	
	/**
	 * 페이저를 구현하기 위해서 상품 테이블의 해당 카테고리의 총 행수를 구하기 위한 인터페이스
	 * @param Long subcategory_id
	 * @return int
	 */
	int getSpecificCount(Long subcategory_id);
	
	/**
	 * 카테고리의 목록을 제공하기 위한 인터페이스
	 * @return Map<String, Object>
	 */
	Map<String, Object> getAllCategories();
	
	/**
	 * 상품 상세 정보를 제공하기 위한 인터페이스
	 * @param Long product_id
	 * @return ProductDetailsDTO
	 */
	ProductDetailsDTO getProductDetails(Long product_id);
	
	/**
	 * 상품 추가시에 먼저 시퀀스의 번호를 받아오기 위한 인터페이스
	 * @return Long
	 */
	Long getSequence();
	
	/**
	 * 상품 이미지를 업로드를 위해 제공하는 인터페이스
	 * @param ImageSaveDTO data
	 * @return ImageSaveDTO 
	 */
	ImageSaveDTO uploadImage(ImageSaveDTO data);
	
	/**
	 * 상품 메인 이미지를 업로드 하기 위해 제공하는 인터페이스
	 * @param MultipartFile uploadFile
	 * @return String
	 */
	String uploadMainImage(MultipartFile uploadFile);
	
	/**
	 * 상품의 정보를 수정하기 위해 제공하는 인터페이스
	 * @param ProductsDTO product
	 * @return ProductsDTO
	 */
	ProductsDTO updateProductInfo(ProductsDTO product);
	
	
	
	

}

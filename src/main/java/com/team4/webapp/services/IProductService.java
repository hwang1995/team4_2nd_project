package com.team4.webapp.services;

import java.util.List;

import com.team4.webapp.dto.CategoriesDTO;
import com.team4.webapp.dto.ColorsDTO;
import com.team4.webapp.dto.Pager;
import com.team4.webapp.dto.ProductDetailsDTO;
import com.team4.webapp.dto.ProductImgsDTO;
import com.team4.webapp.dto.ProductsDTO;
import com.team4.webapp.dto.SizesDTO;
import com.team4.webapp.dto.SubCategoriesDTO;

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
	boolean addProduct(ProductsDTO product, 
			CategoriesDTO category, 
			SubCategoriesDTO subcategory,
			List<SizesDTO> size,
			List<ColorsDTO> color,
			List<ProductImgsDTO> productImg);
	
	/**
	 * 미구현 사항
	 * @param Long product_id
	 * @return int (영향받은 행의 수를 알기 위해)
	 */
	boolean removeProduct(Long product_id);
	
	List<ProductsDTO> getProductLists(Pager pager);
	
	List<ProductsDTO> getProductListsBySubCategory(Pager pager, Long subcategory_id);
	
	

}

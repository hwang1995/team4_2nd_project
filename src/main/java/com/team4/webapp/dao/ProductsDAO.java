package com.team4.webapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.team4.webapp.dto.Pager;
import com.team4.webapp.dto.ProductsDTO;

@Mapper
public interface ProductsDAO {
	public List<ProductsDTO> selectProductsList();
	public ProductsDTO selectByProductId(Long product_id);
	public int insertProducts(ProductsDTO products);
	public int updateProducts(ProductsDTO products);
	public int deleteByProductId(Long product_id);
	public List<ProductsDTO> selectProdutsListOrderByHighPrice();
	public List<ProductsDTO> selectProductsListOrderByLowPrice();
	public List<ProductsDTO> selectBySubCategoryId(Long subCategory_id);
	public List<ProductsDTO> selectBySubCategoryIdOrderByHighPrice(Long subCategory_id);
	public List<ProductsDTO> selectBySubCategoryIdOrderByLowPrice(Long subCategory_id);
	
	public List<ProductsDTO> selectByPage(Pager pager);
	public List<ProductsDTO> selectByPageAndSubCategory(Pager pager, @Param("subcategory_id") Long subcategory_id);
	public int count();
	public int specificCount(Long subcategory_id);
	public Long getSequence();
	
	public int deleteProduct(Long product_id);
}

package com.team4.webapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.team4.webapp.dto.CategoriesDTO;

@Mapper
public interface CategoriesDAO {
	public List<CategoriesDTO> selectCategoryList();
	public CategoriesDTO selectCategoryId(Long category_id);
	public int insertCategory(CategoriesDTO categories);
	public int updateCategory(CategoriesDTO categories);
	public int deleteByCategoryId(Long category_id);
}

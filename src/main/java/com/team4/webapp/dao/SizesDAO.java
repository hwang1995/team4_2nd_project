package com.team4.webapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.team4.webapp.dto.SizesDTO;

@Mapper
public interface SizesDAO {
	public List<SizesDTO> selectSizesList();
	public SizesDTO selectBySizeId(Long sizeId);
	public int insertSizes(SizesDTO sizes);
	public int updateSizes(SizesDTO sizes);
	public int deleteBySizeId(Long sizeId);
	public List<SizesDTO> selectByProductId(Long product_id);

}

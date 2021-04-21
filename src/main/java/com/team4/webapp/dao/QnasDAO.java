package com.team4.webapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.team4.webapp.dto.Pager;
import com.team4.webapp.dto.QnasDTO;

@Mapper
public interface QnasDAO {
	public List<QnasDTO> selectQnasList();
	public int insertQnas(QnasDTO qnasdto);
	public QnasDTO selectByQnaId(Long qna_id);
	public int updateQnas(QnasDTO qnasdto);
	public int deleteByQnaId(Long qna_id);
	
	public int count();
	public List<QnasDTO> selectByPage(Pager pager);
	public List<QnasDTO> selectByPageAndId(@Param("pager") Pager pager, @Param("member_id") Long member_id);
	public List<QnasDTO> selectByPageAndCategory(@Param("pager") Pager pager, @Param("qna_category") String qna_category);
	public List<QnasDTO> selectByPageAndAnswer(@Param("pager") Pager pager, @Param("qna_answer") String qna_answer);
}

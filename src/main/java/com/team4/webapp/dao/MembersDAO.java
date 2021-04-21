package com.team4.webapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.team4.webapp.dto.MembersDTO;
import com.team4.webapp.dto.Pager;

@Mapper
public interface MembersDAO {
	public List<MembersDTO> selectMembersList();
	public int insertMembers(MembersDTO membersdto);
	public MembersDTO selectByMemberId(Long member_id);
	public int updateMembers(MembersDTO membersdto);
	public int deleteByMemberId(Long member_id);
	public MembersDTO selectByEmailId(String member_email);
	public int count();
	public List<MembersDTO> selectByPage(Pager pager);
	public List<MembersDTO> selectByPageAndEmail(@Param("pager") Pager pager, @Param("member_email") String member_email);
	public List<MembersDTO> selectByPageAndName(@Param("pager") Pager pager, @Param("member_name") String member_name);
	public int updateMembersByAdmin(MembersDTO memberInfo);
	
	
}

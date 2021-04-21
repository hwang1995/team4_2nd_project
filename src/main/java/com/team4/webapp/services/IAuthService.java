package com.team4.webapp.services;

import java.util.List;

import com.team4.webapp.dto.MembersDTO;

public interface IAuthService {
	
	/**
	 * 회원이 회원가입을 하기 위한 인터페이스
	 * @param MembersDTO member
	 * @return int (영향을 받은 행의 수를 얻기 위하여)
	 */
	int registMember(MembersDTO member);
	
	/**
	 * 회원이 비밀번호 찾기를 위해 이메일, 이름을 입력하여 찾기 위해 제공하는 인터페이스
	 * @param String email
	 * @param String name
	 * @return true or false
	 */
	boolean findMyPassword(String email, String name);
	
	/**
	 * 회원이 회원가입 시에 존재하는 이메일이 있는지를 알기 위해 제공하는 인터페이스
	 * @param String email
	 * @return boolean (true or false)
	 */
	boolean isExistedEmail(String email);
	
	/**
	 * 회원의 이메일을 입력하면 MembersDTO를 전달해준다.
	 * @param String email
	 * @return MembersDTO
	 */
	MembersDTO findMemberbyEmail(String email);
	
	/**
	 * 미구현 사항 - 회원이 탈퇴를 하기 위해 제공하는 인터페이스
	 * @param Long member_id
	 * @return int (영향 받은 행의 수를 얻기 위하여)
	 */
	int removeMember(Long member_id);
	
	/**
	 * 미구현 사항 - 관리자가 회원의 목록을 보기 위해 제공하는 인터페이스
	 * @return List<MembersDTO>
	 */
	List<MembersDTO> showMemberList();
	
	

}

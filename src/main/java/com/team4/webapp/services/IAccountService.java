package com.team4.webapp.services;

import java.util.List;

import com.team4.webapp.dto.MembersDTO;
import com.team4.webapp.dto.MyPageDTO;
import com.team4.webapp.dto.MyPageListDTO;
import com.team4.webapp.dto.OrdersDTO;
import com.team4.webapp.dto.Pager;

public interface IAccountService {
	
	/**
	 * 회원이 자신이 주문한 상세 내역(주문자, 수취인, 상품 리스트(상품, 주문 기록))을 보여주기 위해 제공하는 인터페이스
	 * @param Long member_id
	 * @param Long order_id
	 * @return MyPageDTO (회원 정보, 주문 정보, 상품 디테일의 대한 정보) 포함
	 */
	List<MyPageListDTO> showMyInfo(Pager pager);
	
	/**
	 * 회원의 정보를 보여주기 위해 제공하는 인터페이스
	 * @param Long member_id
	 * @return MembersDTO
	 */
	List<MyPageDTO> showMyOrderInfo(Long member_id);
	
	/**
	 * 회원이 정보를 바꾸기 위해 제공하는 인터페이스
	 * @param MembersDTO member
	 * @return int (영향 받은 행의 수를 받기 위하여)
	 */
	int editMyInfo(MembersDTO member);

	/**
	 * 주문번호로 주문테이블을 보여주기 위해 제공하는 인터페이스
	 * @param Long order_id
	 * @return OrdersDTO
	 */
	OrdersDTO findOrderbyOrderId(Long order_id);
	
	/**
	 * 페이징에 필요한 총 행의 count를 구하기 위해 제공하는 인터페이스
	 * @param Long member_id
	 * @return int (카운트 값을 얻기 위해)
	 */
	int getTotalOrderRows(Long member_id);
	
	/**
	 * 회원 목록 + 페이저로 리스트를 받기 위해 제공하는 인터페이스
	 * @param Pager pager
	 * @return List<MembersDTO>
	 */
	List<MembersDTO> getAccountsList(Pager pager);
	
	/**
	 * 회원 목록 + 이메일 주소 + 페이저로 리스트를 받기 위해 제공하는 인터페이스
	 * @param Pager pager
	 * @param String email
	 * @return List<MembersDTO>
	 */
	List<MembersDTO> getAccountsListByEmail(Pager pager, String email);
	
	/**
	 * 회원 목록 + 이름 + 페이저로 리스트를 받기 위해 제공하는 인터페이스
	 * @param pager
	 * @param name
	 * @return List<MembersDTO>
	 */
	List<MembersDTO> getAccountsListByName(Pager pager, String name);
	
	/**
	 * 회원 정보를 수정하기 위해 제공하는 인터페이스
	 * @param MembersDTO memberInfo
	 * @return boolean
	 */
	boolean modifyAccount(MembersDTO memberInfo);
	
	/**
	 * 회원을 삭제하기 위해 제공하는 인터페이스
	 * @param Long member_id
	 * @return boolean
	 */
	boolean deleteAccount(Long member_id);
	
	/**
	 * 페이징에 필요한 회원 전체 행의 count를 구하기 위해 제공하는 인터페이스
	 * @return int (카운트 값을 얻기 위해)
	 */
	int getTotalMemberRows();
	
	/**
	 * 페이징에서 이메일로 검색한 회원 행의 count를 구하기 위해 제공하는 인터페이스
	 * @param String email
	 * @return int (카운트 값을 얻기 위해)
	 */
	int getMemberRowsByEmail(String email);
	
	/**
	 * 페이징에 이름으로 검색한 회원 행의 count를 구하기 위해 제공하는 인터페이스
	 * @param String name
	 * @return int (카운트 값을 얻기 위해)
	 */
	int getMemberRowsByName(String name);
}

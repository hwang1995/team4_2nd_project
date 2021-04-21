package com.team4.webapp.services;




import java.util.List;

import com.team4.webapp.dto.Pager;
import com.team4.webapp.dto.QnasDTO;

public interface IQnaService {
	/**
	 * 회원이 상품, 배송, 교환, 기타 문의에 대해 관리자에게 남길 수 있도록 제공하는 인터페이스
	 * @param Long member_id
	 * @param QnasDTO qna
	 * @return int (insert가 성공적으로 되었는가?)
	 */
	int writeQna(String member_email, QnasDTO qna);
	
	/**
	 * Q&A 목록 + 페이저로 리스트를 받기 위해 제공하는 인터페이스
	 * @param Pager pager
	 * @return List<QnasDTO>
	 */
	List<QnasDTO> getQnasList(Pager pager);
	
	/**
	 * Q&A 목록 + 회원 ID + 페이저로 리스트를 받기 위해 제공하는 인터페이스
	 * @param Pager pager
	 * @param String email
	 * @return List<QnasDTO>
	 */
	List<QnasDTO> getQnasListById(Pager pager, Long member_id);
	
	/**
	 * Q&A 목록 + 카테고리 + 페이저로 리스트를 받기 위해 제공하는 인터페이스
	 * @param Pager pager
	 * @param String category
	 * @return List<QnasDTO>
	 */
	List<QnasDTO> getQnasListByCategory(Pager pager, String category);
	
	/**
	 * Q&A 목록 + 답변 + 페이저로 리스트를 받기 위해 제공하는 인터페이스
	 * @param Pager pager
	 * @param String answer
	 * @return List<QnasDTO>
	 */
	List<QnasDTO> getQnasListByAnswer(Pager pager, String answer);
	
	/**
	 * Q&A를 수정하기 위해 제공하는 인터페이스
	 * @param QnasDTO qnaInfo
	 * @return boolean
	 */
	boolean modifyQna(QnasDTO qnaInfo);
	
	/**
	 * Q&A를 삭제하기 위해 제공하는 인터페이스
	 * @param QnasDTO qnaInfo
	 * @return boolean
	 */
	boolean deleteQna(QnasDTO qnaInfo);
}

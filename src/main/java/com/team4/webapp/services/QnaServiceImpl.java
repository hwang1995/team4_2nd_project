package com.team4.webapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team4.webapp.dao.MembersDAO;
import com.team4.webapp.dao.QnasDAO;
import com.team4.webapp.dto.Pager;
import com.team4.webapp.dto.QnasDTO;

@Service
public class QnaServiceImpl implements IQnaService {
	@Autowired
	private QnasDAO qnaDAO;
	@Autowired
	private MembersDAO memberDAO;
	
	
	/**
	 * 서비스 목적
	 * - 회원이 상품, 배송, 교환, 기타 문의에 대하여
	 * - 관리자에게 남길 수 있도록 제공하는 서비스
	 * @param String member_email (회원의 이메일 주소)
	 * @param QnasDTO qna (Q&A 정보)
	 * @return 행의 수
	 */
	@Override
	public int writeQna(String member_email, QnasDTO qna) {
		/**
		 * 1. QnA 테이블을 넘기기 이전에 qna 객체에
		 * answer = "답변완료"
		 * member_id = Long member_id로 설정
		 */
		qna.setQna_answer("답변완료");
		qna.setMember_id(memberDAO.selectByEmailId(member_email).getMember_id());
		
		// 2. QnasDAO의 insertQnas(QnasDTo qna)를 전달하고 int 값을 반환
		// 3. 만약 반환 받은 값이 1이 아니라면? Exception throw
		// 4. 만약 성공했다면 row를 전달한다.
		int row = qnaDAO.insertQnas(qna);

		return row;
	}


	@Override
	public List<QnasDTO> getQnasList(Pager pager) {
		return null;
	}


	@Override
	public List<QnasDTO> getQnasListById(Pager pager, Long member_id) {
		return null;
	}


	@Override
	public List<QnasDTO> getQnasListByCategory(Pager pager, String category) {
		return null;
	}


	@Override
	public List<QnasDTO> getQnasListByAnswer(Pager pager, String answer) {
		return null;
	}


	@Override
	public boolean modifyQna(QnasDTO qnaInfo) {
		return false;
	}


	@Override
	public boolean deleteQna(QnasDTO qnaInfo) {
		return false;
	}

}

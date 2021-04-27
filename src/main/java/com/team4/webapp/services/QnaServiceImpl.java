package com.team4.webapp.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team4.webapp.dao.MembersDAO;
import com.team4.webapp.dao.OrderlistsDAO;
import com.team4.webapp.dao.OrdersDAO;
import com.team4.webapp.dao.ProductsDAO;
import com.team4.webapp.dao.QnasDAO;
import com.team4.webapp.dto.MyPageDTO;
import com.team4.webapp.dto.OrderDateSumDTO;
import com.team4.webapp.dto.OrderlistsDTO;
import com.team4.webapp.dto.OrdersDTO;
import com.team4.webapp.dto.Pager;
import com.team4.webapp.dto.ProductsDTO;
import com.team4.webapp.dto.QnaMembersDTO;
import com.team4.webapp.dto.QnasDTO;

@Service
public class QnaServiceImpl implements IQnaService {
	@Autowired
	private QnasDAO qnaDAO;
	@Autowired
	private MembersDAO memberDAO;
	@Autowired
	private OrdersDAO ordersDAO;
	@Autowired
	private OrderlistsDAO orderlistsDAO;
	@Autowired
	private ProductsDAO productsDAO;
	
	
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

	/**
	 * 서비스 목적
	 * - Q&A의 모든 리스트를 가져오기 위해 제공하는 서비스
	 * @param Pager pager
	 * @return List<QnaMembersDTO>
	 */
	@Override
	public List<QnaMembersDTO> getQnasList(Pager pager) {
		List<QnaMembersDTO> qnaMemberList = new ArrayList<QnaMembersDTO>();
		List<QnasDTO> qnaList = qnaDAO.selectByPage(pager);
		for(QnasDTO qna : qnaList) {
			QnaMembersDTO qnaMember = new QnaMembersDTO();
			qnaMember.setQnasInfo(qna);
			qnaMember.setMember_email(memberDAO.selectByMemberId(qna.getMember_id()).getMember_email());
			settingQna(qnaMember);
			qnaMemberList.add(qnaMember);
		}
		return qnaMemberList;
	}

	/**
	 * 서비스 목적
	 * - Q&A의 member_email과 일치하는 리스트를 가져오기 위해 제공하는 서비스
	 * @param Pager pager
	 * @param String member_email
	 * @return List<QnaMembersDTO>
	 */
	@Override
	public List<QnaMembersDTO> getQnasListById(Pager pager, String member_email) {
		List<QnaMembersDTO> qnaMemberList = new ArrayList<QnaMembersDTO>();
		try {
			Long member_id = memberDAO.selectByEmailId(member_email).getMember_id();
			List<QnasDTO> qnaList = qnaDAO.selectByPageAndId(pager, member_id);
			for(QnasDTO qna : qnaList) {
				QnaMembersDTO qnaMember = new QnaMembersDTO();
				qnaMember.setQnasInfo(qna);
				qnaMember.setMember_email(member_email);
				settingQna(qnaMember);
				qnaMemberList.add(qnaMember);
			}
		} catch(Exception e) {
			
		}
		return qnaMemberList;
	}

	/**
	 * 서비스 목적
	 * - Q&A의 category와 일치하는 리스트를 가져오기 위해 제공하는 서비스
	 * @param Pager pager
	 * @param String category
	 * @return List<QnaMembersDTO>
	 */
	@Override
	public List<QnaMembersDTO> getQnasListByCategory(Pager pager, String category) {
		List<QnaMembersDTO> qnaMemberList = new ArrayList<QnaMembersDTO>();
		try {
			List<QnasDTO> qnaList = qnaDAO.selectByPageAndCategory(pager, category);
			for(QnasDTO qna : qnaList) {
				QnaMembersDTO qnaMember = new QnaMembersDTO();
				qnaMember.setQnasInfo(qna);
				qnaMember.setMember_email(memberDAO.selectByMemberId(qna.getMember_id()).getMember_email());
				settingQna(qnaMember);
				qnaMemberList.add(qnaMember);
			}
		} catch(Exception e) {
			
		}
		return qnaMemberList;
	}

	/**
	 * 서비스 목적
	 * - Q&A의 answer과 일치하는 리스트를 가져오기 위해 제공하는 서비스
	 * @param Pager pager
	 * @param String answer
	 * @return List<QnaMembersDTO>
	 */
	@Override
	public List<QnaMembersDTO> getQnasListByAnswer(Pager pager, String answer) {
		List<QnaMembersDTO> qnaMemberList = new ArrayList<QnaMembersDTO>();
		try {
			List<QnasDTO> qnaList = qnaDAO.selectByPageAndAnswer(pager, answer);
			for(QnasDTO qna : qnaList) {
				QnaMembersDTO qnaMember = new QnaMembersDTO();
				qnaMember.setQnasInfo(qna);
				qnaMember.setMember_email(memberDAO.selectByMemberId(qna.getMember_id()).getMember_email());
				settingQna(qnaMember);
				qnaMemberList.add(qnaMember);
			}
		} catch(Exception e) {
			
		}
		return qnaMemberList;
	}
	
	/**
	 * 서비스 목적
	 * - Q&A의 답변이 된 것들의 리스트를 가져오기 위해 제공하는 서비스
	 * @param Pager pager
	 * @param String answer
	 * @return List<QnaMembersDTO>
	 */
	@Override
	public List<QnaMembersDTO> getQnasListByFinishedAnswer(Pager pager, String answer) {
		List<QnaMembersDTO> qnaMemberList = new ArrayList<QnaMembersDTO>();
		try {
			List<QnasDTO> qnaList = qnaDAO.selectByPageAndFinishedAnswer(pager, answer);
			for(QnasDTO qna : qnaList) {
				QnaMembersDTO qnaMember = new QnaMembersDTO();
				qnaMember.setQnasInfo(qna);
				qnaMember.setMember_email(memberDAO.selectByMemberId(qna.getMember_id()).getMember_email());
				settingQna(qnaMember);
				qnaMemberList.add(qnaMember);
			}
		} catch(Exception e) {
			
		}
		return qnaMemberList;
	}
	
	//QnaMembersDTO에 답변상태와 분류를 세팅해줌
	public void settingQna(QnaMembersDTO qnaMember) {
		if(qnaMember.getQna_answer().equals("답변중")) {
			qnaMember.setAnswer_status("ⓧ");
		} else {
			qnaMember.setAnswer_status("ⓞ");
		}
		if(qnaMember.getQna_category().equals("products")) {
			qnaMember.setCategory_status("상품문의");
		} else if(qnaMember.getQna_category().equals("delivery")) {
			qnaMember.setCategory_status("배송문의");
		} else if(qnaMember.getQna_category().equals("exchange")) {
			qnaMember.setCategory_status("교환문의");
		} else {
			qnaMember.setCategory_status("기타문의");
		}
	}

	/**
	 * 서비스 목적
	 * - qna_id로 Q&A의 객체를 가져오기 위해 제공하는 서비스
	 * @param Long qna_id
	 * @return QnaMembersDTO
	 */
	@Override
	public QnaMembersDTO getQnaById(Long qna_id) {
		QnasDTO qna = qnaDAO.selectByQnaId(qna_id);
		QnaMembersDTO qnaMember = new QnaMembersDTO();
		qnaMember.setQnasInfo(qna);
		qnaMember.setMember_email(memberDAO.selectByMemberId(qna.getMember_id()).getMember_email());
		return qnaMember;
	}

	/**
	 * 서비스 목적
	 * -  Q&A를 수정하기 위해 제공하는 서비스
	 * @param QnasDTO qnaInfo
	 */
	@Override
	public boolean modifyQna(QnasDTO qnaInfo) {
		int row = qnaDAO.updateQnas(qnaInfo);
		if(row != 1) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 서비스 목적
	 * -  Q&A를 삭제하기 위해 제공하는 서비스
	 * @param Long qna_id
	 */
	@Override
	public boolean deleteQna(Long qna_id) {
		int row = qnaDAO.deleteByQnaId(qna_id);
		if(row != 1) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public int getCount() {
		int row = qnaDAO.count();
		return row;
	}
	
	@Override
	public int getCountByEmail(String member_email) {
		try {
			Long member_id = memberDAO.selectByEmailId(member_email).getMember_id();
			int row = qnaDAO.countByMemberId(member_id);
			return row;
		} catch(Exception e) {
			return 0;
		}
	}

	@Override
	public int getCountByCategory(String qna_category) {
		try {
			int row = qnaDAO.countByCategory(qna_category);
			return row;
		} catch(Exception e) {
			return 0;
		}
	}

	@Override
	public int getCountByAnswer(String qna_answer) {
		try {
			int row = qnaDAO.countByAnswer(qna_answer);
			return row;
		} catch(Exception e) {
			return 0;
		}
	}

	@Override
	public int getCountByFinishedAnswer(String qna_answer) {
		try {
			int row = qnaDAO.countByFinishedAnswer(qna_answer);
			return row;
		} catch(Exception e) {
			return 0;
		}
	}
	
	

}

package com.team4.webapp.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team4.webapp.dto.Pager;
import com.team4.webapp.dto.QnaMembersDTO;
import com.team4.webapp.dto.QnasDTO;
import com.team4.webapp.services.QnaServiceImpl;

@RestController
@RequestMapping("/api/qna")
public class QnasController {
	
	private static final Logger logger = LoggerFactory.getLogger(QnasController.class);
	
	@Autowired
	private QnaServiceImpl qnasService;
	
	// Q&A 목록을 보여줌. selectOption 별로 검색을 하여 목록을 보여줌.
	@GetMapping("")
	public Map<String, Object> getQnasList(@RequestParam(defaultValue = "1") int pageNo, String member_email, String qna_category, String qna_answer) {
		int totalRows;
		Pager pager = null;
		List<QnaMembersDTO> list = new ArrayList<>();
		
		// email이 들어왔다면 email로 리스트를 가져옴
		if(member_email != null) {
			totalRows = qnasService.getCountByEmail(member_email);
			pager = new Pager(10, 5, totalRows, pageNo);
			list = qnasService.getQnasListById(pager, member_email);
		} else if (qna_category != null) {	//category로 리스트 가져옴
			totalRows = qnasService.getCountByCategory(qna_category);
			pager = new Pager(10, 5, totalRows, pageNo);
			list = qnasService.getQnasListByCategory(pager, qna_category);
		} else if (qna_answer != null) {
			if(qna_answer.equals("답변중")) {		//Q&A 답변이 안된 리스트를 가져옴
				totalRows = qnasService.getCountByAnswer(qna_answer);
				pager = new Pager(10, 5, totalRows, pageNo);
				list = qnasService.getQnasListByAnswer(pager, qna_answer);
			} else {	//Q&A 답변이 된 리스트를 가져옴
				qna_answer="답변중";
				totalRows = qnasService.getCountByFinishedAnswer(qna_answer);
				pager = new Pager(10, 5, totalRows, pageNo);
				list = qnasService.getQnasListByFinishedAnswer(pager, qna_answer);
			}
		} else {	//모든 Q&A 리스트를 가져옴
			totalRows = qnasService.getCount();
			pager = new Pager(10, 5, totalRows, pageNo);
			list = qnasService.getQnasList(pager);
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("qnas", list);
		map.put("pager", pager);
		
		return map;
	}
	
	//qna_id로 해당하는 객체를 가져옴
	@GetMapping("/{qna_id}")
	public QnaMembersDTO getQna(@PathVariable Long qna_id) {
		QnaMembersDTO qna = qnasService.getQnaById(qna_id);
		return qna;
	}
	
	//Q&A를 update 시켜줌
	@PutMapping("")
	public QnasDTO modifyQna(@RequestBody QnasDTO qna, HttpServletResponse response) {
		boolean result = qnasService.modifyQna(qna);
		
		if(result) {
			return qna;
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
	}
	
	//Q&A를 delete 함
	@DeleteMapping("/{qna_id}")
	public void deleteQna(@PathVariable Long qna_id, HttpServletResponse response) {
		boolean result = qnasService.deleteQna(qna_id);
		
		if(!result) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}
	
}

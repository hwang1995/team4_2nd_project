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
	
	@GetMapping("")
	public Map<String, Object> getQnasList(@RequestParam(defaultValue = "1") int pageNo, String member_email, String qna_category, String qna_answer) {
		int totalRows;
		Pager pager = null;
		List<QnaMembersDTO> list = new ArrayList<>();
		if(member_email != null) {
			totalRows = qnasService.getCountByEmail(member_email);
			pager = new Pager(10, 5, totalRows, pageNo);
			list = qnasService.getQnasListById(pager, member_email);
		} else if(qna_category != null) {
			totalRows = qnasService.getCountByCategory(qna_category);
			pager = new Pager(10, 5, totalRows, pageNo);
			list = qnasService.getQnasListByCategory(pager, qna_category);
		} else if(qna_answer != null) {
			if(qna_answer.equals("답변중")) {
				totalRows = qnasService.getCountByAnswer(qna_answer);
				pager = new Pager(10, 5, totalRows, pageNo);
				list = qnasService.getQnasListByAnswer(pager, qna_answer);
			} else {
				qna_answer="답변중";
				totalRows = qnasService.getCountByFinishedAnswer(qna_answer);
				pager = new Pager(10, 5, totalRows, pageNo);
				list = qnasService.getQnasListByFinishedAnswer(pager, qna_answer);
			}
		} else {
			totalRows = qnasService.getCount();
			pager = new Pager(10, 5, totalRows, pageNo);
			list = qnasService.getQnasList(pager);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("qnas", list);
		map.put("pager", pager);
		return map;
	}
	
	@GetMapping("/{qna_id}")
	public QnaMembersDTO getQna(@PathVariable Long qna_id) {
		QnaMembersDTO qna = qnasService.getQnaById(qna_id);
		return qna;
	}
	
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
	
	@DeleteMapping("/{qna_id}")
	public void deleteQna(@PathVariable Long qna_id, HttpServletResponse response) {
		boolean result = qnasService.deleteQna(qna_id);
		if(!result) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
	}
}

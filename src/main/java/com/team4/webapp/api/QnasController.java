package com.team4.webapp.api;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team4.webapp.dto.QnasDTO;

@RestController
@RequestMapping("/api/qna")
public class QnasController {
	
	private static final Logger logger = LoggerFactory.getLogger(QnasController.class);
	
	@GetMapping("")
	public Map<String, Object> getQnasList(@RequestParam(defaultValue = "1") int pageNo) {
		return null;
	}
	
	@GetMapping("/{qna_id}")
	public QnasDTO getQna(@PathVariable Long qna_id) {
		return null;
	}
	
	@PutMapping("")
	public QnasDTO modifyQna(@RequestBody QnasDTO qna) {
		return null;
	}
	
	@DeleteMapping("/{qna_id}")
	public void deleteQna(@PathVariable Long qna_id) {
		
	}

}

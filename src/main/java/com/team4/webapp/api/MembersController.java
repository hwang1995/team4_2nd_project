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

import com.team4.webapp.dto.MembersDTO;

@RestController
@RequestMapping("/api/members")
public class MembersController {
	
	private static final Logger logger = LoggerFactory.getLogger(MembersController.class);
	
	@GetMapping("")
	public Map<String, Object> getMembersList(@RequestParam(defaultValue = "1") int pageNo, String email, String name){
		return null;
	}
	
	@PutMapping("/")
	public void modifyMembersInfo(@RequestBody MembersDTO memberInfo) {
		
	}
	
	@DeleteMapping("/{member_id}")
	public void deleteMember(@PathVariable Long member_id) {
		
	}

}

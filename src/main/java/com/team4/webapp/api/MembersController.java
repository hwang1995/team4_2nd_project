package com.team4.webapp.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.team4.webapp.dto.MembersDTO;
import com.team4.webapp.dto.Pager;
import com.team4.webapp.services.AccountServiceImpl;

@RestController
@RequestMapping("/api/members")
public class MembersController {
	
	@Autowired
	private AccountServiceImpl accountServiceImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(MembersController.class);
	
	@GetMapping("")
	public Map<String, Object> getMembersList(@RequestParam(defaultValue = "1") int pageNo, String email, String name){
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<MembersDTO> list = null;
		Pager pager = null; 
		
		if(email == null && name == null) {
			int totalRows = accountServiceImpl.getTotalMemberRows();
			pager = new Pager(5, 5, totalRows, pageNo);
			list = accountServiceImpl.getAccountsList(pager);
		}else if(email != null && name == null) {
			int totalRows = accountServiceImpl.getMemberRowsByEmail(email);
			pager = new Pager(5, 5, totalRows, pageNo);
			list = accountServiceImpl.getAccountsListByEmail(pager, email);
		}else if(email == null && name != null) {
			int totalRows = accountServiceImpl.getMemberRowsByName(name);
			pager = new Pager(5, 5, totalRows, pageNo);
			list = accountServiceImpl.getAccountsListByName(pager, name);
		}
		map.put("pager", pager);
		map.put("list", list);
		return map;
	}
	
	@PutMapping("")
	public void modifyMembersInfo(@RequestBody MembersDTO memberInfo) {
		boolean modifiedMember = accountServiceImpl.modifyAccount(memberInfo);
		if(modifiedMember == true) {
			logger.info("수정 완료");
		}else {
			logger.info("수정 오류");
		}
	}
	
	@DeleteMapping("/{member_id}")
	public void deleteMember(@PathVariable Long member_id) {
		boolean deletedMember = accountServiceImpl.deleteAccount(member_id);
		if(deletedMember == true) {
			logger.info("삭제 완료");
		}else {
			logger.info("삭제 오류");
		}
	}

}

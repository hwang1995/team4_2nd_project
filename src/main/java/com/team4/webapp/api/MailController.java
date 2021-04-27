package com.team4.webapp.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team4.webapp.dto.MailDTO;
import com.team4.webapp.services.MailService;

@RestController
@RequestMapping("/api/mail")
public class MailController {
	private static final Logger logger = LoggerFactory.getLogger(MailController.class);
	
	@Autowired
	private MailService mailService;
	
	/**
	 * 이메일을 보내기 위해 필요한 컨트롤러
	 * @param MailDTO mailDTO
	 */
	@PostMapping("")
	public void execMail(@RequestBody MailDTO mailDTO) {
		mailService.mailSend(mailDTO);
	}
	

}

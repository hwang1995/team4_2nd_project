package com.team4.webapp.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.team4.webapp.dto.MailDTO;

@Service
public class MailService {
	
	private static final Logger logger = LoggerFactory.getLogger(MailService.class);
	
	@Autowired
	private JavaMailSender mailSender;
	
	private static final String FROM_ADDRESS = "byeongju3121@gmail.com";
	
	public void mailSend(MailDTO mailDTO) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(mailDTO.getAddress());
		message.setFrom(MailService.FROM_ADDRESS);
		message.setSubject(mailDTO.getTitle());
		message.setText(mailDTO.getMessage());
		
		mailSender.send(message);
		
	}
	
	

}

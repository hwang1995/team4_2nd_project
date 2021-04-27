package com.team4.webapp.api;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team4.webapp.dto.MembersDTO;
import com.team4.webapp.security.JwtUtil;
import com.team4.webapp.services.AuthServiceImpl;
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private AuthServiceImpl authServiceImpl;
	
	/**
	 * 관리자 서비스를 이용하기 위해 로그인시 필요한 컨트롤러(로그인 시 들어온 회원의 정보를 jwt를 사용하여 로그인 서비스)
	 * @param userInfo 
	 * @return Map
	 */
	@PostMapping("/login")
	public Map<String, String> login(@RequestBody Map<String, String> userInfo){
		// 인증 데이터 얻기
		String email = userInfo.get("email");
		String password = userInfo.get("password");
		
		// 사용자 인증
		UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(email, password);
		Authentication authentication = authenticationManager.authenticate(upat);
		
		// Spring Security에 인증 객체 등록
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		// JWT 생성
		String jwtToken = JwtUtil.createToken(email);
		
		// JSON 응답 보내기
		Map<String, String> map = new HashMap<>();
		map.put("email", email);
		map.put("authToken", jwtToken);
		
		return map;
	}
	
	/**
	 * 관리자 서비스를 사용하기 위해 회원가입하기 위한 컨트롤러
	 * @param memberInfo 
	 */
	@PostMapping("/register")
	public void register(@RequestBody MembersDTO memberInfo) {
		int row = authServiceImpl.registAdminMember(memberInfo);
		
		if(row != 0) {
			logger.info("회원가입 성공");
		} else {
			logger.info("회원가입 실패");
		}
	}
	/**
	 * 회원가입시에 존재하는 이메일인지 확인하기 위해 사용하는 컨트롤러
	 * @param MembersDTO member
	 * @return boolean
	 */
	@PostMapping("/existed-email")
	public boolean isExistedEmail(@RequestBody MembersDTO member) {
		String email = member.getMember_email();
		
		if(email != "") {
			boolean result = authServiceImpl.isExistedEmail(email);
			return result;
		} else {
			return false;
		}
	}
}

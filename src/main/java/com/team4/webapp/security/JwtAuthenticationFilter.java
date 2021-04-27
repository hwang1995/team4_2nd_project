package com.team4.webapp.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.GenericFilterBean;

public class JwtAuthenticationFilter extends GenericFilterBean {
	// 사용자의 상세 정보를 가진 서비스를 여기서 받아옴.
	private UserDetailsService userDetailsService;
	
	public JwtAuthenticationFilter(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// JWT 토큰이 요청 헤더로 전송된 경우
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		// authToken이라는 헤더로 Request가 날라온 경우
		String jwtToken = httpRequest.getHeader("authToken");

		
		if(jwtToken == null) {
			// JWT가 요청 파라미터로 전달된 경우 받아야 한다.
			jwtToken = request.getParameter("authToken");
		}
		
		if(jwtToken != null) {
			if(JwtUtil.validateToken(jwtToken)) {
				// JWT에서 EMAIL 얻기
				String email = JwtUtil.getEmail(jwtToken);

				
				// DB에서 EMAIL에 해당하는 정보를 가져오기(이름, 비밀번호, 권한들)
				UserDetails userDetails = userDetailsService.loadUserByUsername(email);
				
				// 인증 성공시 인증 객체를 만든다.
				Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
				
				// Spring Security에 인증 객체를 등록
				SecurityContextHolder.getContext().setAuthentication(authentication);
				
			}
			
		}
		chain.doFilter(request, response);
		
		
	}
}

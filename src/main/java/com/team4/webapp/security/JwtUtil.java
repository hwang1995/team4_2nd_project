package com.team4.webapp.security;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {
	// 비밀 키
	private static final String secretKey = "team4project";
	private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);
	private static final Long TOKEN_VALID_TIME = 1000 * 60 * 60 * 12L; // 12시간 유효
	
	// JWT 생성 : 인증된 사용자 아이디만 포함
	public static String createToken(String email) {
		String token = null;
		
		try {
			JwtBuilder builder = Jwts.builder();
			builder.setHeaderParam("typ", "JWT");
			builder.setHeaderParam("alg", "HS256");
			builder.setExpiration(new Date(new Date().getTime() + TOKEN_VALID_TIME));
			builder.claim("email", email);
			builder.signWith(SignatureAlgorithm.HS256, secretKey.getBytes("UTF-8"));
			token = builder.compact();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return token;
		
	}
	
	// JWT에서 email 얻기
	public static String getEmail(String token) {
		String email = null;
		try {
			JwtParser parser = Jwts.parser();
			parser.setSigningKey(secretKey.getBytes("UTF-8"));
			Jws<Claims> jws = parser.parseClaimsJws(token);
			Claims claims = jws.getBody();
			email = claims.get("email", String.class);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return email;
	}
	
	// JWT의 유효성 검사 : 유효기간 확인
	public static boolean validateToken(String token) {
		boolean validate = false;
		
		try {
			JwtParser parser = Jwts.parser();
			parser.setSigningKey(secretKey.getBytes("UTF-8"));
			Jws<Claims> jws = parser.parseClaimsJws(token);
			Claims claims = jws.getBody();
			validate = claims.getExpiration().after(new Date());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return validate;
	}
	

}

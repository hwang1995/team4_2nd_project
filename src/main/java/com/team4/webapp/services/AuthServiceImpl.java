package com.team4.webapp.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.team4.webapp.dao.MembersDAO;
import com.team4.webapp.dto.MembersDTO;

@Service
public class AuthServiceImpl implements IAuthService {
	@Autowired
	private MembersDAO membersDAO;
	
	// 로거 설정
	private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
	/**
	 * 서비스 목적
	 * - 회원이 주문, 장바구니, QnA, 마이 페이지 등을 활용하기 위해 가입하는 서비스
	 * - 컨트롤러에 영향받은 행의 수를 전달해야 한다.
	 * @return 행의 수 (정상적으로 되었는지 알기 위해)
	 */
	@Override
	public int registMember(MembersDTO member) {
		// 1. MembersDAO의 insertMembers(member)를 전달한다.
		// 2. 영향받은 행의 수를 전달한다.
		String encodedPassword = pwEncoder(member.getMember_pw());
		member.setMember_authority("ROLE_USER");
		member.setMember_enabled(true);
		member.setMember_pw(encodedPassword);
		
		int row = membersDAO.insertMembers(member);
		return row;
	}

	/**
	 * 서비스 목적
	 * - 회원이 비밀번호를 찾기 위해 사용하는 서비스
	 * - 컨트롤러에 성공했는지, 실패했는지의 boolean 값을 전달해야 한다.
	 * @return boolean (true or false)
	 */
	@Override
	public boolean findMyPassword(String email, String name) {
		// 1. MembersDAO의 selectByMemberEmail(String email)을 호출한다.
		// 2. 객체에서 이메일을 가져온 후 클라이언트의 이메일과 동등한지 확인
		// 3. 만약 동등하지 않다면 return false;
		// 4. 객체에서 이름을 가져온 후 클라이언트의 이름과 동등한지 확인
		// 5. 만약 동등하지 않다면 return false;
		// 6. 둘다 동등하다면 MembersDTO에 암호화한 비밀번호 "1q2w3e4r"을 넣어준다.
		// 7. MembersDAO에 updateMembers에 멤버 객체를 전달한다.
		try{
			MembersDTO member = membersDAO.selectByEmailId(email);
			if(member.getMember_name().equals(name)) {
				String encodedPassword = pwEncoder("1q2w3e4r");
				member.setMember_pw(encodedPassword);
				int row = membersDAO.updateMembers(member);
				if(row != 1) {
					return false;
				}
			}else {
				return false;
			}
		} catch(NullPointerException e){
			return false;
		}
		return true;
	}

	/**
	 * 미구현 사항
	 * - 관리자가 회원을 삭제하기 위해 사용하는 서비스
	 * - 컨트롤러에 영향받은 행의 수를 전달한다.
	 */
	@Override
	public int removeMember(Long member_id) {
		return 0;
	}
	
	/**
	 * 미구현 사항
	 * - 관리자가 회원의 목록을 보기 위해 사용하는 서비스
	 * - 컨트롤러의 회원의 목록을 전달한다.
	 */
	@Override
	public List<MembersDTO> showMemberList() {
		return null;
	}
	
	/**
	 * 서비스 목적
	 * - 회원이 회원가입시에 이메일이 중복되는지 체크하기 위한 서비스
	 * - 컨트롤러에 true or false를 전달한다. 
	 * @return boolean (true or false)
	 */
	@Override
	public boolean isExistedEmail(String email) {
		try{
			MembersDTO member = membersDAO.selectByEmailId(email);
			logger.info(member.toString());
		} catch(NullPointerException e){
			return false;
		}
		return true;
	}

	/**
	 * 서비스 목적
	 * - 회원의 mypage에서 회원정보를 전달하기 위한 서비스
	 * - Account컨트롤러에 찾은 MemberDTO를 전달한다.
	 * @return MembersDTO (회원 정보) 
	 */
	@Override
	public MembersDTO findMemberbyEmail(String email) {
		MembersDTO member = membersDAO.selectByEmailId(email);
		return member;
	}
	
	/**
	 * 비밀번호를 암호화 하기 위해 사용하는 메서드
	 * @param String password (plainText)
	 * @return String (saltedText)
	 */
	private String pwEncoder(String password) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(password);
		return encodedPassword;
	}

}

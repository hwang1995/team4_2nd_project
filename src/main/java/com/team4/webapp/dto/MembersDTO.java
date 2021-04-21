package com.team4.webapp.dto;

public class MembersDTO {
	private Long member_id;
	private String member_email;
	private String member_name;
	private String member_pw;
	private String member_tel;
	private String member_address;
	private String member_authority;
	private boolean member_enabled;
	
	// Getters & Setters
	public Long getMember_id() {
		return member_id;
	}
	public void setMember_id(Long member_id) {
		this.member_id = member_id;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getMember_pw() {
		return member_pw;
	}
	public void setMember_pw(String member_pw) {
		this.member_pw = member_pw;
	}
	public String getMember_tel() {
		return member_tel;
	}
	public void setMember_tel(String member_tel) {
		this.member_tel = member_tel;
	}
	public String getMember_address() {
		return member_address;
	}
	public void setMember_address(String member_address) {
		this.member_address = member_address;
	}
	public String getMember_authority() {
		return member_authority;
	}
	public void setMember_authority(String member_authority) {
		this.member_authority = member_authority;
	}
	public boolean isMember_enabled() {
		return member_enabled;
	}
	public void setMember_enabled(boolean member_enabled) {
		this.member_enabled = member_enabled;
	}
	
	// For Testing purpose -> DTO의 값을 한번에 보고 싶을 때 사용
	@Override
	public String toString() {
		return "MembersDTO [member_id=" + member_id + ", member_email=" + member_email + ", member_name=" + member_name
				+ ", member_pw=" + member_pw + ", member_tel=" + member_tel + ", member_address=" + member_address
				+ ", member_authority=" + member_authority + ", member_enabled=" + member_enabled + "]";
	}
	
	

}

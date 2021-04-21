package com.team4.webapp.dto;

public class QnasDTO {
	private Long qna_id;
	private String qna_category;
	private String qna_title;
	private String qna_content;
	private String qna_answer;
	private Long member_id;
	
	// Getters & Setters
	public Long getQna_id() {
		return qna_id;
	}
	public void setQna_id(Long qna_id) {
		this.qna_id = qna_id;
	}
	public String getQna_category() {
		return qna_category;
	}
	public void setQna_category(String qna_category) {
		this.qna_category = qna_category;
	}
	public String getQna_title() {
		return qna_title;
	}
	public void setQna_title(String qna_title) {
		this.qna_title = qna_title;
	}
	public String getQna_content() {
		return qna_content;
	}
	public void setQna_content(String qna_content) {
		this.qna_content = qna_content;
	}
	public String getQna_answer() {
		return qna_answer;
	}
	public void setQna_answer(String qna_answer) {
		this.qna_answer = qna_answer;
	}
	public Long getMember_id() {
		return member_id;
	}
	public void setMember_id(Long member_id) {
		this.member_id = member_id;
	}
	
	// For Testing purpose -> DTO의 값을 한번에 보고 싶을 때 사용
	@Override
	public String toString() {
		return "QnasDTO [qna_id=" + qna_id + ", qna_category=" + qna_category + ", qna_title=" + qna_title
				+ ", qna_content=" + qna_content + ", qna_answer=" + qna_answer + ", member_id=" + member_id + "]";
	}
	
	

}

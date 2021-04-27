package com.team4.webapp.dto;

public class QnaMembersDTO {
	private Long qna_id;
	private String qna_category;
	private String qna_title;
	private String qna_content;
	private String qna_answer;
	private Long member_id;
	private String member_email;
	private String answer_status;
	private String category_status;
	
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
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public String getAnswer_status() {
		return answer_status;
	}
	public void setAnswer_status(String answer_status) {
		this.answer_status = answer_status;
	}
	public String getCategory_status() {
		return category_status;
	}
	public void setCategory_status(String category_status) {
		this.category_status = category_status;
	}
	
	public void setQnasInfo(QnasDTO qna) {
		this.qna_id = qna.getQna_id();
		this.qna_category = qna.getQna_category();
		this.qna_title = qna.getQna_title();
		this.qna_content = qna.getQna_content();
		this.qna_answer = qna.getQna_answer();
		this.member_id = qna.getMember_id();
	}
	
	// For Testing purpose -> DTO의 값을 한번에 보고 싶을 때 사용
	@Override
	public String toString() {
		return "QnasDTO [qna_id=" + qna_id + ", qna_category=" + qna_category + ", qna_title=" + qna_title
				+ ", qna_content=" + qna_content + ", qna_answer=" + qna_answer + ", member_id=" + member_id + "]";
	}
}

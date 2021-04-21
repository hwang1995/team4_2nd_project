package com.team4.webapp.dto;

public class CheckoutDTO {
	private Long member_id;
	private String order_bank;
	private String recipient_name;
	private String recipient_address;
	private String recipient_tel;
	
	// Getters & Setters
	public Long getMember_id() {
		return member_id;
	}
	public void setMember_id(Long member_id) {
		this.member_id = member_id;
	}
	public String getOrder_bank() {
		return order_bank;
	}
	public void setOrder_bank(String order_bank) {
		this.order_bank = order_bank;
	}
	public String getRecipient_name() {
		return recipient_name;
	}
	public void setRecipient_name(String recipient_name) {
		this.recipient_name = recipient_name;
	}
	public String getRecipient_address() {
		return recipient_address;
	}
	public void setRecipient_address(String recipient_address) {
		this.recipient_address = recipient_address;
	}
	public String getRecipient_tel() {
		return recipient_tel;
	}
	public void setRecipient_tel(String recipient_tel) {
		this.recipient_tel = recipient_tel;
	}
	
	// For testing purpose
	@Override
	public String toString() {
		return "CheckoutDTO [member_id=" + member_id + ", order_bank=" + order_bank + ", recipient_name="
				+ recipient_name + ", recipient_address=" + recipient_address + ", recipient_tel=" + recipient_tel
				+ "]";
	}
	
	
	
	
	

}

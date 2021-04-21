package com.team4.webapp.dto;

import java.util.Date;

public class OrdersDTO {
	private Long order_id;
	private Date order_date;
	private String order_bank;
	private int order_delivery_charge;
	private String order_payment_status;
	private String order_delivery_status;
	private String recipient_name;
	private String recipient_address;
	private String recipient_tel;
	private Long member_id;
	
	// Getters & Setters
	public Long getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	public String getOrder_bank() {
		return order_bank;
	}
	public void setOrder_bank(String order_bank) {
		this.order_bank = order_bank;
	}
	public int getOrder_delivery_charge() {
		return order_delivery_charge;
	}
	public void setOrder_delivery_charge(int order_delivery_charge) {
		this.order_delivery_charge = order_delivery_charge;
	}
	public String getOrder_payment_status() {
		return order_payment_status;
	}
	public void setOrder_payment_status(String order_payment_status) {
		this.order_payment_status = order_payment_status;
	}
	public String getOrder_delivery_status() {
		return order_delivery_status;
	}
	public void setOrder_delivery_status(String order_delivery_status) {
		this.order_delivery_status = order_delivery_status;
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
	public Long getMember_id() {
		return member_id;
	}
	public void setMember_id(Long member_id) {
		this.member_id = member_id;
	}
	public String getRecipient_tel() {
		return recipient_tel;
	}
	public void setRecipient_tel(String recipient_tel) {
		this.recipient_tel = recipient_tel;
	}
	
	// For Testing purpose -> DTO의 값을 한번에 보고 싶을 때 사용
	@Override
	public String toString() {
		return "OrdersDTO [order_id=" + order_id + ", order_date=" + order_date + ", order_bank=" + order_bank
				+ ", order_delivery_charge=" + order_delivery_charge + ", order_payment_status=" + order_payment_status
				+ ", order_delivery_status=" + order_delivery_status + ", recipient_name=" + recipient_name
				+ ", recipient_address=" + recipient_address + ", recipient_tel=" + recipient_tel + ", member_id="
				+ member_id + "]";
	}
	
	// 주문 정보 Set
	public void setOrderInfo(CheckoutDTO orderInfo) {
		this.member_id = orderInfo.getMember_id();
		this.order_bank = orderInfo.getOrder_bank();
		this.recipient_name = orderInfo.getRecipient_name();
		this.recipient_address = orderInfo.getRecipient_address();
		this.recipient_tel = orderInfo.getRecipient_tel();
	}
	
	// 주문 상태 Set
	public void setOrderStatus(
			int order_delivery_charge,
			String order_payment_status,
			String order_delivery_status
	) {
		this.order_delivery_charge = order_delivery_charge;
		this.order_payment_status = order_payment_status;
		this.order_delivery_status = order_delivery_status;
		
	}
	
	
	
	
	
	

}

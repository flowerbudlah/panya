package com.tjoeun.spring.dto;

import java.util.Date;

import lombok.Data;

@Data
public class OrderListDTO {

	private String order_idx;
	
	private String member_id; //주문결제자의 아이디 
	private int paymentprice; //배송비+물품총가격 = 결제금액
	
	private String sender_name; //발송인의 이름
	private String sender_address; //발송인의 주소
	private int sender_postcode; //발송인 우편번호
	private String sender_tel; //발송인의 전화번호 
	
	private String recipient_name; //수신인의 이름
	private String recipient_address; //수신인의 주소 
	private int recipient_postcode; 	
	private String recipient_tel; 
	
	private Date order_date; //결제일 
	private String memo; 
	private String payment_method; 
	
	private int order_detail_idx; 
	private int product_idx; //해당 물건의 인덱스
	private int amount; //구매수량
	
	private String product_name; //상품명
	private int product_price; //단가
	private String product_img; //사진이름 .png
	
	
	
	
	
	
	
	
}

package com.tjoeun.spring.dto;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberDTO {
	private int member_idx; 
	
	@Size(min=2, max=4)
	@Pattern(regexp = "[가-힣]*")
	private String member_name; 	
	
	@Size(min=2, max=20)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String member_id; 	
	
	@Size(min=2, max=20)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String member_pw; 	
	
	@Size(min=2, max=20)
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String member_pw2; 
	
	
	
	
	
	private String member_email; 
	
	private String member_tel; 	
	private String member_address; 	
	private int postcode; //우편번호 
	
	private String question; 
	private String answer; 
	
	private String registerDate; //가입일
	
	private boolean inputMemberID;
	private boolean inputMemberEmail;
	
	private boolean memberLogin;
	
	public MemberDTO() {
		this.inputMemberID = false;
		this.inputMemberEmail = false; 
	}
	
	
	
	//관리자페이지에서 검색
	private String keyword;//키워드 
	private String type; //검색종류
	
}



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
	private int postcode; //�슦�렪踰덊샇 
	
	private String question; 
	private String answer; 
	
	private String registerDate; //媛��엯�씪
	
	private boolean inputMemberID;
	private boolean inputMemberEmail;
	
	private boolean memberLogin;
	
	public MemberDTO() {
		this.inputMemberID = false;
		this.inputMemberEmail = false; 
	}
	
	
	
	//愿�由ъ옄�럹�씠吏��뿉�꽌 寃��깋
	private String keyword;//�궎�썙�뱶 
	private String type; //寃��깋醫낅쪟
	
}



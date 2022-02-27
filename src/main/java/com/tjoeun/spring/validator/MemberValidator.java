package com.tjoeun.spring.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.tjoeun.spring.dto.MemberDTO;

public class MemberValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return MemberDTO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		MemberDTO loginMemberDTO = (MemberDTO)target;
		
		String dtoName = errors.getObjectName();
		
		System.out.println("dtoName : " + dtoName);
		
		if(dtoName.equals("joinMemberDTO") || dtoName.equals("modifyMemberDTO")) {
			
			if(loginMemberDTO.getMember_pw().equals( loginMemberDTO.getMember_pw2() ) == false) {//�엯�젰�븳 鍮꾨쾲 �몢媛쒓� �떎瑜대떎. 
				errors.rejectValue("member_pw", "NotEquals");
				errors.rejectValue("member_pw2", "NotEquals");
			}
		}
		

		if(dtoName.equals("joinMemberDTO")){
			
			if(loginMemberDTO.isInputMemberID() == false) {	
				errors.rejectValue("member_id", "DontCheckMemberIdExist");
			}
		
			if(loginMemberDTO.isInputMemberEmail() == false) {	
				errors.rejectValue("member_email", "CheckMemberEmailExist");
			}
			
			
			
		}
		
	
		
		
		
		
		
	}
	
	
	

}





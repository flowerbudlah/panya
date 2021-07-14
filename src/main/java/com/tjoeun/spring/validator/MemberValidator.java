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
			
			if(loginMemberDTO.getMember_pw().equals( loginMemberDTO.getMember_pw2() ) == false) {//입력한 비번 두개가 다르다. 
				errors.rejectValue("member_pw", "NotEquals");
				errors.rejectValue("member_pw2", "NotEquals");
			}
		}
		
		//프로퍼티 그부분
		if(dtoName.equals("joinMemberDTO")){
			
			//아이디 검증
			if(loginMemberDTO.isInputMemberID() == false) {	
				errors.rejectValue("member_id", "DontCheckMemberIdExist");
			}
		
			//이메일 중복검사 하라고 요청하는 메시지
			if(loginMemberDTO.isInputMemberEmail() == false) {	
				errors.rejectValue("member_email", "CheckMemberEmailExist");
			}
			
			
			
		}
		
	
		
		
		
		
		
	}
	
	
	

}





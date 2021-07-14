package com.tjoeun.spring.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.tjoeun.spring.dto.MemberDTO;

@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	//아이디 중복체크 
	public String checkID(String member_id) {
		String ckecking_id = sqlSessionTemplate.selectOne("member.checkID", member_id);
			return ckecking_id;
	}
	//이메일 중복체크
	public int checkEmail(String member_email) {
		int result = sqlSessionTemplate.selectOne("member.checkEmail", member_email);
			return result;
	}
	
	public void addUserInfo(MemberDTO joinMemberDTO){//회원가입 
		sqlSessionTemplate.insert("member.addMemberInfo", joinMemberDTO);
	}
	
	public MemberDTO getLoginMemberDTO(MemberDTO tmpLoginMemberDTO) {//로그인 
		MemberDTO fromDBMemberDTO = sqlSessionTemplate.selectOne("member.getLoginMember", tmpLoginMemberDTO);
			return fromDBMemberDTO;
	}
	
	
	//회원정보를 수정하기 위해서 내 컴 db에 저장된 그 정보를 가져오는것 
	public MemberDTO getModifyMemberDTO(int member_idx) {                      
		MemberDTO fromDBModifyMemberDTO = sqlSessionTemplate.selectOne("member.getModifyMemberDTO", member_idx);
			return fromDBModifyMemberDTO;
  	} 
	public void modifyMemberInfo(MemberDTO modifyMemberDTO){//회원정보수정 
		sqlSessionTemplate.update("member.modifyMemberInfo", modifyMemberDTO);
	}
	
	//회원탈퇴 
	public void delete(MemberDTO deleteMemberDTO) throws Exception {
		sqlSessionTemplate.delete("member.delete", deleteMemberDTO);
	}
	
	//아이디 찾기
	public String find_id(String member_email) throws Exception{
		return sqlSessionTemplate.selectOne("member.find_id", member_email);
	}
	
	
	//비번찾기 1단계: 아이디를 넣어서 질문과 넣은아이디 가져오기  
	public MemberDTO find_question(String member_id) throws Exception{
		return sqlSessionTemplate.selectOne("member.find_question", member_id);
	}
	//비번찾기: 2단계: 답과 아이디(비공개)를 입력해서 비번찾기  
	public MemberDTO find_password(MemberDTO answerAndId){
		return sqlSessionTemplate.selectOne("member.find_password", answerAndId);
	}
 




}




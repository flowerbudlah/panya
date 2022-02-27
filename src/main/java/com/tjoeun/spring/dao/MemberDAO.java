package com.tjoeun.spring.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.tjoeun.spring.dto.MemberDTO;

@Repository
public class MemberDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	//1. 아이디 중복체크 
	public String checkID(String member_id) {
		String ckecking_id = sqlSessionTemplate.selectOne("member.checkID", member_id);
			return ckecking_id;
	}
	
	//2. 이메일 중복체크
	public String checkEmail(String member_email) {
		String checking_email = sqlSessionTemplate.selectOne("member.checkEmail", member_email);
			return checking_email;
	}
	
	public void addUserInfo(MemberDTO joinMemberDTO){
		sqlSessionTemplate.insert("member.addMemberInfo", joinMemberDTO);
	}
	
	public MemberDTO getLoginMemberDTO(MemberDTO tmpLoginMemberDTO) {
		MemberDTO fromDBMemberDTO = sqlSessionTemplate.selectOne("member.getLoginMember", tmpLoginMemberDTO);
			return fromDBMemberDTO;
	}
	
	
	//�쉶�썝�젙蹂대�� �닔�젙�븯湲� �쐞�빐�꽌 �궡 而� db�뿉 ���옣�맂 洹� �젙蹂대�� 媛��졇�삤�뒗寃� 
	public MemberDTO getModifyMemberDTO(int member_idx) {                      
		MemberDTO fromDBModifyMemberDTO = sqlSessionTemplate.selectOne("member.getModifyMemberDTO", member_idx);
			return fromDBModifyMemberDTO;
  	} 
	public void modifyMemberInfo(MemberDTO modifyMemberDTO){//�쉶�썝�젙蹂댁닔�젙 
		sqlSessionTemplate.update("member.modifyMemberInfo", modifyMemberDTO);
	}
	
	//�쉶�썝�깉�눜 
	public void delete(MemberDTO deleteMemberDTO) throws Exception {
		sqlSessionTemplate.delete("member.delete", deleteMemberDTO);
	}
	
	//�븘�씠�뵒 李얘린
	public String find_id(String member_email) throws Exception{
		return sqlSessionTemplate.selectOne("member.find_id", member_email);
	}
	
	
	//鍮꾨쾲李얘린 1�떒怨�: �븘�씠�뵒瑜� �꽔�뼱�꽌 吏덈Ц怨� �꽔���븘�씠�뵒 媛��졇�삤湲�  
	public MemberDTO find_question(String member_id) throws Exception{
		return sqlSessionTemplate.selectOne("member.find_question", member_id);
	}
	//鍮꾨쾲李얘린: 2�떒怨�: �떟怨� �븘�씠�뵒(鍮꾧났媛�)瑜� �엯�젰�빐�꽌 鍮꾨쾲李얘린  
	public MemberDTO find_password(MemberDTO answerAndId){
		return sqlSessionTemplate.selectOne("member.find_password", answerAndId);
	}
 




}




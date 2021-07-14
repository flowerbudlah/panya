package com.tjoeun.spring.service;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.tjoeun.spring.dao.MemberDAO;
import com.tjoeun.spring.dto.MemberDTO;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Resource(name = "loginMemberDTO")
	@Lazy
	private MemberDTO loginMemberDTO;	
	
	//아이디 중복체크 
	public boolean checkID(String member_id) {
		
		String checking_id = memberDAO.checkID(member_id); 

		if(checking_id == null) {	
			return true; //입력한 아이디가 디비에 없으니 사용가능. 
		}else {	
			return false; //입력한 아이디 이미존재하니 사용불능. 
		}
	}
	
	//이메일 중복체크 
	public int checkEmail(String member_email) {
		int result = memberDAO.checkEmail(member_email);
		return result;  //0, 1
	}
	
	
	//회원가입
	public void addMemberInfo(MemberDTO joinMemberDTO) {
		memberDAO.addUserInfo(joinMemberDTO);
	}
  
	//로그인
	public void getLoginMemberDTO(MemberDTO tmpLoginMemberDTO) {
		MemberDTO fromDBMemberDTO = memberDAO.getLoginMemberDTO(tmpLoginMemberDTO);
		
		if(fromDBMemberDTO != null) {
			loginMemberDTO.setMember_idx(fromDBMemberDTO.getMember_idx());
			loginMemberDTO.setMember_name(fromDBMemberDTO.getMember_name());
			loginMemberDTO.setMember_id(fromDBMemberDTO.getMember_id());
			loginMemberDTO.setMember_pw(fromDBMemberDTO.getMember_pw());
	
			loginMemberDTO.setMemberLogin(true);
		}
	}
	
	
	
	
	public void getModifyMemberDTO(MemberDTO modifyMemberDTO) {
		//회원정보를 수정하기 위해서 정보수정 페이지를 눌렀을때 db에 있는 나의 정보 가져오는것 
		MemberDTO fromDBModifyMemberDTO = memberDAO.getModifyMemberDTO(loginMemberDTO.getMember_idx());
  	
		modifyMemberDTO.setMember_id(fromDBModifyMemberDTO.getMember_id());
		modifyMemberDTO.setMember_name(fromDBModifyMemberDTO.getMember_name());
		modifyMemberDTO.setMember_pw(fromDBModifyMemberDTO.getMember_pw());
		modifyMemberDTO.setMember_pw2(fromDBModifyMemberDTO.getMember_pw2());
		modifyMemberDTO.setMember_tel(fromDBModifyMemberDTO.getMember_tel());
		modifyMemberDTO.setMember_address(fromDBModifyMemberDTO.getMember_address());
		modifyMemberDTO.setPostcode(fromDBModifyMemberDTO.getPostcode());
		modifyMemberDTO.setMember_email(fromDBModifyMemberDTO.getMember_email());
		modifyMemberDTO.setQuestion(fromDBModifyMemberDTO.getQuestion());
		modifyMemberDTO.setAnswer(fromDBModifyMemberDTO.getAnswer());
		
		modifyMemberDTO.setMember_idx(loginMemberDTO.getMember_idx());
	} 

	//회원정보수정 
	public void modifyMemberInfo(MemberDTO modifyMemberDTO){
		modifyMemberDTO.setMember_idx(loginMemberDTO.getMember_idx());
		memberDAO.modifyMemberInfo(modifyMemberDTO);
	}
	
	//회원탈퇴 
	public void delete(MemberDTO deleteMemberDTO) throws Exception{
		memberDAO.delete(deleteMemberDTO);
	}
	
	// 아이디 찾기
	public String find_id(HttpServletResponse response, String member_email) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String id = memberDAO.find_id(member_email);
		
		if (id == null) {
			out.println("<script>");
			out.println("alert('가입된 아이디가 없습니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
			return null;
		} else {
			return id;
		}
	}
	
	
	//비밀번호를 찾기위한 첫단계로 회원가입시 입력한 비번 분실시 질문받기 
	public MemberDTO find_question(HttpServletResponse response, String member_id) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		MemberDTO memberDTO = memberDAO.find_question(member_id);
		
		if (memberDTO == null) {
			out.println("<script>");
			out.println("alert('회원가입시 비번분실시 질문을 입력하지 않으셨거나 혹은 가입된 정보가 없습니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
			return null;
		} else {
			return memberDTO;
		} 
	}
	
	
	// 비밀번호 찾기(질문에 대한 답과 아이디를 입력한 뒤에-로그인방식과 유사 )
	public MemberDTO find_password(MemberDTO answerAndId) {
		return memberDAO.find_password(answerAndId);
		
	}
	
	
	
	}
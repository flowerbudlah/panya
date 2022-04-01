package com.tjoeun.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tjoeun.spring.dao.AdminDAO;
import com.tjoeun.spring.dto.MemberDTO;
import com.tjoeun.spring.dto.OrderDTO;

@Service
public class AdminService {

	@Autowired
	private AdminDAO adminDAO; 
	
	public List<MemberDTO> getAllMember(){
		
		List<MemberDTO> allMemberList = adminDAO.getAllMember(); 
			return allMemberList ;	
	}
	
	public List<OrderDTO> getAllPayment(){
		List<OrderDTO> allPaymentList = adminDAO.getAllPayment();  
			return allPaymentList ;	
	}
	
	public void changeDeliveryCircumstance(OrderDTO changeDeliveryOrderDTO) {
		adminDAO.changeDeliveryCircumstance(changeDeliveryOrderDTO);
	}
	
	
	//회원검색
	public int numberOfResultSearchingMember(MemberDTO searchResultMemberDTO) {
		int search_result_count = adminDAO.numberOfResultSearchingMember(searchResultMemberDTO); 
		return search_result_count; 
	}
	
	
	//검색 결과 회원
	public List<MemberDTO> resultSearchingMember(MemberDTO searchResultMemberDTO){
		List<MemberDTO> memberList = adminDAO.resultSearchingMember(searchResultMemberDTO); 
		return memberList; 
	}
	
	
	
	
}

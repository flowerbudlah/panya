package com.tjoeun.spring.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tjoeun.spring.dto.CartDTO;
import com.tjoeun.spring.dto.OrderDTO;
import com.tjoeun.spring.dto.OrderDetailDTO;
import com.tjoeun.spring.dto.OrderListDTO;

@Repository
public class CartDAO {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public boolean findCartGoods(CartDTO cartDTO) {
		String result = sqlSessionTemplate.selectOne("cart.findCartGoods", cartDTO);
		return Boolean.parseBoolean(result);
	}

	//장바구니에 넣기 
	public void addGoodsInCart(CartDTO cartDTO) {
		sqlSessionTemplate.insert("cart.addGoodsInCart", cartDTO);
	}

	//장바구니 목록 
	public List<CartDTO> getMyCart(String member_id) {	
		return sqlSessionTemplate.selectList("cart.getMyCart", member_id);
	}


	//장바구니 인덱스 하나 삭제
	public void delete(int cart_idx) {
		sqlSessionTemplate.delete("cart.delete", cart_idx);
	}
	
	//장바구니 합계
	public int sum(String member_id) {
		return sqlSessionTemplate.selectOne("cart.sum", member_id);
	}
	
	//장바구니안에있는 물건의 수량변경 
	public void updateAmount(CartDTO updateAmountCartDTO) {
		sqlSessionTemplate.update("cart.updateAmount", updateAmountCartDTO); 
	}
	
	
	
	
	//1. 결제
	
	//1) 주문자의 정보, 수신인 정보입력
	public void orderInfo(OrderDTO newOrderDTO) {
		sqlSessionTemplate.insert("cart.orderInfo", newOrderDTO);
	}
	//2) 주문 상세정보(주문번호, 해당물품, 수량, )
	public void orderInfoDetail(OrderDetailDTO newOrderDetailDTO) {
		sqlSessionTemplate.insert("cart.orderInfo_Details", newOrderDetailDTO); 
	}
	//3) 카트비우기
	public void emptyMyCart(String member_id) {
		sqlSessionTemplate.delete("cart.emptyMyCart", member_id); 
	}
	//4) 특정회원의 결제완료 리스트
	public List<OrderDTO> orderPaymentList(String member_id) {
		return sqlSessionTemplate.selectList("cart.orderPaymentList", member_id);
	}
	
	public List<OrderListDTO> allInformationAboutOrder(OrderDTO idAndOrderIdxOrderDTO) {
		return sqlSessionTemplate.selectList("cart.allInformationAboutOrder",idAndOrderIdxOrderDTO);
	}
	
	
	
	
	
	

}

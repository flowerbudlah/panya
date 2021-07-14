package com.tjoeun.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tjoeun.spring.dao.TopMenuDAO;
import com.tjoeun.spring.dto.BoardDTO;

@Service
public class TopMenuService {
  // TopMenuDAO에 있는 메소드를 호출해서 받아온 정보를 사용해서 작업하는 클래스
	
	@Autowired
	private TopMenuDAO topMenuDAO;
  
	public List<BoardDTO> getTopMenuList() {
		List<BoardDTO> topMenuList = topMenuDAO.getTopMenuList();
			return topMenuList;
	}
	
}



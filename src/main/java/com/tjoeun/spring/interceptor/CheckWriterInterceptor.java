package com.tjoeun.spring.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.HandlerInterceptor;

import com.tjoeun.spring.dto.PostDTO;
import com.tjoeun.spring.dto.MemberDTO;
import com.tjoeun.spring.service.BoardService;

public class CheckWriterInterceptor implements HandlerInterceptor{
	/*
	 1) 현재 로그인한 사람의 index 가져옴
	  
	 2) BoardService에 getContentInfo 는 게시글을 가져오므로 작성한 사람의 index번호가 있음. 
	  	이 메소드를 호출해서 작성한 사람의 index번호 가져옴
	  
	  XML 방식에서는 자동주입이 되므로 생성자를 작성하지 않아도 됨
	*/
	
	@Resource(name="loginMemberDTO")
	@Lazy
	private MemberDTO loginMemberDTO;
	
	@Autowired
	private BoardService boardService;
	
	//로그인한 사람과 작성한 사람이 같은지 검사하기--이번 Interceptor는 수정할 때와 삭제할 때만 반응하도록 함
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
  	
		/*자동으로 주입받지 않음, Request 객체에 저장된 값을 getParameter로 가져옴*/
		int post_idx = Integer.parseInt(request.getParameter("post_idx"));
  	
		/*Request 객체 속에 저장된 content_idx(글번호)에 해당하는 ContentDTO 가져오기(content_wirter_idx 가 필요해서)*/ 
		PostDTO tmpPostDTO  = boardService.getPostInfo(post_idx);
  	
  	// 로그인한 사람과 글 쓴 사람의 회원번호(idx)가 일치하지 않으면 BoardController의 "/board/not_writer" 로 이동하게 함
  	if(tmpPostDTO.getPost_writer_idx() != loginMemberDTO.getMember_idx() ) {
  		String contextPath = request.getContextPath();
  			response.sendRedirect(contextPath + "/board/not_writer");
  				return false;
  	}
  		return true;
  }
  

}

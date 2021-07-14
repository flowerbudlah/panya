package com.tjoeun.spring.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tjoeun.spring.dto.PostDTO;

@Repository
public class BoardDAO {
  
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	//글쓰기
	public void addPostInfo(PostDTO writePostDTO) {
		sqlSessionTemplate.insert("board.addPostInfo", writePostDTO);
	}
	
	//게시판 이름 가져오기
	public String getBoardName(int board_idx) {
		String board_name = sqlSessionTemplate.selectOne("board.getBoardName", board_idx);
			return board_name; 	
	}
	//게시글 전체수
	public int getPostCnt(int post_board_idx) {
		int postCnt = sqlSessionTemplate.selectOne("board.getPostCnt", post_board_idx);
		return postCnt;
	}
	//전체게시물 리스트 
	public List<PostDTO> getPostList(int board_idx, RowBounds rowBounds){
		List<PostDTO> postList = sqlSessionTemplate.selectList("board.getPostList", board_idx, rowBounds);
		return postList;
	}
	
	
	
	//검색한 게시물의 수 
	public int searchResultCount(PostDTO searchPostDTO) {
		 int search_result_count = sqlSessionTemplate.selectOne("board.searchResultCount", searchPostDTO); 
		 return search_result_count; 
	}
	//게시물 검색 결과 리스트
	public List<PostDTO> selectSearchList(PostDTO searchPostDTO, RowBounds rowBounds){
		return sqlSessionTemplate.selectList("board.selectSearchList", searchPostDTO, rowBounds);
	}

	
	
	
	
	
	
	//글 하나 읽기
	public PostDTO getPostInfo(int post_idx){
		PostDTO readPostDTO = sqlSessionTemplate.selectOne("board.getPostInfo", post_idx);
		return readPostDTO;
	}
	
	//글 수정
	public void modifyPostInfo(PostDTO modifyPostDTO) {
		sqlSessionTemplate.update("board.modifyPostInfo", modifyPostDTO);
	}
	
	//글 삭제
	public void delete(int post_idx){
		sqlSessionTemplate.delete("board.replyDelete", post_idx); 
		sqlSessionTemplate.delete("board.delete", post_idx);
	}
	
	//조회수 증가
	public void viewCount(int post_idx) {
		sqlSessionTemplate.update("board.viewCount", post_idx); 
	}


	

	
	
}














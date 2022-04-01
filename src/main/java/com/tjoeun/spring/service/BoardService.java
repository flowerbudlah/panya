package com.tjoeun.spring.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tjoeun.spring.dao.BoardDAO;
import com.tjoeun.spring.dto.PostDTO;
import com.tjoeun.spring.dto.PageDTO;
import com.tjoeun.spring.dto.MemberDTO;

@Service
@PropertySource("/WEB-INF/properties/option.properties")
public class BoardService {
	
	@Value("${path.load}")
	private String path_load;
	
	// 한 페이지당 보여주는 글의 개수
	@Value("${page.listcnt}")
	private int page_listcnt;	
	
	// 한 페이지당 보여주는 페이지 버튼 개수
	@Value("${page.paginationcnt}")
	private int page_paginationcnt;	
	
	@Autowired
	private BoardDAO boardDAO;
	
	@Resource(name="loginMemberDTO")
	@Lazy
	private MemberDTO loginMemberDTO;
	
	
	//게시판 이미지 파일 업로드 관련 
	private String saveUploadFile(MultipartFile upload_file) {
		
		String file_name = System.currentTimeMillis() + "_" + upload_file.getOriginalFilename();
		
		try { 
			upload_file.transferTo(new File(path_load + "/" + file_name));
		} catch (IllegalStateException e) { 
			e.printStackTrace();
		} catch (IOException e) { 
			e.printStackTrace();
		}
		return file_name;
	}
	
	//글쓰기 
	public void addPostInfo(PostDTO writePostDTO) {   // parameter로 전달되어오는 data 확인하기
		
		MultipartFile upload_file = writePostDTO.getUpload_file();
		
		if(upload_file.getSize() > 0) {//업로드한 파일이 있는경우
			String file_name = saveUploadFile(upload_file);
				writePostDTO.setPost_file(file_name);
		}
		
		writePostDTO.setPost_writer_idx(loginMemberDTO.getMember_idx());
		boardDAO.addPostInfo(writePostDTO);
		
	}
	
	
	
	
	//게시판 이름 가져오기 
	public String getBoardName(int board_idx) {
		String board_name = boardDAO.getBoardName(board_idx);
			return board_name;
	}
	
	
	// 페이징 작업을 거친 게시판 메인화면 그 리스트! 
	public List<PostDTO> getPostList(int board_idx, int page){
		                             // 10  
		int start = (page - 1) * page_listcnt;
		RowBounds rowBounds = new RowBounds(start, page_listcnt);
		
		List<PostDTO> postList = boardDAO.getPostList(board_idx, rowBounds);
			return postList;
	}
	
	//게시판 메인 페이지
	public PageDTO getPostCnt(int post_board_idx, int currentPage) {
		
		int postCnt = boardDAO.getPostCnt(post_board_idx); //전체게시글 수 
		PageDTO pageDTO = new PageDTO(postCnt, currentPage, page_listcnt, page_paginationcnt);
		
		return pageDTO;
	}
	
	
	//검색한 게시물의 수 
	public int searchResultCount(PostDTO searchPostDTO) {
		int search_result_count = boardDAO.searchResultCount(searchPostDTO);
			return search_result_count; 
	}
	
	
	//페이징 작업도 추가한 게시판 검색관련 
	public List<PostDTO> selectSearchList(PostDTO searchPostDTO, int page){
		
		int start = (page - 1) * page_listcnt;
		RowBounds rowBounds = new RowBounds(start, page_listcnt);
			
		List<PostDTO> searchList = boardDAO.selectSearchList(searchPostDTO, rowBounds);
		 
		return searchList; 
	}
	
	
	//검색결과관련 페이징
	public PageDTO searchListCount(PostDTO searchPostDTO, int currentPage) {
		
		int search_result_count = boardDAO.searchResultCount(searchPostDTO); //검색해서 나온 게시글의 수 
		PageDTO pageDTO = new PageDTO(search_result_count, currentPage, page_listcnt, page_paginationcnt);
			
		return pageDTO;
	}

		
	//글 읽기
	public PostDTO getPostInfo(int post_idx){
		PostDTO readPostDTO = boardDAO.getPostInfo(post_idx);
			return readPostDTO;
	}
	
	
	//글수정
	public void modifyPostInfo(PostDTO modifyPostDTO) {
		
		MultipartFile upload_file = modifyPostDTO.getUpload_file();
		if(upload_file.getSize() > 0) {
			String file_name = saveUploadFile(upload_file);
			modifyPostDTO.setPost_file(file_name);
		}
		boardDAO.modifyPostInfo(modifyPostDTO);
	}
	
	
	
	
	
	//글삭제
	public void delete(int post_idx){
		boardDAO.delete(post_idx);
	}
	
	//조회수 증가
	public void viewCount(int post_idx) {
		boardDAO.viewCount(post_idx);
	}
	
}
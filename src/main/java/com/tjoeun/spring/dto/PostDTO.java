package com.tjoeun.spring.dto;

import javax.validation.constraints.NotBlank;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class PostDTO {
	
	private int post_idx; //게시글 일련번호
	
	@NotBlank
	private String post_subject; //게시글 제목
	
	@NotBlank
	private String post_text; //게시글 내용
	
	
	private int post_writer_idx; //게시글 작성자의 일련번호 
	
	private String post_writer_name; // SQL문에서 alias(별칭)으로 지정한 컬럼명을 PostDTO의 멤버변수로 선언해야 값을 받아올 수 있음
	
	private int post_board_idx; //이 글이 속하는 게시판번호. 1이면 공지사항, 2이면 고객센터   
	
	private String post_date; //게시일(작성날짜)
	
	private String post_file; // 데이터베이스에 저장되어있는 파일이름을 저장하는 변수
	private MultipartFile upload_file; // browser가 보내는 file data를 저장하는 변수
	
	
	private int post_read_count; //조회수
	private int reply_cnt;    // **게시글 댓글의 수 추가(2021년 06월 01일)
	
	
	private String keyword;//키워드 
	private String type; //검색종류
	

	
	
	 
}





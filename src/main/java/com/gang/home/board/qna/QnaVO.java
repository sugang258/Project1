package com.gang.home.board.qna;

import java.sql.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class QnaVO {
	
	private Long num;
	@NotBlank
	private String title;
	private String writer;
	private String contents;
	private Long hit;
	private Date regDate;
	private Long ref;
	private Long step;
	private Long depth;
	private List<QnaFileVO> qnaFilesVO;
	private MultipartFile[] files;

	
}
package com.gang.home.board.qna;

import org.springframework.stereotype.Repository;

import lombok.Data;

@Data
public class QnaFileVO {
	
	private Long fileNum;
	private Long num;
	private String fileName;
	private String oriName;
}


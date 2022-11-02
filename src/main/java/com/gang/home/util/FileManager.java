package com.gang.home.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.AbstractView;

import com.gang.home.board.qna.QnaFileVO;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class FileManager extends AbstractView {
	
	@Value("${app.download.base}")
	private String base;
	
	//Down 걸어주는 Method
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		//FileManagerController에서 꺼내오기
		QnaFileVO qnaFileVO =(QnaFileVO) model.get("fileVO");
		String path =(String) model.get("path");
		
		log.info("=============================");
		log.info("FileVO : {}",qnaFileVO);
		
		//배포하면 경로가 바뀌어야함 /app2/upload/  ==> application-prod.properties 파일에 해줌
		//개발환경 => C:/result/upload/  ==> application.properties 파일에 해줌
		File file = new File(base+path, qnaFileVO.getFileName());
		
		//한글 처리
		response.setCharacterEncoding("UTF-8");
		
		//총 파일의 크기
		response.setContentLengthLong(file.length());
		
		//다운로드시 파일의 이름을 인코딩
		String oriName = URLEncoder.encode(qnaFileVO.getOriName(),"UTF-8");
		
		//header 설정
		response.setHeader("Content-Disposition", "attachment;filename=\""+oriName+"\"");
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		//HDD에서 파일을 읽고
		FileInputStream fi = new FileInputStream(file);
		//Client로 전송 준비
		OutputStream os = response.getOutputStream();
		
		//전송
		FileCopyUtils.copy(fi,os);
		
		//자원 해제
		os.close();
		fi.close();
	}
	
	public String saveFile(MultipartFile multipartFile, String path) throws IOException {
		
		//1. 중복되지 않은 파일명 생성(UUID, Date)
		String fileName = UUID.randomUUID().toString();
		
		//2. 확장자
		StringBuffer bf = new StringBuffer();
		bf.append(fileName);
		bf.append("_");
		
		
		//파일명과 확장자 분리
		String ex = multipartFile.getOriginalFilename();
		ex = ex.substring(ex.lastIndexOf("."));
		
		bf.append(ex);
		
		fileName = bf.toString();
		
		//3. File Save
		File file = new File(path,bf.toString());
		
		//FileCopyUtils.copy(multipartFile.getBytes(), file);
	    multipartFile.transferTo(file);
		
		
		log.info("fileName : {}",bf.toString());
		
		return fileName;
		
	}

}

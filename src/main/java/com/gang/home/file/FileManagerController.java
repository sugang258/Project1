package com.gang.home.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.gang.home.board.qna.QnaFileVO;
import com.gang.home.board.qna.QnaService;

import lombok.extern.slf4j.Slf4j;

//@Controller
//@Slf4j
//public class FileManagerController {
//	
//	@Autowired
//	private QnaService qnaService;
//
//	@GetMapping("/fileDown/{path}") //RestFul, RestAPI
//	public ModelAndView fileDown(@PathVariable String path, QnaFileVO qnaFileVO) throws Exception{
//		log.info("===================");
//		log.info("Path : {}",path);
//		ModelAndView mv = new ModelAndView();
//		
//		if(path.equals("qna")) {
//			qnaFileVO = qnaService.getFileDetail(qnaFileVO);
//			
//			
//		}else if(path.equals("notice")) {
//			qnaFileVO.setFileName("palace.jpg");
//			qnaFileVO.setOriName("경복궁.png");
//		}
//		
//		//DB에서 정보 조회
//		
//		mv.addObject("fileVO", qnaFileVO);
//		mv.addObject("path", path);
//
//		//FileManager로 가자!
//		mv.setViewName("fileManager");
//		//BeanNameResolver :
//		//view의 이름과 일치하는 bean의 이름이 있으면 해당 Bean 실행
//		
//		//InternalResourceViewResolver
//		//WEB-INF/views/fileManager.jsp
//		
//		return mv;
//		
//	}
//}
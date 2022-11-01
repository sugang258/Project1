package com.gang.home.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gang.home.util.Pager;

@RequestMapping(value="/qna/*")
@Controller
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	@GetMapping("list")
	public ModelAndView getList(ModelAndView mv,Pager pager) throws Exception{
		List<QnaVO> ar = qnaService.getList(pager);
		
		mv.addObject("ar", ar);
		mv.addObject("pager", pager);
		mv.setViewName("/board/list");
		
		return mv;
	}
	
	@GetMapping("detail")
	public ModelAndView getDetail(ModelAndView mv, QnaVO qnaVO) throws Exception{
		qnaVO = qnaService.getDetail(qnaVO);
		mv.addObject("qnaVO", qnaVO);
		mv.setViewName("/board/detail");
		return mv;
	}

}

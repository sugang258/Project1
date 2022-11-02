package com.gang.home.board.qna;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gang.home.util.Pager;

import lombok.extern.slf4j.Slf4j;

@RequestMapping(value="/qna/*")
@Controller
@Slf4j
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
	
	@GetMapping("add")
	public String setAdd(@ModelAttribute QnaVO qnaVO)throws Exception{
		return "/board/add";
	}
	
	@PostMapping("add")
	public ModelAndView setAdd(@Valid QnaVO qnaVO, BindingResult bindingResult, RedirectAttributes redirectAttributes,ModelAndView mv) throws Exception{
		qnaVO.setWriter("나야~");
		if(bindingResult.hasErrors()) {
			log.info("====검증 에러 발생====");
			mv.setViewName("/board/add");
			return mv;
		}
		int result = qnaService.setAdd(qnaVO);
		//redirectAttributes.addAttribute("result", result);
		mv.setViewName("redirect:../");
		return mv;
	}

}

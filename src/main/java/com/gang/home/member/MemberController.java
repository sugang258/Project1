package com.gang.home.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping(value="/member/*")
@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;

	@GetMapping("login")
	public String getLogin() throws Exception{
		return "/member/login";
	}
	
	@GetMapping("join")
	public String setJoin() throws Exception{
		return "/member/join";
	}
	
	@PostMapping("join")
	public ModelAndView setJoin(MemberVO memberVO,ModelAndView mv) throws Exception{
		int result = memberService.setJoin(memberVO);
		
		mv.setViewName("/member/login");
		return mv;
	}
	
//	@GetMapping("logout")
//	public String getLogout(HttpServletRequest request, HttpServletResponse response) throws Exception{
//
//			
//			return "/member/login";
//	}
}

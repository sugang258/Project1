package com.gang.home.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping(value="/member/*")
@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;

	@GetMapping("login")
	public void getLogin(@RequestParam(defaultValue="false", required=false)boolean error, String message, Model model) throws Exception{
		if(error) {
			model.addAttribute("msg", "ID 또는 PW를 확인하세요");
		}
	}
	
	@PostMapping("login")
	public String getLogin() throws Exception{
		return "member/login";
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
	
	@GetMapping("logoutResult")
	public String socialLogout() throws Exception{
		return "redirect:../";
	}
}

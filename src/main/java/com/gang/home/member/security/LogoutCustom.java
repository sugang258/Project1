package com.gang.home.member.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.gang.home.member.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LogoutCustom implements LogoutHandler{
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		// TODO Auto-generated method stub
	log.info("========== LogoutHandler ===========");
	//1. 일반 로그인?? 아니면 social login 서비스를 이용한건지??
	log.info("Auth => {}",authentication);
	
	MemberVO memberVO =(MemberVO) authentication.getPrincipal(); //memberVO
	
	String social = memberVO.getSocial();
	if(social != null && social.equals("kakao")) {
//		try {
//			response.sendRedirect("https://kauth.kakao.com/oauth/logout?client_id=dc329e2221f6cd70dee64dd589b72cc8&logout_redirect_uri=http://localhost:82/member/logoutResult");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		RestTemplate resttemplate = new RestTemplate();
		//header x
		//parameter x
		ResponseEntity<String> res =resttemplate.getForEntity("https://developers.kakao.com/logout",null ,String.class);
		log.info("res => {}",res);
		
	}else if(social != null && social.equals("google")) {
		
	}else {
		
	}
	
	request.getSession().invalidate();
	
	
	
	
	
	}

	
}

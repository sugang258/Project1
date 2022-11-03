package com.gang.home.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class TestInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse reponse, Object handler) throws Exception{
		
			log.info("==========PreHandle=============");
			return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse reponse, Object handler,ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		log.info("==========PostHandle==========");
	}

}

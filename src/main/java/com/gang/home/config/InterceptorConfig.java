package com.gang.home.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import com.gang.home.interceptors.StudyInterceptor;
import com.gang.home.interceptors.TestInterceptor;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class InterceptorConfig implements WebMvcConfigurer {
	
	@Autowired
	private TestInterceptor testInterceptor;
	@Autowired
	private StudyInterceptor studyInterceptor;
	@Autowired
	private LocaleChangeInterceptor localeChangeInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		//method 체이닝
		//적용할 Interceptor 등록
		registry.addInterceptor(testInterceptor)
				.addPathPatterns("/qna/**")
				.addPathPatterns("/notice/**")
		//제외할 url 등록
				.excludePathPatterns("/qna/detail")
				.excludePathPatterns("/qna/write");
		
		//추가 Interceptor등록
		registry.addInterceptor(studyInterceptor)
				.addPathPatterns("/qna/**");
		
		registry.addInterceptor(localeChangeInterceptor)
				.addPathPatterns("/**");
			
	}

}

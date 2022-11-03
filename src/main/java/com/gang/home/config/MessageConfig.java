package com.gang.home.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class MessageConfig implements WebMvcConfigurer{

	@Bean
	public LocaleResolver locale() {
		//둘 중 하나만 사용
		//1. session
		SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setDefaultLocale(Locale.KOREAN);
		
		//2. Cookie
		CookieLocaleResolver cResolver = new CookieLocaleResolver();
		cResolver.setDefaultLocale(Locale.KOREAN);
		cResolver.setCookieName("lang");
		
		
		return resolver;
		//return cResolver;
		
	}
	
	//Interceptor 객체
	@Bean
	public LocaleChangeInterceptor changeInterceptor() {
		LocaleChangeInterceptor changeInterceptor = new LocaleChangeInterceptor();
		changeInterceptor.setParamName("lang");
		//parameter를 받아서 언어 구분
		//url?lang=en
		return changeInterceptor;
	}
	
	// ***-context.xml
	// <bean class="" id=""> == new 생성자()
	//bean => 외부 라이브러리 사용할때
	//	@Bean("my")
	//	public String getString() {
	//		return new String();
	//	}
	
}

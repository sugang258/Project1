package com.gang.home.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	//public을 선언하면 default로 바꾸라는 메세지 출력
	WebSecurityCustomizer webSecurityConfig() {
		//Security에서 무시해야하는 URL 패턴 등록
		return web -> web
				.ignoring()
				.antMatchers("/images/**")
				.antMatchers("/css/**")
				.antMatchers("/js/**")
				.antMatchers("/favicon/**")
				.antMatchers("/resources/**");
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
		httpSecurity
					.cors()
					.and()
					.csrf()
					.disable()
				.authorizeRequests()
					.antMatchers("/").permitAll()
					.antMatchers("/member/join").permitAll()
					.antMatchers("/admin").hasRole("ADMIN")
					.antMatchers("/manager").hasRole("MANAGER")
					.antMatchers("/qna/list").permitAll()
					.antMatchers("/qna/add").hasRole("ADMIN")
					.anyRequest().authenticated()
					.and()
				.formLogin()
					.loginPage("/member/login")
					.passwordParameter("pw")
					.usernameParameter("id")
					.defaultSuccessUrl("/")
					.failureUrl("/member/login")
					.permitAll()
					.and()
				.logout()
					.logoutUrl("/member/logout")        
				 	.logoutSuccessUrl("/member/login")        
				 	.invalidateHttpSession(true).deleteCookies("JSESSIONID")
					.permitAll();
		
		return httpSecurity.build();
	}

    @Bean
    PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }
	

}

package com.gang.home.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.gang.home.member.MemberSecurityService;
import com.gang.home.member.MemberService;
import com.gang.home.member.MemberSocialService;
import com.gang.home.member.security.LoginFail;
import com.gang.home.member.security.LoginSuccess;
import com.gang.home.member.security.LogoutCustom;
import com.gang.home.member.security.LogoutSuccessCustom;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private MemberSecurityService memberSecurityService;
	@Autowired
	private LoginSuccess loginSuccess;
	@Autowired
	private LoginFail loginFail;
	@Autowired
	private LogoutSuccessCustom logoutSuccessCustom;
	@Autowired
	private LogoutCustom logoutCustom;
	@Autowired
	private MemberSocialService memberSocialService;
	
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
					.csrf()
					.disable()
					.cors()
					.configurationSource(this.corsConfigurationSource())
					.disable()
				.authorizeRequests()
					.antMatchers("/").permitAll()
					.antMatchers("/member/join").permitAll()
					.antMatchers("/admin").hasRole("ADMIN")
					.antMatchers("/manager").hasRole("MANAGER")
					.antMatchers("/qna/list").permitAll()
					//.antMatchers("/qna/add").hasRole("ADMIN")
					.anyRequest().authenticated()
					.and()
				.formLogin()
					.loginPage("/member/login")
					.passwordParameter("pw")
					.usernameParameter("id")
					//.defaultSuccessUrl("/")
					.successHandler(loginSuccess)
					//.failureUrl("/member/login")
					.failureHandler(loginFail)
					.permitAll()
					.and()
				.logout()
					.logoutUrl("/member/logout")        
				 	//.logoutSuccessUrl("/member/login")
					//.logoutSuccessHandler(logoutSuccessCustom)
					.addLogoutHandler(logoutCustom)
				 	.invalidateHttpSession(true)
				 	.deleteCookies("JSESSIONID")
					.permitAll()
					.and()
				.rememberMe() //RememberMe 설정(아이디 기억하기)
					.rememberMeParameter("rememberMe") //파라미터명
					.tokenValiditySeconds(300)			//로그인 유지 유지시간, 초단위
					.key("rememberMe") //인증받은 사용자의 정보로 Token 생성시 필요, 필수값
					.userDetailsService(memberSecurityService) //인증 절차를 실행할 userdetailService, 필수
					.authenticationSuccessHandler(loginSuccess) //Login 성공 Handler
					.and()
				.oauth2Login()
					.userInfoEndpoint()
					.userService(memberSocialService)
					;
		
		return httpSecurity.build();
	}

    @Bean
    PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    //@Bean
    CorsConfigurationSource corsConfigurationSource() {
    	CorsConfiguration configuration = new CorsConfiguration();
    	configuration.setAllowedOrigins(Arrays.asList("http://192.168.1.63:5500"));
    	configuration.setAllowedMethods(Arrays.asList("GET","POST"));
    	
    	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    	source.registerCorsConfiguration("/**", configuration);
    	
    	return source;
    }
	

}

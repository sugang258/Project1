package com.gang.home.member;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class MemberVO implements UserDetails{

	private String  id;
	private String pw;
	private String name;
	private String email;
	private boolean enabled;
	private List<RoleVO> roleVOs;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//ROLE의 이름을 꺼내서 list에 넣기
		//로그인한 유저의 권한 보기
		List<GrantedAuthority> authorities = new ArrayList<>();
		for(RoleVO roleVO : roleVOs) {
			authorities.add(new SimpleGrantedAuthority(roleVO.getRoleName()));
		}
		return authorities;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.getPw();
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.id;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	
	

}

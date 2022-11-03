package com.gang.home.member;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MemberMapper {
	
	public int setJoin(MemberVO memberVO) throws Exception;
	public MemberVO getLogin(String username) throws UsernameNotFoundException;
	public int setRole(MemberVO memberVO) throws Exception;

}

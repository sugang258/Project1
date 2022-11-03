package com.gang.home.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	public int setJoin(MemberVO memberVO) throws Exception{
		memberVO.setPw(passwordEncoder.encode(memberVO.getPw()));

		int result = memberMapper.setJoin(memberVO);
		
		if(result < 1) {
			throw new Exception();
		}
		result = memberMapper.setRole(memberVO);
		
		if(result <1) {
			throw new Exception();
		}
		return result;
	}
	
	public int setRole(MemberVO memberVO) throws Exception{
		return memberMapper.setRole(memberVO);
	}
	
	

}

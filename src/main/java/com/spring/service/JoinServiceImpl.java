package com.spring.service;

import org.springframework.stereotype.Service;

import com.spring.domain.MembersDTO;
import com.spring.mapper.CommunityMapper;
import com.spring.mapper.JoinMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JoinServiceImpl  implements JoinService{
	private final JoinMapper mapper;

	@Override
	public int registerMembers(MembersDTO mdto) {
		return mapper.registerMembers(mdto);
	}
	
	@Override
	public int emailCheck(String email) {
		int cnt = mapper.emailCheck(email);
		return cnt;
	}
	
	@Override
	public int nicknameCheck(String nickname) {
		int cnt = mapper.nicknameCheck(nickname);
		return cnt;
	}
	@Override
	public int phoneCheck(int phone) {
		int cnt = mapper.phoneCheck(phone);
		return cnt;
	}

}

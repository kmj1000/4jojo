package com.spring.service;

import com.spring.domain.MembersDTO;

public interface JoinService {
	public int registerMembers(MembersDTO mdto);
	public int emailCheck(String email); 
}

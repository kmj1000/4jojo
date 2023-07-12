package com.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.domain.P_DTO;
import com.spring.mapper.P_Mapper;
import com.spring.util.PageMaker;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class P_ServiceImpl implements P_Service {
	private final P_Mapper mapper;
	@Override
	public List<P_DTO> getP(int pet_notice_no) {
	
		return mapper.selectP(pet_notice_no);
	}

	@Override
	public List<P_DTO> getAllBoard() {
		
		return mapper.selectAllBoard();
	}


	@Override
	public List<P_DTO> getAllBoardByPage(PageMaker pageMaker) {
		return mapper.selectAllBoardByPage(pageMaker);
	}

	@Override
	public int getCountAllBoard() {
		int result = mapper.selectCountAllBoard();
		return result;
	}


	

}

package com.spring.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.spring.domain.F_W_DTO;
import com.spring.domain.W_DTO;
import com.spring.service.W_Service;
import com.spring.util.Criteria;
import com.spring.util.PageMaker;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
@Log4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/with/*")
public class WithpetController {
	private final W_Service service;
	
	@GetMapping("/withall")
	public String getAllBoard(Criteria cri, Model model) {
		int totalCount = service.getCountAllBoard();
		PageMaker pageMaker = new PageMaker(cri, totalCount); 
		model.addAttribute("withList",service.getAllBoardByPage(pageMaker));
		model.addAttribute("pageMaker",pageMaker);
		return "/with/with";
	}
	@GetMapping("/withdetail")
	public String getW(int with_pet_no, Model model) {
		model.addAttribute("withdetailList",service.getW(with_pet_no));
		return "/with/withdetail";
	}
	
	@RequestMapping("/withselect")
	public String SelectRegionPet(W_DTO dto) {
		service.getRegionWith(dto);
		return "/with/with";
	}
	
	
	@RequestMapping("/registerwith")
	@ResponseBody
	protected void  insertF_W(HttpServletRequest request, HttpServletResponse response
	  
	    
	)throws ServletException, IOException {
	    F_W_DTO dto = new F_W_DTO();
	    W_DTO dto2 = new W_DTO();
	    String with_pet_no = request.getParameter("with_pet_no");
		
		Date favoritew_reg_date = new Date(System.currentTimeMillis());
        if (with_pet_no == null || with_pet_no.equals("")) {
        	with_pet_no = "-1";
        }
        
        dto.setNickname(request.getParameter("nickname"));
        dto.setFavoritew_reg_date(favoritew_reg_date);
        dto2.setWith_pet_no(Integer.parseInt(with_pet_no));
        dto2.setBuilding( request.getParameter("building"));
        dto2.setCategory3( request.getParameter("category3"));
        dto2.setRoad( request.getParameter("road"));
        dto2.setHomepage( request.getParameter("homepage"));
        dto2.setHour( request.getParameter("hour"));
        dto2.setParking( request.getParameter("parking"));
        dto2.setWith_pet_info( request.getParameter("with_pet_info"));
        dto2.setOnly_pet_info( request.getParameter("only_pet_info"));
        dto2.setPet_size( request.getParameter("pet_size"));
        dto2.setPet_limit(request.getParameter("pet_limit"));
        dto2.setInside( request.getParameter("inside"));
        dto2.setOutside( request.getParameter("outside"));
        dto2.setExtra( request.getParameter("extra"));
		
		
		int result=service.registerWithpetData(dto2);
		//{ result : 11}
		
		

		
		JsonObject jsonObj = new JsonObject();
		jsonObj.addProperty("result", result);
	
		response.getWriter().println( new Gson().toJson(jsonObj) );
	}
	@RequestMapping("/removewith")
	@ResponseBody
	protected void  delete_W(HttpServletRequest request, HttpServletResponse response
	  
	    
	)throws ServletException, IOException {
	    F_W_DTO dto = new F_W_DTO();
	    W_DTO dto2 = new W_DTO();
	    String with_pet_no = request.getParameter("with_pet_no");
		
		Date favoritew_reg_date = new Date(System.currentTimeMillis());
        if (with_pet_no == null || with_pet_no.equals("")) {
        	with_pet_no = "-1";
        }
        
        dto.setNickname(request.getParameter("nickname"));
        dto.setFavoritew_reg_date(favoritew_reg_date);
        dto2.setWith_pet_no(Integer.parseInt(with_pet_no));
        dto2.setBuilding( request.getParameter("building"));
        dto2.setCategory3( request.getParameter("category3"));
        dto2.setRoad( request.getParameter("road"));
        dto2.setHomepage( request.getParameter("homepage"));
        dto2.setHour( request.getParameter("hour"));
        dto2.setParking( request.getParameter("parking"));
        dto2.setWith_pet_info( request.getParameter("with_pet_info"));
        dto2.setOnly_pet_info( request.getParameter("only_pet_info"));
        dto2.setPet_size( request.getParameter("pet_size"));
        dto2.setPet_limit(request.getParameter("pet_limit"));
        dto2.setInside( request.getParameter("inside"));
        dto2.setOutside( request.getParameter("outside"));
        dto2.setExtra( request.getParameter("extra"));
		
		
		int result=service.removeWithpetData(Integer.parseInt(with_pet_no));
		//{ result : 1}
		
		

		
		JsonObject jsonObj = new JsonObject();
		jsonObj.addProperty("result", result);
	
		response.getWriter().println( new Gson().toJson(jsonObj) );
	}
}

package com.spring.controller;

import java.io.IOException;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;
import com.spring.domain.FavoriteWithDTO;
import com.spring.domain.WithDTO;
import com.spring.service.FavoriteWithService;
import com.spring.service.WithService;
import com.spring.util.Criteria;
import com.spring.util.PageMaker;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/with/*")

public class WithController {
	private final WithService service;
	private final FavoriteWithService fwservice;

	// 카테고리별 선택 > 지역별 조회
	@RequestMapping("/withall")
	@ResponseBody
	public ModelAndView getWithList(@RequestParam(value = "region", required = false, defaultValue = "") String region,
			@RequestParam(value = "pageNum", required = false, defaultValue = "1") int pageNum,
			@RequestParam(value = "category3", required = false) String category3,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "keyword", required = false) String keyword, Model model, Criteria cri) {

		ModelAndView mav = new ModelAndView("/with/with");

		PageMaker pageMaker;
		List<WithDTO> withList;
		int totalCount = 0;
		cri = new Criteria(pageNum);
		Map<String, Object> response = new HashMap<>();

		totalCount = service.getWithCount(type, keyword, region, category3);
		pageMaker = new PageMaker(cri, totalCount);
		withList = service.getWithList(type, keyword, region, category3, pageMaker);

		response.put("withList", withList);
		model.addAttribute("pageMaker", pageMaker);
		mav.addObject("response", response);
		return mav;

	}

	@RequestMapping("/withdetail")
	public ModelAndView getAllBoard(HttpSession session, int with_pet_no, Model model, String nickname) {
		Boolean SESS_AUTH = (Boolean) session.getAttribute("SESS_AUTH");
		nickname = (String) session.getAttribute("SESS_NICKNAME");
		List<WithDTO> withdetailList = service.getWithDetail(with_pet_no);
		List<FavoriteWithDTO> fawList = fwservice.getIsLikedWith(nickname);
		if (SESS_AUTH != null && SESS_AUTH) {

			ModelAndView mav = new ModelAndView("/with/withdetail");
			mav.addObject("withdetailList", withdetailList);
			mav.addObject("fawList", fawList);
			return mav;
		} else {
			ModelAndView mav2 = new ModelAndView("/main/main");
			return mav2;
		}
	}

	// 즐겨찾기
	@RequestMapping("/registerwith")
	@ResponseBody
	public String insertFavoriteWithData(HttpSession session, @RequestParam(required = false) String with_pet_no,
			@RequestParam(required = false) String nickname, @RequestParam(required = false) String building,
			@RequestParam(required = false) String category3, @RequestParam(required = false) String road,
			@RequestParam(required = false) String homepage, @RequestParam(required = false) String hour,
			@RequestParam(required = false) String parking, @RequestParam(required = false) String with_pet_info,
			@RequestParam(required = false) String only_pet_info, @RequestParam(required = false) String pet_size,
			@RequestParam(required = false) String pet_limit, @RequestParam(required = false) String inside,
			@RequestParam(required = false) String outside, @RequestParam(required = false) String extra)
			throws ServletException, IOException {
		FavoriteWithDTO dto = new FavoriteWithDTO();
		WithDTO dto2 = new WithDTO();
		Date favoritew_reg_date = new Date(System.currentTimeMillis());
		if (with_pet_no == null || with_pet_no.equals("")) {
			with_pet_no = "-1";
		}
		nickname = (String) session.getAttribute("SESS_NICKNAME");
		dto.setNickname(nickname);
		dto.setFavoritew_reg_date(favoritew_reg_date);
		dto2.setWith_pet_no(Integer.parseInt(with_pet_no));
		dto2.setBuilding(building);
		dto2.setCategory3(category3);
		dto2.setRoad(road);
		dto2.setHomepage(homepage);
		dto2.setHour(hour);
		dto2.setParking(parking);
		dto2.setWith_pet_info(with_pet_info);
		dto2.setOnly_pet_info(only_pet_info);
		dto2.setPet_size(pet_size);
		dto2.setPet_limit(pet_limit);
		dto2.setInside(inside);
		dto2.setOutside(outside);
		dto2.setExtra(extra);

		service.registerWithData(nickname, dto2);

		JsonObject jsonObj = new JsonObject();
		jsonObj.addProperty("success", true);

		return jsonObj.toString();
	}

	@RequestMapping("/removewith")
	@ResponseBody
	public String deleteFavoriteWithData(@RequestParam(required = false) String with_pet_no,
			@RequestParam(required = false) String nickname, @RequestParam(required = false) String building,
			@RequestParam(required = false) String category3, @RequestParam(required = false) String road,
			@RequestParam(required = false) String homepage, @RequestParam(required = false) String hour,
			@RequestParam(required = false) String parking, @RequestParam(required = false) String with_pet_info,
			@RequestParam(required = false) String only_pet_info, @RequestParam(required = false) String pet_size,
			@RequestParam(required = false) String pet_limit, @RequestParam(required = false) String inside,
			@RequestParam(required = false) String outside, @RequestParam(required = false) String extra

	) throws ServletException, IOException {
		FavoriteWithDTO dto = new FavoriteWithDTO();
		WithDTO dto2 = new WithDTO();
		Date favoritew_reg_date = new Date(System.currentTimeMillis());
		if (with_pet_no == null || with_pet_no.equals("")) {
			with_pet_no = "-1";
		}
		dto.setNickname(nickname);
		dto.setFavoritew_reg_date(favoritew_reg_date);
		dto2.setWith_pet_no(Integer.parseInt(with_pet_no));
		dto2.setBuilding(building);
		dto2.setCategory3(category3);
		dto2.setRoad(road);
		dto2.setHomepage(homepage);
		dto2.setHour(hour);
		dto2.setParking(parking);
		dto2.setWith_pet_info(with_pet_info);
		dto2.setOnly_pet_info(only_pet_info);
		dto2.setPet_size(pet_size);
		dto2.setPet_limit(pet_limit);
		dto2.setInside(inside);
		dto2.setOutside(outside);
		dto2.setExtra(extra);

		int result = service.removeWithData(Integer.parseInt(with_pet_no));

		JsonObject jsonObj = new JsonObject();
		jsonObj.addProperty("result", result);

		return jsonObj.toString();
	}

	@GetMapping("/withca")
	public String selectCa() {
		return "/with/withca";
	}

}

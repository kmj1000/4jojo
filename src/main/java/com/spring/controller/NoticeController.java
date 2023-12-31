package com.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.domain.MembersDTO;
import com.spring.domain.NoticeDTO;
import com.spring.service.LoginService;
import com.spring.service.NoticeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/notice/*")
@RequiredArgsConstructor
public class NoticeController {
	private final NoticeService service;
	
	@GetMapping("/new")
	public String test() {
		return "notice/NewFile";
	}
	
	@GetMapping("/nlist")
	public String NoticeList(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
        boolean SESS_AUTH = false;
         
        try {
            SESS_AUTH = (boolean)session.getAttribute("SESS_AUTH");
        }catch(Exception e) {}
         
        if( SESS_AUTH ) {
            request.setAttribute("SESS_AUTH", false);
            String email = (String) session.getAttribute("SESS_EMAIL");
            String nickname = (String) session.getAttribute("SESS_NICKNAME");
            
			model.addAttribute("noticeList", service.getAllNotice());
			return "notice/notice";
        }else {
        	model.addAttribute("noticeList", service.getAllNotice());
		return "notice/notice";
        }
	}
	
	@GetMapping("/newNot")
	public String moveRegi() {
		return "notice/notRegi";
	}
	
	@RequestMapping("/newNot")
	public String NoticeRegi(HttpServletRequest request, NoticeDTO noti, MembersDTO mdto) {
		HttpSession session = request.getSession();
        boolean SESS_AUTH = false;
         
        try {
            SESS_AUTH = (boolean)session.getAttribute("SESS_AUTH");
        }catch(Exception e) {}
         
        if( SESS_AUTH ) {
            request.setAttribute("SESS_AUTH", false);
            String email = (String) session.getAttribute("SESS_EMAIL");
            String nickname = (String) session.getAttribute("SESS_NICKNAME");
            
			int result=service.registerNotice(noti);
			if(result>0) {
				return "redirect:/notice/nlist";
			}else {
				return "redirect:/notice/notRegi";
			}
        }else {
        	return "redirect:/main/main";
        }
    }
	
	@GetMapping("/notSel")
	public String noticeSel(HttpServletRequest request, Model model, int notice_no, String nickname) {
		HttpSession session = request.getSession();
        boolean SESS_AUTH = false;
         
        try {
            SESS_AUTH = (boolean)session.getAttribute("SESS_AUTH");
        }catch(Exception e) {}
         
        if( SESS_AUTH ) {
            request.setAttribute("SESS_AUTH", false);
            String email = (String) session.getAttribute("SESS_EMAIL");

		NoticeDTO selectone=service.getNotice(notice_no);
		model.addAttribute("selectone", selectone);
		service.viewCount(notice_no);
		return "notice/notSel";
        }else {
        	NoticeDTO selectone=service.getNotice(notice_no);
    		model.addAttribute("selectone", selectone);
    		service.viewCount(notice_no);	
        	return "notice/notSel";
        }	
	}
	
	@RequestMapping("/notUp1")
	public String NoticeUdt(Model model, int notice_no, String nickname) {
		model.addAttribute("selectone",service.getNotice(notice_no));
			return "notice/notUp";
		}
	
	@PostMapping("/notUp")
	public String Updt(NoticeDTO not) {
	int	result=service.modifyNotice(not);
		if(result>0) {
			return "redirect:/notice/notSel?notice_no="+not.getNotice_no();
			
		}else {
			return "redirect:/notice/notUp?notice_no="+not.getNotice_no();
		}
	}
	
	@RequestMapping("/notDel")
	public String NoticeDel(int notice_no) {
	int	result=service.removeNotice(notice_no);
		if(result>0) {
			return "redirect:/notice/nlist";
		}else {
			return "redirect:/notice/notSel?notice_no="+notice_no;
		}
	}
	
}

package com.hanul.iot;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import common.CommonUtility;
import gone.GoneFileVO;
import gone.GoneServiceImpl;
import member.MemberServiceImpl;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired private GoneServiceImpl service;

	//오류처리
	@RequestMapping("/error")
	public String error(HttpServletRequest request, Model model ) {
		Throwable exception 
			= (Throwable)request.getAttribute("javax.servlet.error.exception");
		
		StringBuffer msg = new StringBuffer();
		while( exception != null ) {
			msg.append("<p>").append( exception.getMessage()  ).append("</p>");
			exception = exception.getCause();
		}
		model.addAttribute("msg", msg.toString() );
		
		int code = (Integer)request.getAttribute("javax.servlet.error.status_code");
		return "default/error/" + (code==404 ? "404" : "common");
	}
	
	
	
	
	//시각화화면 요청
	@RequestMapping("/visual/list")
	public String list(HttpSession session) {
		session.setAttribute("category", "vi");
		return "visual/list";
	}
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired private MemberServiceImpl member;
	@Autowired private CommonUtility common;
	
	@GetMapping("/")
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	@RequestMapping(value = "/", method = { RequestMethod.POST,  RequestMethod.GET})
	public String home(Locale locale, Model model, HttpSession session) {
		/*
		//이미 저장된 비번을 암호화해서 DB에 저장해두기: 나중에 삭제 ------------------
		//모든회원정보 조회
		List<MemberVO> list = member.member_list();
		for(MemberVO vo : list) {
			if( vo.getSocial()!=null ) continue;
			if( vo.getSalt()!=null ) continue;
			//비번 암호화를 위한 솔트 생성
			String salt = common.generateSalt();
			//솔트를 사용해 비번을 암호화
			String pw = common.getEncrypt(vo.getPw(), salt);
			vo.setPw(pw);
			vo.setSalt(salt);
			member.member_change_pw(vo);
		}
		//----------------------------------------------------------------
		*/
		
		
		
		//DB에서 방명록 목록을 조회해온다
		List<GoneFileVO> list = (List<GoneFileVO>)service.GoneFile_list();
		
		model.addAttribute("list", list);
		
		session.removeAttribute("category");
		return "home";
		
	}
	
}

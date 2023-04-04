package com.hanul.iot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import common.CommonUtility;
import gone.GoneCommentVO;
import gone.GoneFileVO;
import gone.GonePageVO;
import gone.GoneServiceImpl;
import gone.GoneVO;
import location.LocationVO;
import member.MemberServiceImpl;
import member.MemberVO;
import course.CourseVO;

@Controller
public class MynameController {
	@Autowired private GoneServiceImpl service;
	
		
	
	
	//방명록 글 수정화면 요청
	@RequestMapping("/modify.na")
	public String modify(Model model, int id, GonePageVO page) {
		//선택한 글정보를 DB에서 조회한다.
		GoneVO vo = service.gone_info(id);		
		//화면에 출력할 수 있도록 Model에 담는다
		model.addAttribute("vo", vo);
		model.addAttribute("page", page);
		return "myname/modify";
	}
	


	//선택한 방명록 글화면 요청
	@RequestMapping("/info.na")
	public String info(Model model, int id, GonePageVO page) {
		//조회수처리
		service.gone_read(id);
		//선택한 글의 정보를 DB에서 조회해온다
		GoneVO vo = service.gone_info(id);
		model.addAttribute("vo", vo);
		//화면에 출력할 수 있도록 Model에 담는다
		model.addAttribute("page", page);
		model.addAttribute("vo", vo);
		model.addAttribute("crlf", "\r\n");
		model.addAttribute("lf", "\n");
		return "myname/info";
	}




	
	
	
	
	//방명록 목록화면 요청
	@RequestMapping("/list.na")
	public String myname(Model model, HttpSession session
						, GonePageVO page) {
		session.setAttribute("categort", "na");
		
		//DB에서 방명록 목록을 조회해온다
		page = service.gone_list(page);		
		//화면에 출력할 수 있도록 Model에 담는다
		model.addAttribute("page", page);
		
		return "myname/list";
	}
}

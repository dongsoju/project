package com.bteam.iot;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import course.CourseVO;
import location.LocationVO;
import course.CourseServiceImpl;

@Controller
public class CourseController {
	@Autowired private CourseServiceImpl service;
		
	//신규고객등록처리 요청
	@RequestMapping("/insert.co")
	public String insert(CourseVO vo) {
		service.course_insert(vo);
		return "redirect:list.co";
	}
	
	@ResponseBody @RequestMapping(value="/anLogin", produces="text/plain; charset=utf-8" )
	public String anLogin(HttpServletRequest req, Model model) {

		String id = (String) req.getParameter("id");
		String pw = (String) req.getParameter("pw");
		System.out.println("id : " + id + ", pw : " + pw);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pw", pw);
;
		CourseVO list = service.course_info("");
		
		if( list != null ) {
			
			Gson gson = new Gson();
			return gson.toJson( (CourseVO)list );
			
		}else {
			
			return "아이디 또는 페스워드 확인";
			
		}		
		
	}	
	@RequestMapping("/new.co")
	public String course(Model model) {
		List<LocationVO> list = service.location_list();
		model.addAttribute("list", list);
		return "course/new";
	}
	
	//선택한 고객정보 수정저장처리 요청
	@RequestMapping("/update.co")
	public String update(CourseVO vo) {
		//화면에서 변경입력한 정보를 DB에 변경저장한다
		service.course_update(vo);
		//응답화면연결-고객정보
		return "redirect:info.co?id=" + vo.getId();
	}
	
	
	
	//선택한 고객정보 수정화면 요청
	@RequestMapping("/modify.co")
	public String modify(Model model, int id) {
		//선택한 고객정보를 DB에서 조회해와
		CourseVO vo = service.course_info(id);
		List<LocationVO> list = service.location_list();
		//고객수정화면에서 조회한 정보를 출력할 수 있도록 Model에 담는다
		model.addAttribute("vo", vo);
		model.addAttribute("list", list);
		//응답화면연결 - 고객수정
		return "course/modify";
	}
	
	@RequestMapping("/delete.co")
	public String delete(int id) {
		service.course_delete(id);
		return "redirect:list.co";
	}
	
	
	@RequestMapping("/info.co")
	public String info(int id, Model model) {
		CourseVO vo = service.course_info(id);
		model.addAttribute("vo", vo);
		
		//응답화면연결
		return "course/info";
	}
	
	
	//고객목록화면 요청
	@RequestMapping("/list.co")
	public String list(Model model, HttpSession session ) {
		session.setAttribute("category", "cu");
		List<CourseVO> list = service.course_list();
		model.addAttribute("list", list);
		return "course/list";
	}
}

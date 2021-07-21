package com.example.randomStyle.controller;

import javax.inject.Inject;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.randomStyle.model.member.dto.MemberDTO;
import com.example.randomStyle.service.memberService.MemberService;

@Controller
@RequestMapping("member/*")
public class MemberController {

	
	@Inject
	MemberService memberService;
	
	@RequestMapping("address.do")
	public String address() {
		return "randomStyle/address";
	}
	
	@RequestMapping("login_check.do")
	@ResponseBody
	public ModelAndView login(@ModelAttribute MemberDTO dto, HttpSession session) {
			
			String name = memberService.loginCheck(dto, session);
			session.setAttribute("userid", dto.getUserid());
			ModelAndView mav = new ModelAndView();
			if (name != null && !name.equals("null")) {
				mav.setViewName("randomStyle/main");

			} else {
				mav.setViewName("randomStyle/home");
				mav.addObject("message", "error");
			}

			return mav;		
	}
	
	@RequestMapping("login.do")
	public String login() {
		return "randomStyle/home";
	}
	
	
	@RequestMapping("join.do")
	public String join() {
		return "randomStyle/join";
	}
	
	@RequestMapping("main.do")
	public String main() {
		return "randomStyle/main";
	}
	
	@RequestMapping("sign_up.do")
	public ModelAndView signUp(@ModelAttribute MemberDTO dto) {

		memberService.memberInsert(dto);
		
		String name = dto.getName();
		ModelAndView mav = new ModelAndView();
			mav.setViewName("randomStyle/join_result");
			mav.addObject("result", name + "님 환영합니다");
		return mav;
}	
		
	@RequestMapping("idCheck.do")
	public ModelAndView idCheck(@RequestParam String userid) {
		int result = 0;
		result = memberService.idCheck(userid);
		ModelAndView mav = new ModelAndView();
		if(result == 0) {
			mav.setViewName("randomStyle/idCheck_result");
			mav.addObject("result","사용 가능한 아이디입니다.");
		} else {
			mav.setViewName("randomStyle/idCheck_result");
			mav.addObject("result","사용중인 아이디입니다.");
		}
		return mav;
	}
	
	@RequestMapping("logout.do")
	public ModelAndView logout(HttpSession session, ModelAndView mav) {
		memberService.logout(session);
		mav.setViewName("randomStyle/home");
		mav.addObject("message", "logout");
		return mav;
	}
	
	@RequestMapping("and_login_check.do")
	@ResponseBody
	public JSONObject login_check_json(MemberDTO dto, HttpSession session) {
		String name = memberService.loginCheck(dto, session);
		JSONObject obj = new JSONObject();
		obj.put("name", name);
		return obj;
	}
	
	@RequestMapping("and_id_check.do")
	@ResponseBody
	public int and_id_check(@RequestParam String userid) {
		int result = 0;
		result = memberService.idCheck(userid);
		return result;
		  		
	}
	
	@RequestMapping("and_sign_up.do")
	@ResponseBody
	public String and_signUp(MemberDTO dto) {
		System.out.println("dto==========" + dto);
		memberService.memberInsert(dto);
		String name = dto.getName();
		return name;
}	
	
}

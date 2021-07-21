package com.example.randomStyle.controller;

import java.util.HashMap;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.randomStyle.service.randomService.RandomService;

@Controller
@RequestMapping("random/*")
public class RandomController {
	
	@Inject 
	RandomService randomService;
	

	
	@RequestMapping("random.do")
	public ModelAndView radndom(HttpServletRequest request, HttpSession session) {
		String userid = (String)session.getAttribute("userid");
		String[] category = request.getParameterValues("category");
		System.out.println("121212" +category); 
		HashMap <String, Object> map = new HashMap<String, Object>();
			map.put("category" ,category); 
			map.put("userid", userid); 
		HashMap<String, String> result_map= randomService.random(map);
	ModelAndView mav = new ModelAndView();
		mav.setViewName("randomStyle/random");
		mav.addObject("map", result_map);
		
		return mav; 
		 
		
	}
	
	@RequestMapping("random_check.do") 
	public ModelAndView random_check(@RequestParam String category, HttpSession session) {
		String userid = (String)session.getAttribute("userid");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("category", category);
		map.put("userid", userid);
		int result = randomService.random_check(map);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("randomStyle/random_check_result");
		mav.addObject("result", result); 
		mav.addObject("category", category);
		return mav; 
		
	} 
	
	@RequestMapping("and_random.do")
	@ResponseBody
	public HashMap<String, String> and_radndom(@RequestParam String param, @RequestParam String userid) {
		String[] category = param.split(",");
		System.out.println("category========" +category); 
		System.out.println("userid========" +userid); 
		HashMap <String, Object> map = new HashMap<String, Object>();
			map.put("category" ,category); 
			map.put("userid", userid); 
			System.out.println("return===========" +randomService.random(map));
		return randomService.random(map);
		 
	}
	
	

}

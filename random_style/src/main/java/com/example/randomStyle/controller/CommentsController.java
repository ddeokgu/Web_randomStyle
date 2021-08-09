package com.example.randomStyle.controller;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.randomStyle.model.comments.dto.CommentsDTO;
import com.example.randomStyle.service.commentsService.CommentsService;


@Controller
@RequestMapping("comments/*")
public class CommentsController {
	
	@Inject
	CommentsService commentsService;
	
	@RequestMapping("comments_count.do")
	public ModelAndView comments_count(@RequestParam int b_no, HttpSession session){
		session.setAttribute("b_no", b_no);
		int result = commentsService.Count_Comments(b_no);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("randomStyle/comments_count_result");
		mav.addObject("result", result);		
		return mav;
		
		
	}
	
	@RequestMapping("comments_update.do")
	public String comments_update(@RequestParam int b_no, HttpSession session){
		session.setAttribute("b_no", b_no);
		commentsService.Comments_update(b_no);	
		return "redirect:/board/detail.do?no="+b_no;
		
		
	}
	
	@RequestMapping("comments_update_min.do")
	public String comments_update_min(@RequestParam int b_no, HttpSession session){
		System.out.println("dfasdf"+ b_no);
		session.setAttribute("b_no", b_no);
		commentsService.Comments_update_min(b_no);	
		return "redirect:/board/detail.do?no="+b_no;
		
		
	}
	
	@RequestMapping("comments_list.do")
	public ModelAndView comments_list(@RequestParam int b_no) {
		List<CommentsDTO> list = commentsService.Comments_list(b_no);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("randomStyle/comments_list_result");
		mav.addObject("list", list);		
		return mav;
		
		
	}
	
	@RequestMapping("comments_write.do")
	public String comments_write(@ModelAttribute CommentsDTO dto) {
		int b_no = dto.getB_no();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("b_no", dto.getB_no());
		map.put("userid", dto.getUserid());
		map.put("comments", dto.getComments());
		commentsService.Comments_write(map);
		return "redirect:/comments/comments_update.do?b_no="+b_no;
	}
	
	@RequestMapping("comments_delete.do")
	public String comments_delete(@RequestParam int c_no, HttpSession session) {
		int b_no = (Integer) session.getAttribute("b_no");
		commentsService.Comments_delete(c_no);
		return "redirect:/comments/comments_update_min.do?b_no="+b_no;
	}
	
	@RequestMapping("and_comments_list.do")
	@ResponseBody
	public JSONObject and_comments_list(@RequestParam int b_no) {
		return commentsService.and_comments_list(b_no);
		
	}
	
	@RequestMapping("and_comments_write.do")
	public String and_comments_write(CommentsDTO dto) {
		System.out.println("dto============" + dto);
		int b_no = dto.getB_no();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("b_no", dto.getB_no());
		map.put("userid", dto.getUserid());
		map.put("comments", dto.getComments());
		commentsService.Comments_write(map);
		return "redirect:/comments/and_comments_update.do?b_no="+b_no;
	}
	
	@RequestMapping("and_comments_update.do")
	public void and_comments_update(@RequestParam int b_no){
		System.out.println("b_no========" + b_no);
		commentsService.Comments_update(b_no);	
		
		
		
	}
	@RequestMapping("and_comments_delete.do")	
	public String and_comments_delete(@RequestParam int c_no,@RequestParam int b_no) { 
		System.out.println("delete========"+c_no+","+b_no);
		commentsService.Comments_delete(c_no);
		return "redirect:/comments/and_comments_update_min.do?b_no="+b_no; 
		
	}
	
	@RequestMapping("and_comments_update_min.do") 
	public void and_comments_update_min(@RequestParam int b_no){
		System.out.println("min========" + b_no); 
		commentsService.Comments_update_min(b_no);	

		
	}
	@RequestMapping("and_comments_count.do")
	@ResponseBody
	public int and_comments_count(@RequestParam int b_no){
		int result = commentsService.Count_Comments(b_no);
		return result; 
		
		
	}

}

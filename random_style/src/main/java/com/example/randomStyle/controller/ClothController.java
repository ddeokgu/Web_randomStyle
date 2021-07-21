package com.example.randomStyle.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.randomStyle.model.cloth.dto.ClothDTO;
import com.example.randomStyle.service.clothService.ClothService;

@Controller
@RequestMapping("cloth/*")
public class ClothController {
	
	@Inject
	ClothService clothService;
	
	@Resource(name = "itemsPath")
	String itemsPath; 
	
	@RequestMapping("cloth_list.do")
	public ModelAndView list(@RequestParam String category, 
			ModelAndView mav, HttpSession session) {
		System.out.println(category);
		
		String userid=(String)session.getAttribute("userid");
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("category", category);
		map.put("userid", userid);
		mav.setViewName("randomStyle/cloth_list_result");
		mav.addObject("list", clothService.Cloth_list(map));
		return mav; 
	}
	
	@RequestMapping("cloth.do")
	public String cloth(){
		return "randomStyle/cloth_insert";
				
	}
	@RequestMapping("cloth_detail.do")
	public ModelAndView cloth_detail(String no, ModelAndView mav){
		System.out.println(no);
		String result = clothService.cloth_detail(no);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("no", no);
		map.put("photo_url", result);
		mav.setViewName("randomStyle/cloth_detail");
		mav.addObject("dto", map);
		System.out.println(map);
		return mav;
				
	}
	
	@RequestMapping("cloth_insert.do")
	public String insert(@ModelAttribute ClothDTO dto,@RequestParam String category, 
			 MultipartFile file1) {
		UUID uuid = UUID.randomUUID();
		String filename1=""; 
		if(!dto.getFile1().isEmpty()) {
			String savedName = dto.getFile1().getOriginalFilename();
			filename1 = uuid.toString()+"_"+savedName; 
			try {
				File target1 = new File(itemsPath, filename1);
				FileCopyUtils.copy(file1.getBytes(), target1);
				new File(itemsPath).mkdir();
				dto.getFile1().transferTo(new File(itemsPath+filename1));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		dto.setCategory(category);
		dto.setPhoto_url(filename1);  
		clothService.Cloth_insert(dto);
		return "redirect:/cloth/cloth.do";
	}
	
	@RequestMapping("cloth_delete.do")
	public String delete_board(@RequestParam String no, HttpSession session) {
		String userid = (String)session.getAttribute("userid");
		System.out.println(no);
		clothService.Cloth_delete(no); 
		return "redirect:/board/list_userid.do?userid="+userid;
	}
	
	
	@RequestMapping("and_cloth_all_list.do")
	@ResponseBody
	public JSONObject and_cloth_list(@RequestParam String userid) {
		System.out.println("userid============" + userid);
			return clothService.and_Cloth_all_list(userid);
		
			
	}
	
	@RequestMapping("and_cloth_detail.do")
	@ResponseBody
	public String and_cloth_detail(@RequestParam String no) {
		return clothService.cloth_detail(no);
	}
	
	@RequestMapping("and_cloth_delete.do")
	@ResponseBody
	public String and_delete_board(@RequestParam String no, String userid) {
		System.out.println(no);
		clothService.Cloth_delete(no); 
		return "redirect:/board/list_userid.do?userid="+userid;
	}
	

	@RequestMapping("and_cloth_insert.do")
	@ResponseBody
	public void and_insert(ClothDTO dto,@RequestParam String category, 
			 MultipartFile file1) {
		System.out.println("file1========" +dto.getFile1() +","+ "userid======"+dto.getUserid() +","+ "category=========" + category);
		UUID uuid = UUID.randomUUID();
		String filename1=""; 
		if(!dto.getFile1().isEmpty()) {
			String savedName = dto.getFile1().getOriginalFilename();
			filename1 = uuid.toString()+"_"+savedName; 
			try {
				File target1 = new File(itemsPath, filename1);
				FileCopyUtils.copy(file1.getBytes(), target1);
				new File(itemsPath).mkdir();
				dto.getFile1().transferTo(new File(itemsPath+filename1));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		dto.setCategory(category);
		dto.setPhoto_url(filename1);  
		clothService.Cloth_insert(dto);
	}
	
	
}

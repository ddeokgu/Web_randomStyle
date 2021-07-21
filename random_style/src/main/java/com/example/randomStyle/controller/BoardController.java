package com.example.randomStyle.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.randomStyle.model.board.dto.BoardDTO;
import com.example.randomStyle.service.boardService.BoardService;

@Controller
@RequestMapping("board/*")
public class BoardController {

	@Inject
	BoardService boardService;

	@Resource(name = "uploadPath")
	String uploadPath;

	@RequestMapping("list.do")
	public ModelAndView list(ModelAndView mav) {
		mav.setViewName("randomStyle/board");
		mav.addObject("list", boardService.BoardList());
		return mav;
	}

	@RequestMapping("board_write.do")

	public String insert(@ModelAttribute BoardDTO dto, MultipartFile file1, MultipartFile file2,
			HttpServletRequest request) {
		UUID uuid = UUID.randomUUID();
		String filename1 = "-";
		String filename2 = "-";
		if (!dto.getFile1().isEmpty() && !dto.getFile2().isEmpty()) {
			filename1 = uuid.toString()+dto.getFile1().getOriginalFilename();
			filename2 = uuid.toString()+dto.getFile2().getOriginalFilename();
			try {

				File target1 = new File(uploadPath, filename1);
				File target2 = new File(uploadPath, filename2);
				new File(uploadPath).mkdir();
				FileCopyUtils.copy(file1.getBytes(), target1);
				FileCopyUtils.copy(file2.getBytes(), target2);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		dto.setPhoto1_url(filename1);
		dto.setPhoto2_url(filename2);
		boardService.BoardWrite(dto);

		return "redirect:/board/list.do";
	}

	@RequestMapping("delete_board.do")
	public String delete_board(@RequestParam String no, HttpSession session) {
		String userid = (String) session.getAttribute("userid");
		String[] b_no = no.split(",");

		for (int i = 0; i < b_no.length; i++) {
			boardService.BoardDelete(b_no[i]);

		}

		return "redirect:/board/list_userid.do?userid=" + userid;
	}

	@RequestMapping("detail.do")
	public ModelAndView detail(@RequestParam int no, HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		boardService.increaseViews(no, session);
		mav.setViewName("randomStyle/board_detail");
		mav.addObject("dto", boardService.BoardDetail(no));
		return mav;
	}

	@RequestMapping("likes_check.do")
	public ModelAndView likes_check(@RequestParam String b_no, @RequestParam String userid) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("b_no", b_no);
		map.put("userid", userid);

		int result = boardService.Likes_check(map);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("randomStyle/likes_check_result");
		mav.addObject("result", result);
		return mav;

	}

	@RequestMapping("likes_check_insert.do")
	public void likes_check_insert(@RequestParam String b_no, @RequestParam String userid) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("b_no", b_no);
		map.put("userid", userid);
		boardService.Likes_check_insert(map);

	}

	@RequestMapping("likes_check_delete.do")
	public void likes_check_delete(@RequestParam String b_no, @RequestParam String userid) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("b_no", b_no);
		map.put("userid", userid);
		boardService.Likes_check_delete(map);

	}

	@RequestMapping("likes.do")
	public String likes(@RequestParam int no, @RequestParam String userid) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("no", no);
		map.put("userid", userid);
		boardService.Likes(map);
		return "redirect:/board/detail.do?no=" + no;

	}

	@RequestMapping("likes_min.do")
	public String likes_min(@RequestParam int no, @RequestParam String userid) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("no", no);
		map.put("userid", userid);
		boardService.Likes_Min(map);
		return "redirect:/board/detail.do?no=" + no;

	}

	@RequestMapping("likes_count.do")
	public ModelAndView likes_count(@RequestParam int no) {

		int result = boardService.Count_Likes(no);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("randomStyle/likes_count_result");
		mav.addObject("result", result);
		return mav;

	}

	@RequestMapping("list_userid.do")
	public ModelAndView list_userid(@RequestParam String userid, ModelAndView mav) {
		mav.setViewName("randomStyle/user"); // 출력페이지 지정
		// 페이지에 전달할 데이터
		mav.addObject("list", boardService.BoardList_Userid(userid));
		return mav;

	}

	@RequestMapping("insert_board.do")
	public String insert_board() {
		return "randomStyle/board_write";

	}

	@RequestMapping("and_board_list.do")
	@ResponseBody
	public JSONObject book_list(Model model) {
		return boardService.and_board_list();
	}

	@RequestMapping("and_board_list_userid.do")
	@ResponseBody
	public JSONObject book_list_user(String userid) {
		return boardService.and_board_list_userid(userid);
	}

	@RequestMapping("and_board_detail.do")
	@ResponseBody
	public JSONObject borad_detail(@RequestParam int no, HttpSession session) throws Exception {
		boardService.increaseViews(no, session);
		return boardService.and_board_detail(no);

	}

	@RequestMapping("and_likes_check.do")
	@ResponseBody
	public int and_likes_check(@RequestParam String b_no, @RequestParam String userid) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("b_no", b_no);
		map.put("userid", userid);

		int result = 0;
		result = boardService.Likes_check(map);
		return result;

	}

	@RequestMapping("and_likes.do")
	@ResponseBody
	public void and_likes(@RequestParam int no, @RequestParam String userid) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("no", no);
		map.put("userid", userid);
		System.out.println("likes.do========" + map);
		boardService.Likes(map);

	}

	@RequestMapping("and_likes_min.do")
	@ResponseBody
	public void and_likes_min(@RequestParam int no, @RequestParam String userid) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("no", no);
		map.put("userid", userid);
		boardService.Likes_Min(map);
		System.out.println("likes.min.do========" + map);

	}

	@RequestMapping("and_likes_check_insert.do")
	@ResponseBody
	public void and_likes_check_insert(@RequestParam String b_no, @RequestParam String userid) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("b_no", b_no);
		map.put("userid", userid);
		boardService.Likes_check_insert(map);

	}

	@RequestMapping("and_likes_check_delete.do")
	@ResponseBody
	public void and_likes_check_delete(@RequestParam String b_no, @RequestParam String userid) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("b_no", b_no);
		map.put("userid", userid);
		boardService.Likes_check_delete(map);

	}

	@RequestMapping("and_likes_count.do")
	@ResponseBody
	public int and_likes_count(@RequestParam int no) {
		System.out.println("no==========" + no);
		int result = boardService.Count_Likes(no);
		System.out.println("count_result==========" + result);
		return result;

	}

	@RequestMapping("and_board_write.do")
	@ResponseBody 
	public void and_insert(BoardDTO dto) {
		System.out.println("dto========" + dto);
		System.out.println("test========"+dto.getComments()+","+dto.getTitle()+","+dto.getUserid()+","+dto.getFile1()+","+dto.getFile2());
		UUID uuid = UUID.randomUUID(); 
	
		String filename1 = "-"; 
		String filename2 = "-"; 
		if (!dto.getFile1().isEmpty() && !dto.getFile2().isEmpty()) {
			filename1 = uuid.toString()+dto.getFile1().getOriginalFilename();
			filename2 = uuid.toString()+dto.getFile2().getOriginalFilename();
			System.out.println("filename===============" + filename1 + filename2);
			try {

				File target1 = new File(uploadPath, filename1);
				File target2 = new File(uploadPath, filename2);
				new File(uploadPath).mkdir();
				FileCopyUtils.copy(dto.getFile1().getBytes(), target1);
				FileCopyUtils.copy(dto.getFile2().getBytes(), target2);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		dto.setPhoto1_url(filename1);
		dto.setPhoto2_url(filename2);
		boardService.BoardWrite(dto);


	}
	
	@RequestMapping("and_board_delete.do")
	@ResponseBody
	public void and_delete_board(@RequestParam String no) {
		System.out.println("no================" + no);
			boardService.BoardDelete(no);

}
	
}

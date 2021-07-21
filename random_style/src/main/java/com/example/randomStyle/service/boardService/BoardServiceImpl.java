package com.example.randomStyle.service.boardService;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.randomStyle.model.board.dao.BoardDAO;
import com.example.randomStyle.model.board.dto.BoardDTO;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	BoardDAO boardDao;

	@Override
	public List<BoardDTO> BoardList() {
		return boardDao.BoardList();
	}

	@Override
	public void BoardWrite(BoardDTO dto) {
		boardDao.BoardWrite(dto);

	}

	@Override
	public void BoardDelete(String b_no) {
		boardDao.BoardDelete(b_no);

	}

	@Override
	public BoardDTO BoardDetail(int no) {

		return boardDao.BoardDetail(no);
	}

	@Override
	public int Likes_check(HashMap<String, Object> map) {
		return boardDao.Likes_check(map);

	}

	@Override
	public void Likes_check_insert(HashMap<String, Object> map) {
		boardDao.Likes_check_insert(map);

	}

	@Override
	public void Likes_check_delete(HashMap<String, Object> map) {
		boardDao.Likes_check_delete(map);

	}

	@Override
	public void Likes(HashMap<String, Object> map) {
		boardDao.Likes(map);

	}

	@Override
	public void Likes_Min(HashMap<String, Object> map) {
		boardDao.Likes_Min(map);

	}

	@Override
	public int Count_Likes(int no) {
		return boardDao.Count_Likes(no);
	}

	@Override
	public String fileInfo(int no) {
		return boardDao.fileInfo(no);
	}

	@Override
	public List<BoardDTO> BoardList_Userid(String userid) {
		return boardDao.BoardList_Userid(userid);
	}

	@Override
	public void increaseViews(int no, HttpSession session) throws Exception {
		long update_time = 0;
		if (session.getAttribute("update_time_" + no) != null) {
			update_time = (long) session.getAttribute("update_time_" + no);
		}
		long current_time = System.currentTimeMillis();
		if (current_time - update_time > 60 * 1000) {
			boardDao.increaseViews(no);
			session.setAttribute("update_time_" + no, current_time);
		}
	}
	
	@Override
	public JSONObject and_board_list() {
		return boardDao.and_board_list();
	}
	@Override
	public JSONObject and_board_list_userid(String userid) {
		return boardDao.and_board_list_userid(userid);
	}
	@Override
	public JSONObject and_board_detail(int no) {
		return boardDao.and_board_detail(no);
	}
	
	@Override
	public void and_board_write(BoardDTO dto) {
		boardDao.and_board_write(dto);
	}
		
	
	
}

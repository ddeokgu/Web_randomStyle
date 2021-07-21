package com.example.randomStyle.model.board.dao;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.example.randomStyle.model.board.dto.BoardDTO;

public interface BoardDAO {
	public List<BoardDTO> BoardList();
	public void BoardWrite(BoardDTO dto);
	void BoardDelete(String b_no);
	public BoardDTO BoardDetail(int no);
	public int Likes_check(HashMap<String, Object> map);
	public void Likes_check_insert(HashMap<String, Object> map);
	public void Likes_check_delete(HashMap<String, Object> map);
	public void Likes(HashMap<String, Object> map);
	public void Likes_Min(HashMap<String, Object> map);
	public int Count_Likes(int no);
	String fileInfo(int no);
	public List<BoardDTO> BoardList_Userid(String userid);
	public void increaseViews(int no);
	
	public JSONObject and_board_list();
	public JSONObject and_board_list_userid(String userid);
	public JSONObject and_board_detail(int no);
	public void and_board_write(BoardDTO dto);
}

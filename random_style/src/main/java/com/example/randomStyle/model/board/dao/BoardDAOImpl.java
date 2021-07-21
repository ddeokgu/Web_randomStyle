package com.example.randomStyle.model.board.dao;

import java.util.HashMap;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository;

import com.example.randomStyle.model.board.dto.BoardDTO;


@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@Inject
	SqlSession sqlSession;

	
	@Override
	public List<BoardDTO> BoardList() {
		return sqlSession.selectList("board.board_list");
	}

	@Override
	public void BoardWrite(BoardDTO dto) {
		sqlSession.insert("board.board_write", dto);

	}
	
	@Override
	public void BoardDelete(String b_no) {
		sqlSession.delete("board.board_delete", b_no);
		
		

	}

	@Override
	public BoardDTO BoardDetail(int no) {
		
		return sqlSession.selectOne("board.board_detail", no);
	}

	
	@Override
	public int Likes_check(HashMap<String, Object> map) {
		System.out.println("map============" + map);
		return sqlSession.selectOne("board.board_likes_check", map);

	}
	
	@Override
	public void Likes_check_insert(HashMap<String, Object> map) {
		sqlSession.insert("board.board_likes_check_insert", map);

	}
	
	@Override
	public void Likes_check_delete(HashMap<String, Object> map) {
		sqlSession.delete("board.board_likes_check_delete", map);

	}

	@Override
	public void Likes(HashMap<String, Object> map) {
		sqlSession.update("board.board_likes", map);

	}

	@Override
	public void Likes_Min(HashMap<String, Object> map) {
		sqlSession.update("board.board_likes_min", map);

	}

	@Override
	public int Count_Likes(int no) {
		return sqlSession.selectOne("board.count_likes", no);
		
	}
	
	@Override
	public String fileInfo(int no) {
		return sqlSession.selectOne("board.file_info", no);
	}
	
	@Override
	public List<BoardDTO> BoardList_Userid(String userid) {
		return sqlSession.selectList("board.board_list_userid", userid);
	}
	
	@Override
	public void increaseViews(int no) {
		sqlSession.update("board.increaseViews", no);

	}
	
	@Override
	public JSONObject and_board_list() {
		List<BoardDTO> items = sqlSession.selectList("board.board_list");
		JSONObject jsonMain = new JSONObject(); 
		JSONArray jArray = new JSONArray(); 
		int count = 0;
		for(BoardDTO dto : items) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("no", dto.getNo());
			jsonObj.put("userid", dto.getUserid());
			jsonObj.put("title", dto.getTitle());
			jsonObj.put("likes", dto.getLikes());
			jsonObj.put("comments", dto.getComments());
			jsonObj.put("views", dto.getViews());
			jArray.add(count, jsonObj);  
			count++;
			
			
		}
		jsonMain.put("sendData", jArray);
		
		return jsonMain;
	}

	@Override
	public JSONObject and_board_list_userid(String userid) {
		List<BoardDTO> items = sqlSession.selectList("board.board_list_userid",userid);
		JSONObject jsonMain = new JSONObject(); 
		JSONArray jArray = new JSONArray(); 
		int count = 0;
		for(BoardDTO dto : items) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("no", dto.getNo());
			jsonObj.put("userid", dto.getUserid());
			jsonObj.put("title", dto.getTitle());
			jsonObj.put("likes", dto.getLikes());
			jsonObj.put("comments", dto.getComments());
			jsonObj.put("views", dto.getViews());
			jArray.add(count, jsonObj);  
			count++;
			
			
		}
		jsonMain.put("sendData", jArray);
		return jsonMain;
	}
	
	@Override
	public JSONObject and_board_detail(int no) {
		BoardDTO dto =sqlSession.selectOne("board.board_detail", no);
		JSONObject jsonMain = new JSONObject(); 
		JSONArray jArray = new JSONArray(); 
		int count = 0;
		
			JSONObject jsonObj = new JSONObject();
			
			jsonObj.put("userid", dto.getUserid());
			jsonObj.put("title", dto.getTitle());
			jsonObj.put("likes", dto.getLikes());
			jsonObj.put("comments", dto.getComments());
			jsonObj.put("contents", dto.getContents());
			jsonObj.put("photo1_url", dto.getPhoto1_url());
			jArray.add(0, jsonObj);  
			count++;
		
		jsonMain.put("sendData", jArray);
		return jsonMain;
		
	}
	
	public void and_board_write(BoardDTO dto) {
		sqlSession.insert("board.board_write", dto);
	}



}

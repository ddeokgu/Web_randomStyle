package com.example.randomStyle.model.comments.dao;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository;

import com.example.randomStyle.model.board.dto.BoardDTO;
import com.example.randomStyle.model.comments.dto.CommentsDTO;

@Repository
public class CommentsDAOImpl implements CommentsDAO {
	
	@Inject
	SqlSession sqlSession;

	@Override
	public List<CommentsDTO> Comments_list(int b_no) {
		return sqlSession.selectList("comments.comments_list", b_no);
	}

	@Override
	public void Comments_write(HashMap<String, Object> map) {
		sqlSession.insert("comments.comments_write", map);

	}
	
	@Override
	public void Comments_delete(int c_no) { 
		sqlSession.delete("comments.comments_delete", c_no);

	}
	
	@Override
	public void Comments_update(int b_no) { 
		sqlSession.delete("comments.comments_update", b_no);

	}
	
	
	@Override
	public void Comments_update_min(int b_no) { 
		sqlSession.delete("comments.comments_update_min", b_no);

	}

	@Override 
	public int Count_Comments(int b_no) {
		return sqlSession.selectOne("comments.comments_count", b_no);
	}
	
	@Override
	public JSONObject and_comments_list(int b_no) {
		List<CommentsDTO> items = sqlSession.selectList("comments.comments_list",b_no);
		JSONObject jsonMain = new JSONObject(); 
		JSONArray jArray = new JSONArray(); 
		int count = 0;
		for(CommentsDTO dto : items) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("userid", dto.getUserid());
			jsonObj.put("comments", dto.getComments());
			jsonObj.put("date", dto.getWrite_date());
			jArray.add(count, jsonObj);  
			count++;
			
		}
		jsonMain.put("comments", jArray);
		
		return jsonMain;
	}

}

package com.example.randomStyle.model.comments.dao;

import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;

import com.example.randomStyle.model.comments.dto.CommentsDTO;

public interface CommentsDAO {
	public List<CommentsDTO> Comments_list(int b_no);
	public void Comments_write(HashMap<String, Object> map);
	public void Comments_delete(int c_no);
	public void Comments_update(int b_no);
	public void Comments_update_min(int b_no);
	public int Count_Comments(int b_no);
	public JSONObject and_comments_list(int b_no);
	
	

}

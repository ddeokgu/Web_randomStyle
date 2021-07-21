package com.example.randomStyle.model.cloth.dao;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository;

import com.example.randomStyle.model.board.dto.BoardDTO;
import com.example.randomStyle.model.cloth.dto.ClothDTO;

@Repository
public class ClothDAOImple implements ClothDAO {
	
	@Inject
	SqlSession sqlSession;

	@Override
	public List<ClothDTO> cloth_list(HashMap <String, String> map) {
		return sqlSession.selectList("cloth.cloth_list",map);
	}
	
	public void cloth_insert(ClothDTO dto) {
		sqlSession.insert("cloth.cloth_insert", dto);
	}
	
	public void cloth_delete(String no) {
		sqlSession.delete("cloth.cloth_delete",no);
	}
	
	public JSONObject and_cloth_all_list(String userid) {
		List<ClothDTO> list = sqlSession.selectList("cloth.cloth_all_list", userid);
		JSONObject jsonMain = new JSONObject(); 
		JSONArray jArray = new JSONArray(); 
		int count = 0;
		System.out.println("list========" + list);
		for(ClothDTO dto : list) {
			JSONObject jsonObj = new JSONObject();
			jsonObj.put("no", dto.getNo());
			jsonObj.put("category", dto.getCategory());
			jsonObj.put("photo_url", dto.getPhoto_url());
			jArray.add(count, jsonObj);  
			count++;
			
		}
		jsonMain.put("sendData", jArray);
		return jsonMain;
		
	}
	
	public String cloth_detail(String no) {
		return sqlSession.selectOne("cloth.cloth_detail", no);
	}

}

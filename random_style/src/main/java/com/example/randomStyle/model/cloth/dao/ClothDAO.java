package com.example.randomStyle.model.cloth.dao;

import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;

import com.example.randomStyle.model.cloth.dto.ClothDTO;

public interface ClothDAO {
	
 public List<ClothDTO>cloth_list(HashMap <String, String> map);
 public void cloth_insert(ClothDTO dto);
 public void cloth_delete(String no);
 public JSONObject and_cloth_all_list(String userid);
 public String cloth_detail(String no);
		
	

}

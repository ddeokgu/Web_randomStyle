package com.example.randomStyle.service.clothService;

import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;

import com.example.randomStyle.model.cloth.dto.ClothDTO;

public interface ClothService {
	public List<ClothDTO> Cloth_list(HashMap <String, String> map);
	public void Cloth_insert(ClothDTO dto);
	public void Cloth_delete(String no);
	public JSONObject and_Cloth_all_list(String userid);
	public String cloth_detail(String no);

}

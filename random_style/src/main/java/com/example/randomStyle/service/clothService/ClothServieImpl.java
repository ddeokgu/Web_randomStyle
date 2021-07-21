package com.example.randomStyle.service.clothService;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.example.randomStyle.model.cloth.dao.ClothDAO;
import com.example.randomStyle.model.cloth.dto.ClothDTO;

@Service
public class ClothServieImpl implements ClothService {
	
	@Inject
	ClothDAO clothDao;

	@Override
	public List<ClothDTO> Cloth_list(HashMap <String, String> map) {
		return clothDao.cloth_list(map);
	}
	
	@Override
	public void Cloth_insert(ClothDTO dto) {
		 clothDao.cloth_insert(dto);
	}
	
	@Override
	public void Cloth_delete(String no) {
		clothDao.cloth_delete(no);
	}
	
	
	@Override
	public JSONObject and_Cloth_all_list(String userid) {
		return clothDao.and_cloth_all_list(userid);
	}
	
	@Override
	public String cloth_detail(String no) {
		return clothDao.cloth_detail(no);
	}

}

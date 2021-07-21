package com.example.randomStyle.service.randomService;

import java.util.HashMap;

import javax.inject.Inject;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.example.randomStyle.model.random.dao.RandomDAO;

@Service
public class RandomServiceImpl implements RandomService {
	
	@Inject
	RandomDAO randomDao;

	@Override
	public HashMap<String, String>random(HashMap<String, Object> map) {
		return randomDao.random(map);
	}
	
	@Override
	public int random_check(HashMap<String, String> map) {
		return randomDao.random_check(map);
	}
	
	

}

package com.example.randomStyle.service.randomService;

import java.util.HashMap;

import org.json.simple.JSONObject;

public interface RandomService {
	
	public HashMap<String, String> random(HashMap<String, Object> map);
	public int random_check(HashMap<String, String> map);
	
}

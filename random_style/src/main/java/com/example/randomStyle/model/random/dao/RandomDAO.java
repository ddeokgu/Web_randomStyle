package com.example.randomStyle.model.random.dao;

import java.util.HashMap;

import org.json.simple.JSONObject;


public interface RandomDAO {
	public HashMap<String, String> random(HashMap<String, Object> map);
	public int random_check(HashMap<String, String> map);
	

}

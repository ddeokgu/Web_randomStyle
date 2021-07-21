package com.example.randomStyle.model.random.dao;

import java.util.HashMap;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class RandomDAOImpl implements RandomDAO {
	
	@Inject
	SqlSession sqlSession;

	@Override
	public HashMap<String, String> random(HashMap<String, Object> map) {
		String[] str = (String[]) map.get("category");
		String userid = map.get("userid").toString();
		HashMap<String, String> map1 = new HashMap<String, String>();
		for(int i = 0; i < str.length; i++) {
		HashMap<String, String > sql_map = new HashMap<String, String>();
		sql_map.put("userid", userid);
		sql_map.put("category",str[i]);
		String result = sqlSession.selectOne("random.random_list", sql_map);
		map1.put(str[i],result);
		     		
		}
		
		System.out.println("map1==========" + map1);
		
		return map1;
	}
	@Override
	public int random_check(HashMap<String, String> map) {
		return sqlSession.selectOne("random.random_check", map);
	}
	

}

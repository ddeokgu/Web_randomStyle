package com.example.randomStyle.model.member.dao;

import com.example.randomStyle.model.member.dto.MemberDTO;

public interface MemberDAO {
	public String loginCheck(MemberDTO dto);
	public void memberInsert(MemberDTO dto);
	public int idCheck(String userid);
	
	
	

}

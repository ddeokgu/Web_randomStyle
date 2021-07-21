package com.example.randomStyle.service.memberService;


import javax.servlet.http.HttpSession;

import com.example.randomStyle.model.member.dto.MemberDTO;

public interface MemberService {
	public String loginCheck(MemberDTO dto, HttpSession session);
	public void memberInsert(MemberDTO dto);
	public int idCheck(String userid);
	public void logout(HttpSession session);
	
}

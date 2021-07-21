package com.example.randomStyle.service.memberService;



import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.example.randomStyle.model.member.dao.MemberDAO;
import com.example.randomStyle.model.member.dto.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Inject
	MemberDAO memberDao;

	@Override
	public String loginCheck(MemberDTO dto, HttpSession session) {
		String name = memberDao.loginCheck(dto);
		if(name != null) {
			session.setAttribute("userid", dto.getUserid());
			session.setAttribute("name", name);
		}
		return name;
	}

	@Override
	public void memberInsert(MemberDTO dto) {
		memberDao.memberInsert(dto);

	}

	@Override
	public int idCheck(String userid) {
		
		return memberDao.idCheck(userid);
	}
	
	@Override
	public void logout(HttpSession session) {
		session.invalidate();

	}


}

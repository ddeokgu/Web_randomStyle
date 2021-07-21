package com.example.randomStyle.model.member.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.example.randomStyle.model.member.dto.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	@Inject
	SqlSession sqlSession;

	@Override
	public String loginCheck(MemberDTO dto) {
	
		return sqlSession.selectOne("member.login_check",dto);
	}

	@Override
	public void memberInsert(MemberDTO dto) {
		sqlSession.insert("member.member_insert", dto);

	}

	@Override
	public int idCheck(String userid) {
		return sqlSession.selectOne("member.id_check", userid);
	}
	


}

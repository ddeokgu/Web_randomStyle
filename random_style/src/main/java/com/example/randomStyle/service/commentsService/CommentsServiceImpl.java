package com.example.randomStyle.service.commentsService;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.example.randomStyle.model.comments.dao.CommentsDAO;
import com.example.randomStyle.model.comments.dto.CommentsDTO;
@Service
public class CommentsServiceImpl implements CommentsService {
	
	@Inject
	CommentsDAO commentsDao;

	@Override
	public List<CommentsDTO> Comments_list(int b_no) {
		return commentsDao.Comments_list(b_no);
	}

	@Override
	public void Comments_write(HashMap<String, Object> map) {
		commentsDao.Comments_write(map);

	}
	@Override
	public void Comments_delete(int c_no) {
		commentsDao.Comments_delete(c_no);
	}
	
	@Override
	public void Comments_update(int b_no) {
		commentsDao.Comments_update(b_no);

	}
	
	@Override
	public void Comments_update_min(int b_no) {
		commentsDao.Comments_update_min(b_no);

	}

	@Override
	public int Count_Comments(int b_no) {
		return commentsDao.Count_Comments(b_no);
	}
	
	@Override
	public JSONObject and_comments_list(int b_no) {
		return commentsDao.and_comments_list(b_no);
	}

}

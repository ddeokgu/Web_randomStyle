package com.example.randomStyle.model.cloth.dto;

import org.springframework.web.multipart.MultipartFile;

public class ClothDTO {
	
	private int no;
	private String userid;
	private String category;
	private String photo_url;
	private String file;
	private MultipartFile file1;
	
	
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPhoto_url() {
		return photo_url;
	}
	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
	}
	public MultipartFile getFile1() {
		return file1;
	}
	public void setFile1(MultipartFile file1) {
		this.file1 = file1;
	}
	@Override
	public String toString() {
		return "ClothDTO [no=" + no + ", userid=" + userid + ", category=" + category + ", photo_url=" + photo_url
				+ ", file=" + file + ", file1=" + file1 + "]";
	}
	
	

	
}

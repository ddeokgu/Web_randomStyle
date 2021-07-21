package com.example.randomStyle.model.member.dto;

public class MemberDTO {
	
	private String userid;
	private String passwd;
	private String name;
	private String email;
	private String phone;
	private String tel;
	private String zipcode;
	private String address1;
	private String address2;
	
	
	
	public MemberDTO() {
		super();
		
	}
	
	



	public MemberDTO(String userid, String passwd, String name, String email, String phone, String tel, String zipcode,
			String address1, String address2) {
		super();
		this.userid = userid;
		this.passwd = passwd;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.tel = tel;
		this.zipcode = zipcode;
		this.address1 = address1;
		this.address2 = address2;
	}





	public String getUserid() {
		return userid;
	}



	public void setUserid(String userid) {
		this.userid = userid;
	}



	public String getPasswd() {
		return passwd;
	}



	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getTel() {
		return tel;
	}



	public void setTel(String tel) {
		this.tel = tel;
	}



	public String getZipcode() {
		return zipcode;
	}



	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}



	public String getAddress1() {
		return address1;
	}



	public void setAddress1(String address1) {
		this.address1 = address1;
	}



	public String getAddress2() {
		return address2;
	}



	public void setAddress2(String address2) {
		this.address2 = address2;
	}



	@Override
	public String toString() {
		return "MemberDTO [userid=" + userid + ", passwd=" + passwd + ", name=" + name + ", email=" + email + ", phone="
				+ phone + ", tel=" + tel + ", zipcode=" + zipcode + ", address1=" + address1 + ", address2=" + address2
				+ "]";
	}
	

}

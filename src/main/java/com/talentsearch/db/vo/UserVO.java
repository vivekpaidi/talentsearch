package com.talentsearch.db.vo;

public class UserVO {

	private int userid;
	private String username;
	private String password;
	private String admin;
	
	
	
	public UserVO(int userid, String username, String password, String admin) {
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.admin = admin;
	}
	public int getUserid() {
		return userid;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getAdmin() {
		return admin;
	}
	
}

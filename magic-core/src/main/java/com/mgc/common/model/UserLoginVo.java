package com.mgc.common.model;

public class UserLoginVo {
	private Long userId;
	private String userName;
	private String password;
	private int version;
	private int loginCout=0;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public int getLoginCout() {
		return loginCout;
	}
	public void setLoginCout(int loginCout) {
		this.loginCout = loginCout;
	}
	
	
}

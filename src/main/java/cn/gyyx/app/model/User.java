package cn.gyyx.app.model;

import java.io.Serializable;

public class User implements Serializable {
	
	private String guidUserId;
	private String userName;
	private String userCode;
	private String userJober;
	private String userEmail;
	
	public String getGuidUserId() {
		return guidUserId;
	}
	public void setGuidUserId(String guidUserId) {
		this.guidUserId = guidUserId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserJober() {
		return userJober;
	}
	public void setUserJober(String userJober) {
		this.userJober = userJober;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
}

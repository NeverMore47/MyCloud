package com.wjh.entity;

import org.springframework.context.annotation.Scope;

@Scope("prototype")
public class User {

	private Integer uId;
	private String uName;
	private String uPwd;
	private String comment;
	private Integer isVip;

	public Integer getuId() {
		return uId;
	}

	public void setuId(Integer uId) {
		this.uId = uId;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String getuPwd() {
		return uPwd;
	}

	public void setuPwd(String uPwd) {
		this.uPwd = uPwd;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getIsVip() {
		return isVip;
	}

	public void setIsVip(Integer isVip) {
		this.isVip = isVip;
	}
}

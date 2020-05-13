package com.tledu.aaa.model;

import java.util.Date;

public class OperationLog {
	private int id;
	private Date come_time;
	private User user;
	private String desc;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getCome_time() {
		return come_time;
	}
	public void setCome_time(Date come_time) {
		this.come_time = come_time;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}

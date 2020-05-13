package com.tledu.aaa.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class MySchedule {
	private int id;
	private String title;
	private String address;
	private Meeting meeting;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date begin_time;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date end_time;
	private String content;
	private User user;
	//创建时间,自动设置
	private Date create_date;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Meeting getMeeting() {
		return meeting;
	}
	public void setMeeting(Meeting meeting) {
		this.meeting = meeting;
	}
	public Date getBegin_time() {
		return begin_time;
	}
	public void setBegin_time(Date begin_time) {
		this.begin_time = begin_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	
}

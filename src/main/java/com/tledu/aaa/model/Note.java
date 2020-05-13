package com.tledu.aaa.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Note {
private int id;
private String title;
private String content;
private User user;
@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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

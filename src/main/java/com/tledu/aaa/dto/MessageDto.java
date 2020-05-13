package com.tledu.aaa.dto;

import com.tledu.aaa.model.Message;

public class MessageDto {
private int id;
private String title;
private String content;
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

public Message getMessage(){
	Message message = new Message();
	message.setId(id);
	message.setTitle(title);
	message.setContent(content);
	return message;
}
}

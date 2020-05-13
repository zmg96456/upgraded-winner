package com.tledu.aaa.service;

import java.util.List;

import com.tledu.aaa.model.Message;
import com.tledu.aaa.model.MessageType;
import com.tledu.aaa.util.Pager;

public interface IMessageService {
	public Message load(int id);
	
	 public List<Message> list();

	public Pager<Message> find(String search, int page, int limit);
	
	public void update(Message message);
	
	public void add(Message message);
	
	public void delete(int id);
	
	public void edit(Message message);
	
	public List<MessageType> listMessageTypes();
	
}

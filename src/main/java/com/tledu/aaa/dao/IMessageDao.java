package com.tledu.aaa.dao;

import java.util.List;

import com.tledu.aaa.model.Message;
import com.tledu.aaa.model.MessageType;
import com.tledu.aaa.model.Note;
import com.tledu.aaa.util.Pager;

public interface IMessageDao {
	public Message load(int id);
	
	 public List<Message> list();
	 
	public int find_count(String search);

	public Pager<Message> find(String search, int page, int limit);
	
	public void update(Message message);
	
	public void add(Message message);
	
	public void delete(int id);
	
	public void edit(Message message);
	
	public List<MessageType> listMessageTypes();
	
}

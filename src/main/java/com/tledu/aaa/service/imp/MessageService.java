package com.tledu.aaa.service.imp;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tledu.aaa.dao.IMessageDao;
import com.tledu.aaa.model.Message;
import com.tledu.aaa.model.MessageType;
import com.tledu.aaa.service.IMessageService;
import com.tledu.aaa.util.Pager;
@Service
public class MessageService implements IMessageService {
	private IMessageDao messageDao;
	
	public IMessageDao getMessageDao() {
		return messageDao;
	}
	@Autowired
	public void setMessageDao(IMessageDao messageDao) {
		this.messageDao = messageDao;
	}

	@Override
	public Message load(int id) {
		
		return messageDao.load(id);
	}

	@Override
	public List<Message> list() {
		return messageDao.list();
	}

	@Override
	public Pager<Message> find(String search, int page, int limit) {
		try {
			if (search == null || search.trim().equals("")) {
				search = "";
			} else {
				// GET请求,汉字会乱码,需要转码
			search = search.trim();
			search = new String(search.getBytes("ISO-8859-1"), "UTF-8");
		} 
			search = "%" +search +"%";
			return messageDao.find(search, page, limit);
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;	
	}

	@Override
	public void update(Message message) {
		messageDao.update(message);
	}

	@Override
	public void add(Message message) {
	messageDao.add(message);
		
	}

	@Override
	public void delete(int id) {
	messageDao.delete(id);
		
	}

	@Override
	public void edit(Message message) {
	messageDao.edit(message);
		
	}

	@Override
	public List<MessageType> listMessageTypes() {
		
		return messageDao.listMessageTypes();
	}

}

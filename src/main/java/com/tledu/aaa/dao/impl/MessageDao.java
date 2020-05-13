package com.tledu.aaa.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tledu.aaa.dao.IMessageDao;
import com.tledu.aaa.model.Message;
import com.tledu.aaa.model.MessageType;
import com.tledu.aaa.util.Pager;
@Repository
public class MessageDao extends SqlSessionDaoSupport implements IMessageDao {
	
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	@Override
	public Message load(int id) {
		return getSqlSession().getMapper(IMessageDao.class).load(id);
	}

	@Override
	public List<Message> list() {

		return getSqlSession().getMapper(IMessageDao.class).list();
	}

	@Override
	public int find_count(String search) {
		
		return  getSqlSession().getMapper(IMessageDao.class).find_count(search);
	}

	@Override
	public Pager<Message> find(String search, int page, int limit) {
		Pager< Message> pager =new Pager<Message>();
		int count =  find_count(search);
		pager.setCount(count);
		Map<String, Object> map =new HashMap<String, Object>();
		map.put("pageOffset",(page-1)*limit);
		map.put("limit", limit);
		map.put("search", search);
		List<Message> messages = getSqlSession().selectList("com.tledu.aaa.dao.IMessageDao.find",map);
		pager.setData(messages);
		return pager;
	}

	@Override
	public void update(Message message) {
		getSqlSession().getMapper(IMessageDao.class).update(message);
	}

	@Override
	public void add(Message message) {
		getSqlSession().getMapper(IMessageDao.class).add(message);
		
	}

	@Override
	public void delete(int id) {
		getSqlSession().getMapper(IMessageDao.class).delete(id);
		
	}

	@Override
	public void edit(Message message) {
		getSqlSession().getMapper(IMessageDao.class).edit(message);
	}

	@Override
	public List<MessageType> listMessageTypes() {
	
		return getSqlSession().getMapper(IMessageDao.class).listMessageTypes();
	}
	
	
}

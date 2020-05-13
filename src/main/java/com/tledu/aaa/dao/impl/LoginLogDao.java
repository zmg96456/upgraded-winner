package com.tledu.aaa.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tledu.aaa.dao.ILoginLogDao;
import com.tledu.aaa.model.LoginLog;
import com.tledu.aaa.model.User;
import com.tledu.aaa.util.Pager;
@Repository("loginLogDao")
public class LoginLogDao extends SqlSessionDaoSupport implements ILoginLogDao{

	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	@Override
	public User login(String name) {
		return getSqlSession().getMapper(ILoginLogDao.class).login(name);
	}
	@Override
	public int find_count(String search) {
	
		return getSqlSession().getMapper(ILoginLogDao.class).find_count(search);
	}
	@Override
	public Pager<LoginLog> find(String search, int page, int limit) {
		Pager<LoginLog> pager =new Pager<LoginLog>();
		// 查询总条数
		int count =  find_count(search);
		pager.setCount(count);
		// 准备参数
		Map<String, Object> map =new HashMap<String, Object>();
				// 起始值
				// limit 起始值  , 条数 , 并且 起始值 0开始
				// 起始值是 页数 -1 乘 每页的条数
		map.put("pageOffset",(page-1)*limit);
		map.put("limit", limit);
		map.put("search", search);
		List<LoginLog> users = getSqlSession().selectList("com.tledu.aaa.dao.ILoginLogDao.find",map);
		
		pager.setData(users);
		
		return pager;
	}

	@Override
	public void add(LoginLog loginLog) {
	getSqlSession().getMapper(ILoginLogDao.class).add(loginLog);
	}
	
}

package com.tledu.aaa.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tledu.aaa.dao.IDeptDao;
import com.tledu.aaa.model.Dept;
import com.tledu.aaa.util.Pager;
@Repository
public class DeptDao extends SqlSessionDaoSupport implements IDeptDao {
	
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	@Override
	public List<Dept> list() {
		return 	getSqlSession().getMapper(IDeptDao.class).list();
	}
	
	@Override
	public Pager<Dept> find(String search, int page, int limit) {
		int count = find_count(search);
		int pageOffset = (page-1)*limit;
		Map<String, Object> pars = new HashMap<String, Object>();
		pars.put("search", search);
		pars.put("pageOffset", pageOffset);
		pars.put("limit", limit);
		List<Dept> depts = getSqlSession().selectList("com.tledu.aaa.dao.IDeptDao.find", pars);
		Pager<Dept> pager = new Pager<Dept>();
		pager.setData(depts);
		pager.setCount(count);
		return pager;
	}
	@Override
	public int find_count(String search) {
		
		return  getSqlSession().getMapper(IDeptDao.class).find_count(search);
	}
	@Override
	public void add(Dept dept) {
		getSqlSession().getMapper(IDeptDao.class).add(dept);
		
	}
	@Override
	public void delete(int id) {
		getSqlSession().getMapper(IDeptDao.class).delete(id);
		
	}
	@Override
	public void update(Dept dept) {
	getSqlSession().getMapper(IDeptDao.class).update(dept);
		
	}
	@Override
	public Dept load(int id) {
		
		return getSqlSession().getMapper(IDeptDao.class).load(id);
	}
	@Override
	public void edit(Dept dept) {
	getSqlSession().getMapper(IDeptDao.class).edit(dept);
		
	}
	@Override
	public List<Dept> listByUserId(int userId) {
		
		return getSqlSession().getMapper(IDeptDao.class).listByUserId(userId);
	}

}

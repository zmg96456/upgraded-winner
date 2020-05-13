package com.tledu.aaa.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tledu.aaa.dao.IOperationLogDao;
import com.tledu.aaa.model.OperationLog;
import com.tledu.aaa.util.Pager;
@Repository("operationLogDao")
public class OperationLogDao extends SqlSessionDaoSupport implements IOperationLogDao{
	
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	@Override
	public void add(OperationLog operationLog) {
		getSqlSession().getMapper(IOperationLogDao.class).add(operationLog);
	}

	@Override
	public int find_count(String search) {
	
		return getSqlSession().getMapper(IOperationLogDao.class).find_count(search);
	}

	@Override
	public Pager<OperationLog> find(String search, int page, int limit) {
		Pager<OperationLog> pager = new Pager<OperationLog>();
		// 查询总条数
		int count = find_count(search);
		pager.setCount(count);

		// 准备参数
		Map<String, Object> map = new HashMap<String, Object>();
		// 起始值
		// limit 起始值 , 条数 , 并且 起始值 0开始
		// 起始值是 页数 -1 乘 每页的条数
		map.put("pageOffset", (page - 1) * limit);
		map.put("limit", limit);
		map.put("search", search);
		// 查询
		List<OperationLog> users = getSqlSession().selectList(
				"com.tledu.aaa.dao.IOperationLogDao.find", map);
		pager.setData(users);
		return pager;
	
	}

}

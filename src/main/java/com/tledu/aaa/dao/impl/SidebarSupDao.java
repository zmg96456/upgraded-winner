package com.tledu.aaa.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tledu.aaa.dao.ISidebarSupDao;
import com.tledu.aaa.model.SidebarSup;
@Repository("sidebarSupDao")
public class SidebarSupDao extends SqlSessionDaoSupport implements ISidebarSupDao {

		@Autowired
		public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
			super.setSqlSessionFactory(sqlSessionFactory);
		}
		
	@Override
	public List<SidebarSup> list(int isAdmin) {

		Map<String, Object> par = new HashMap<String, Object>();
		par.put("isAdmin", isAdmin);
		return getSqlSession().selectList("com.tledu.aaa.dao.ISidebarSupDao.list",par);
	}

}

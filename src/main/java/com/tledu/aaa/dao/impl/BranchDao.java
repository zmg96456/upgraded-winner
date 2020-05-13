package com.tledu.aaa.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tledu.aaa.dao.IBranchDao;
import com.tledu.aaa.model.Branch;
import com.tledu.aaa.util.Pager;
@Repository
public class BranchDao extends SqlSessionDaoSupport implements IBranchDao {
		@Autowired
		public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
			super.setSqlSessionFactory(sqlSessionFactory);
		}
	
	@Override
	public Pager<Branch> find(String search, int page, int limit) {
		int count = find_count(search);
		int pageOffset =(page-1)*limit;
		Map<String, Object> pars = new HashMap<String, Object>();
		pars.put("search", search);
		pars.put("pageOffset", pageOffset);
		pars.put("limit", limit);
		List<Branch> branchs = getSqlSession().selectList("com.tledu.aaa.dao.IBranchDao.find", pars);
		Pager<Branch> pager = new Pager<Branch>();
		pager.setCount(count);
		pager.setData(branchs);
		return pager;
	}

	@Override
	public int find_count(String search) {
		
		return getSqlSession().getMapper(IBranchDao.class).find_count(search);
	}

	@Override
	public List<Branch> list() {
		
		return getSqlSession().getMapper(IBranchDao.class).list();
	}

	@Override
	public void add(Branch branch) {
	getSqlSession().getMapper(IBranchDao.class).add(branch);
		
	}

	@Override
	public void delete(int id) {
		getSqlSession().getMapper(IBranchDao.class).delete(id);
		
	}

	@Override
	public void update(Branch branch) {
		getSqlSession().getMapper(IBranchDao.class).update(branch);
		
	}

	@Override
	public Branch load(int id) {
		return getSqlSession().getMapper(IBranchDao.class).load(id);
	}

}

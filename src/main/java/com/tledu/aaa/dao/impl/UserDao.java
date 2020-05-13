package com.tledu.aaa.dao.impl;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;




import com.tledu.aaa.dao.IUserDao;
import com.tledu.aaa.model.User;
import com.tledu.aaa.util.Pager;
@Repository("userDao")
public class UserDao extends SqlSessionDaoSupport implements IUserDao{
	
	@Resource
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	@Override
	public int find_count(String search) {
		
		return getSqlSession().getMapper(IUserDao.class).find_count(search);
	}

	@Override
	public Pager<User> find(String search, int page, int limit) {
		Pager<User> pager =new Pager<User>();
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
		List<User> users = getSqlSession().selectList("com.tledu.aaa.dao.IUserDao.find",map);
		pager.setData(users);
		return pager;
	}

	@Override
	public void updateRole(User user) {
			getSqlSession().getMapper(IUserDao.class).updateRole(user);
	}

	@Override
	public User load(int id) {
		
		return getSqlSession().getMapper(IUserDao.class).load(id);
	}

	@Override
	public void updateUser(User user) {
	getSqlSession().getMapper(IUserDao.class).updateUser(user);
		
	}

	@Override
	public List<User> list() {
		
		return getSqlSession().getMapper(IUserDao.class).list();
	}

	@Override
	public void update(User user) {
		getSqlSession().getMapper(IUserDao.class).update(user);
		
	}

	@Override
	public void add(User user) {
		getSqlSession().getMapper(IUserDao.class).add(user);
		
	}

	@Override
	public void delete(int id) {
		getSqlSession().getMapper(IUserDao.class).delete(id);
		
	}

	@Override
	public List<User> listByDeptId(int deptId) {
	
		return getSqlSession().getMapper(IUserDao.class).listByDeptId(deptId);
	}

	@Override
	public void edit(User user) {
		getSqlSession().getMapper(IUserDao.class).edit(user);
		
	}
	
	
}

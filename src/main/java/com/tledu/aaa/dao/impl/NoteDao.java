package com.tledu.aaa.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tledu.aaa.dao.INoteDao;
import com.tledu.aaa.model.Note;
import com.tledu.aaa.util.Pager;

@Repository("noteDao")
public class NoteDao extends SqlSessionDaoSupport implements INoteDao {
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	@Override
	public Note load(int id) {
		return getSqlSession().getMapper(INoteDao.class).load(id);
	}

	@Override
	public List<Note> list() {
		return getSqlSession().getMapper(INoteDao.class).list();
	}

	@Override
	public int find_count(String search) {
		return getSqlSession().getMapper(INoteDao.class).find_count(search);
	}

	@Override
	public Pager<Note> find(String search, int page, int limit) {
		Pager<Note> pager = new Pager<Note>();
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
		List<Note> mList = getSqlSession().selectList(
				"com.tledu.aaa.dao.INoteDao.find", map);
		pager.setData(mList);
		return pager;
	}

	@Override
	public void update(Note note) {
		getSqlSession().getMapper(INoteDao.class).update(note);
	}

	@Override
	public void add(Note note) {
		getSqlSession().getMapper(INoteDao.class).add(note);
	}

	@Override
	public void delete(int id) {
		getSqlSession().getMapper(INoteDao.class).delete(id);
	}

	@Override
	public void edit(Note note) {
		getSqlSession().getMapper(INoteDao.class).edit(note);

	}

}

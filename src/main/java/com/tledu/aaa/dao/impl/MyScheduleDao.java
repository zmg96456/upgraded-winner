package com.tledu.aaa.dao.impl;



import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;








import com.tledu.aaa.dao.IMyScheduleDao;
import com.tledu.aaa.model.Meeting;
import com.tledu.aaa.model.MySchedule;
import com.tledu.aaa.util.Pager;
@Repository("myScheduleDao")
public class MyScheduleDao extends SqlSessionDaoSupport implements IMyScheduleDao{
	
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	@Override
	public int find_count(String search) {
		
		return getSqlSession().getMapper(IMyScheduleDao.class).find_count(search);
	}

	@Override
	public Pager<MySchedule> find(String search, int page, int limit) {
		Pager<MySchedule> pager =new Pager<MySchedule>();
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
		List<MySchedule> mList = getSqlSession().selectList("com.tledu.aaa.dao.IMyScheduleDao.find",map);
		pager.setData(mList);
		return pager;
	}



	@Override
	public MySchedule load(int id) {
		
		return getSqlSession().getMapper(IMyScheduleDao.class).load(id);
	}

	@Override
	public void update(MySchedule mySchedule) {
		getSqlSession().getMapper(IMyScheduleDao.class).update(mySchedule);
		
	}

	@Override
	public void add(MySchedule mySchedule) {
		getSqlSession().getMapper(IMyScheduleDao.class).add(mySchedule);
		
	}

	@Override
	public List<Meeting> listMeeting() {
		
		return getSqlSession().getMapper(IMyScheduleDao.class).listMeeting();
	}

	@Override
	public List<MySchedule> list() {
		
		return getSqlSession().getMapper(IMyScheduleDao.class).list();
	}

	@Override
	public void delete(int id) {
		getSqlSession().getMapper(IMyScheduleDao.class).delete(id);
		
	}

	

	@Override
	public void edit(MySchedule mySchedule) {
		getSqlSession().getMapper(IMyScheduleDao.class).edit(mySchedule);
		
	}

	
	
	
}

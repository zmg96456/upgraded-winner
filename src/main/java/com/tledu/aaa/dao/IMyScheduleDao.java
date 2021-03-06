package com.tledu.aaa.dao;



import java.util.List;

import com.tledu.aaa.model.Meeting;
import com.tledu.aaa.model.MySchedule;
import com.tledu.aaa.util.Pager;

public interface IMyScheduleDao {
	public MySchedule load(int id);
	
	 public List<MySchedule> list();
	public int find_count(String search);

	public Pager<MySchedule> find(String search, int page, int limit);
	
	//创建时间和创建者 不可以更改
	public void update(MySchedule mySchedule);
	
	public void add(MySchedule mySchedule);
	
	public void delete(int id);
	
	public void edit(MySchedule mySchedule);
	//查询所有的会议类型,添加和更新 会用到
	public List<Meeting> listMeeting();
}

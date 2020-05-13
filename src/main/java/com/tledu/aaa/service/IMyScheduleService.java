package com.tledu.aaa.service;

import java.util.List;

import com.tledu.aaa.model.Meeting;
import com.tledu.aaa.model.MySchedule;
import com.tledu.aaa.util.Pager;

public interface IMyScheduleService {
	public MySchedule load(int id);

	public Pager<MySchedule> find(String search, int page, int limit);

	public List<MySchedule> list();

	public void add(MySchedule mySchedule);

	public void delete(int id);

	// 创建时间和创建者 不可以更改
	public void update(MySchedule mySchedule);

	public void edit(MySchedule mySchedule);

	public List<Meeting> listMeeting();
}

package com.tledu.aaa.service.imp;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tledu.aaa.dao.IMyScheduleDao;
import com.tledu.aaa.model.Meeting;
import com.tledu.aaa.model.MySchedule;
import com.tledu.aaa.service.IMyScheduleService;
import com.tledu.aaa.util.Pager;

@Service("myScheduleService")
public class MyScheduleService implements IMyScheduleService {
	@Autowired
private IMyScheduleDao myScheduleDao;
	public IMyScheduleDao getMyScheduleDao() {
	return myScheduleDao;
}
public void setMyScheduleDao(IMyScheduleDao myScheduleDao) {
	this.myScheduleDao = myScheduleDao;
}

	@Override
	public MySchedule load(int id) {
		return myScheduleDao.load(id);
	}

	@Override
	public Pager<MySchedule> find(String search, int page, int limit) {
		try {
			if (search == null || search.trim().equals("")) {
				search = "";
			} else {
				// GET请求,汉字会乱码,需要转码
			search = search.trim();
			search = new String(search.getBytes("ISO-8859-1"), "UTF-8");
		} 
			search = "%" +search +"%";
			return myScheduleDao.find(search, page, limit);
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;		
	}
	

	@Override
	public List<MySchedule> list() {
		
		return myScheduleDao.list();
	}

	@Override
	public void add(MySchedule mySchedule) {
		myScheduleDao.add(mySchedule);
	}

	@Override
	public void delete(int id) {
	myScheduleDao.delete(id);
		
	}

	@Override
	public void update(MySchedule mySchedule) {
		myScheduleDao.update(mySchedule);
	}

	@Override
	public void edit(MySchedule mySchedule) {
		myScheduleDao.edit(mySchedule);
	}

	@Override
	public List<Meeting> listMeeting() {
		
		return myScheduleDao.listMeeting();
	}

}

package com.tledu.aaa.service.imp;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tledu.aaa.dao.IDeptDao;
import com.tledu.aaa.dao.IUserDao;
import com.tledu.aaa.model.Dept;
import com.tledu.aaa.model.User;
import com.tledu.aaa.service.IDeptService;
import com.tledu.aaa.util.OAException;
import com.tledu.aaa.util.Pager;
@Service
public class DeptService implements IDeptService {
	private IDeptDao deptDao;
	private IUserDao userDao;
	public IUserDao getUserDao() {
		return userDao;
	}
	@Autowired
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	public IDeptDao getDeptDao() {
		return deptDao;
	}
	@Autowired
	public void setDeptDao(IDeptDao deptDao) {
		this.deptDao = deptDao;
	}

	@Override
	public Pager<Dept> find(String search, int page, int limit) {
		
		try {			
			if (search ==null || search.trim().equals("")) {
				search="";
			}else {
			search = new String(search.getBytes("ISO-8859-1"), "UTF-8");				
			}
			search = "%"+search+"%";
			return deptDao.find(search, page, limit);
			}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<Dept> list() {
	
		return deptDao.list();
	}
	@Override
	public void add(Dept dept) {
		deptDao.add(dept);
		
	}
	@Override
	public void delete(int id) throws OAException{
		List<User> users = userDao.listByDeptId(id);
		if (users!=null&&users.size()>0) {
			throw new OAException("该部门下有员工,不能删除");
		}
	deptDao.delete(id);
		
	}
	@Override
	public void update(Dept dept) {
		deptDao.update(dept);
		
	}
	@Override
	public Dept load(int id) {
		
		return deptDao.load(id);
	}
	@Override
	public void edit(Dept dept) {
		deptDao.edit(dept);
		
	}

}

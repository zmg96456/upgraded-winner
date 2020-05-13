package com.tledu.aaa.service.imp;



import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tledu.aaa.dao.IDeptDao;
import com.tledu.aaa.dao.IUserDao;
import com.tledu.aaa.model.Dept;
import com.tledu.aaa.model.Role;
import com.tledu.aaa.model.User;
import com.tledu.aaa.service.IUserService;
import com.tledu.aaa.util.AjaxObj;
import com.tledu.aaa.util.OAException;
import com.tledu.aaa.util.Pager;
@Service("userService")
public class UserService implements IUserService {
	@Autowired
	private IUserDao userDao;
	private IDeptDao deptDao;
	
	public IDeptDao getDeptDao() {
		return deptDao;
	}
	@Autowired
	public void setDeptDao(IDeptDao deptDao) {
		this.deptDao = deptDao;
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public Pager<User> find(String search, int page, int limit) {
		
			try {
				if (search == null || search.trim().equals("")) {
					search = "";
				} else {
					// GET请求,汉字会乱码,需要转码
				search = search.trim();
				search = new String(search.getBytes("ISO-8859-1"), "UTF-8");
			} 
				search = "%" +search +"%";
				return userDao.find(search, page, limit);
			}catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return null;		
		}


	@Override
	public AjaxObj updateRole(int id, int role_id) {
		User user = userDao.load(id);
		int oldRole_id=1;
		if (user.getRole().getId()==1) {
			oldRole_id = 2;
		}
		if (role_id != oldRole_id) {
			return new AjaxObj(0, "失败,传递数据不合法");
		}
		Role role =new Role();
		role.setId(oldRole_id);
		user.setRole(role);
		userDao.updateRole(user);
		return new AjaxObj(1);
	}

	@Override
	public AjaxObj updateUser(int id, int status) {
		User user= userDao.load(id);
		int oldStatus = 1;
		if (user.getStatus()==1) {
			oldStatus = 2;
		}
		if (status!=oldStatus) {
			return new AjaxObj(0, "失败,传递数据不合法");
		}
		user.setStatus(oldStatus);
		userDao.updateUser(user);
		return new AjaxObj(1);
	}

	@Override
	public List<User> list() {
		
		return userDao.list();
	}

	@Override
	public User load(int id) {
		return userDao.load(id);
	}

	@Override
	public void add(User user) {
		userDao.add(user);
	}

	@Override
	public void delete(int id) throws OAException {
		List<Dept> depts = deptDao.listByUserId(id);
		if (depts != null && depts.size() >0 ) {
			throw new OAException("该员工还是部门负责人,不能删除");
		}
		userDao.delete(id);
	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public void edit(User user) {
		userDao.edit(user);
	}


}

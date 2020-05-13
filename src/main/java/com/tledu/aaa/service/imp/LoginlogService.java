package com.tledu.aaa.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tledu.aaa.dao.ILoginLogDao;
import com.tledu.aaa.model.LoginLog;
import com.tledu.aaa.model.User;
import com.tledu.aaa.service.ILoginLogService;
import com.tledu.aaa.util.OAException;
import com.tledu.aaa.util.Pager;

@Service
public class LoginlogService implements ILoginLogService {
	private ILoginLogDao loginLogDao;

	public ILoginLogDao getLoginLogDao() {
		return loginLogDao;
	}
	@Autowired
	public void setLoginLogDao(ILoginLogDao loginLogDao) {
		this.loginLogDao = loginLogDao;
	}
	@Override
	public User login(User user) throws OAException {
		User oldUser = loginLogDao.login(user.getName());
		if (oldUser == null) {
			throw new OAException("用户名不存在");
		}
		if (oldUser.getStatus() == 2) {
			throw new OAException("您的账户已被禁用,请于管理员联系!");
		}
		if (!oldUser.getPassword().equals(user.getPassword().trim())) {
			throw new OAException("密码不正确");
		}
		return oldUser;
	
	}
	@Override
	public Pager<LoginLog> find(String search, int page, int limit) {
		search = "%" +search +"%";
		return loginLogDao.find(search, page, limit);
	}
	@Override
	public void add(LoginLog loginLog) {
	loginLogDao.add(loginLog);
		
	}
	
}

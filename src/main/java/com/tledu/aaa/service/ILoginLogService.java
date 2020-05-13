package com.tledu.aaa.service;

import com.tledu.aaa.model.LoginLog;
import com.tledu.aaa.model.User;
import com.tledu.aaa.util.OAException;
import com.tledu.aaa.util.Pager;

public interface ILoginLogService {

	public User login(User user)  throws OAException;
	
	public Pager<LoginLog> find(String search, int page, int limit);
	
	public void add(LoginLog loginLog);
}

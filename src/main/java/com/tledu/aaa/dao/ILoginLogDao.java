package com.tledu.aaa.dao;

import com.tledu.aaa.model.LoginLog;
import com.tledu.aaa.model.User;
import com.tledu.aaa.util.Pager;

public interface ILoginLogDao {
	public User login(String name);
	
	public int find_count(String search);

	public Pager<LoginLog> find(String search, int page, int limit);
	
	public void add(LoginLog loginLog);
}

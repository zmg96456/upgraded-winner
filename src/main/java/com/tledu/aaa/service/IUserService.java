package com.tledu.aaa.service;



import java.util.List;

import com.tledu.aaa.model.User;
import com.tledu.aaa.util.AjaxObj;
import com.tledu.aaa.util.OAException;
import com.tledu.aaa.util.Pager;


public interface IUserService {
	public Pager<User> find(String search, int page, int limit);
	
	public AjaxObj updateRole(int id,int role_id);
	
	public AjaxObj updateUser(int id,int status);
	
	public List<User> list();
	
	public User load(int id);
	
	public void add(User user);
	//用户还是部门负责人的时候 不能删除
	public void delete(int id) throws  OAException;
	
	public void update(User user);
	
	public void edit(User user);
	
	
	
}

package com.tledu.aaa.dao;



import java.util.List;

import com.tledu.aaa.model.User;
import com.tledu.aaa.util.Pager;

public interface IUserDao {
	public User load(int id);
	
	 public List<User> list();
	public int find_count(String search);

	public Pager<User> find(String search, int page, int limit);
	
	public void	updateRole(User user);
	
	public void	updateUser(User user);
	
	public void update(User user);
	
	public void add(User user);
	//删除员工时,需要判断,如果该员工是某部门负责人,就不能删除
	public void delete(int id);
	//根据部门ID 查询部门下所有员工
	public List<User> listByDeptId(int deptId);
	
	public void edit(User user);
}

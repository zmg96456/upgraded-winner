package com.tledu.aaa.dao;

import java.util.List;

import com.tledu.aaa.model.Dept;
import com.tledu.aaa.util.OAException;
import com.tledu.aaa.util.Pager;

public interface IDeptDao {
		public List<Dept> list();
		
		public int find_count(String search);
		
		public Pager<Dept> find(String search,int page, int limit);
		
		public void add(Dept dept);
		// 部门下有员工,不能删除该部门
		public void delete(int id) throws OAException;
		
		public void update(Dept dept);
		
		public Dept load(int id);
		
		public void edit(Dept dept);
		//根据员工ID查询负责的部门
		public List<Dept> listByUserId(int userId);
}

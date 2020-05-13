package com.tledu.aaa.service;

import java.util.List;

import com.tledu.aaa.model.Dept;
import com.tledu.aaa.util.Pager;


public interface IDeptService {
	public List<Dept> list();
	
	public Pager<Dept> find(String search, int page,int limit);
	
	public void add(Dept dept);
	
	public void delete(int id);
	
	public void update(Dept dept);
	
	public Dept load(int id);
	
	public void edit(Dept dept);
}

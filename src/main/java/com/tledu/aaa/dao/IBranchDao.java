package com.tledu.aaa.dao;

import java.util.List;

import com.tledu.aaa.model.Branch;
import com.tledu.aaa.util.Pager;

public interface IBranchDao {
	public Pager<Branch> find(String search,int page, int limit);
	
	public Branch load(int id);
	
	public int find_count(String search);
	
	public List<Branch> list();
	
	public void add(Branch branch);
	
	public void delete(int id);
	
	public void update(Branch branch);
}

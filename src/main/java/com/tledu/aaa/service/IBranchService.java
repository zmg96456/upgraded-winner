package com.tledu.aaa.service;

import java.util.List;

import com.tledu.aaa.model.Branch;
import com.tledu.aaa.util.Pager;


public interface IBranchService {
	public Branch load(int id);
	
	public Pager<Branch> find(String search,int page, int limit);
	
	public List<Branch> list();
	
	public void add(Branch branch);
	
	public void delete(int id);
	
	public void update(Branch branch);
}

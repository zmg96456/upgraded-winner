package com.tledu.aaa.service.imp;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tledu.aaa.dao.IBranchDao;
import com.tledu.aaa.model.Branch;
import com.tledu.aaa.service.IBranchService;
import com.tledu.aaa.util.Pager;
@Service
public class BranchService implements IBranchService{
 private IBranchDao branchDao;
 
	public IBranchDao getBranchDao() {
	return branchDao;
}
@Autowired
public void setBranchDao(IBranchDao branchDao) {
	this.branchDao = branchDao;
}
@Override
public Pager<Branch> find(String search, int page, int limit) {

		try {
			if (search==null||search.trim().equals("")) {
				search="";
			}else {
			search = new String(search.getBytes("ISO-8859-1"),"UTF-8");	
		}
			search = "%"+search+"%";
			return branchDao.find(search, page, limit);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	
	return null;
}
@Override
public List<Branch> list() {
	
	return branchDao.list();
}
@Override
public void add(Branch branch) {
branchDao.add(branch);
	
}
@Override
public void delete(int id) {
branchDao.delete(id);
	
}
@Override
public void update(Branch branch) {
branchDao.update(branch);
	
}
@Override
public Branch load(int id) {
	
	return branchDao.load(id);
}

}

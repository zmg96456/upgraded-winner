package com.tledu.aaa.dao;

import com.tledu.aaa.model.OperationLog;
import com.tledu.aaa.util.Pager;

public interface IOperationLogDao {
	public void add(OperationLog operationLog);
	public int find_count(String search);
	public Pager<OperationLog> find(String search, int page, int limit);
}

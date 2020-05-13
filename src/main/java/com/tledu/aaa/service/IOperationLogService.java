package com.tledu.aaa.service;

import com.tledu.aaa.model.OperationLog;
import com.tledu.aaa.util.Pager;

public interface IOperationLogService {

	public void add(OperationLog operationLog);

	public Pager<OperationLog> find(String search, int page, int limit);

}

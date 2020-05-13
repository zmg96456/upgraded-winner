package com.tledu.aaa.service.imp;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tledu.aaa.dao.IOperationLogDao;
import com.tledu.aaa.model.OperationLog;
import com.tledu.aaa.service.IOperationLogService;
import com.tledu.aaa.util.Pager;
@Service
public class OperationLogService implements IOperationLogService {
	private IOperationLogDao operationLogDao;
	public IOperationLogDao getOperationLogDao() {
		return operationLogDao;
	}
	@Autowired
	public void setOperationLogDao(IOperationLogDao operationLogDao) {
		this.operationLogDao = operationLogDao;
	}

	@Override
	public void add(OperationLog operationLog) {
		operationLogDao.add(operationLog);
		
	}

	@Override
	public Pager<OperationLog> find(String search, int page, int limit) {
		search = "%" + search + "%";
		return operationLogDao.find(search, page, limit);
	}
	
}

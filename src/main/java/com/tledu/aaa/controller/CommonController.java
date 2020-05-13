package com.tledu.aaa.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tledu.aaa.model.OperationLog;
import com.tledu.aaa.model.User;
import com.tledu.aaa.service.IOperationLogService;

/**
 * 公共模块 记录日志用
 */
@Controller
public class CommonController {
	private IOperationLogService operationLogService;
	private HttpServletRequest request;
	public IOperationLogService getOperationLogService() {
		return operationLogService;
	}
	@Autowired
	public void setOperationLogService(IOperationLogService operationLogService) {
		this.operationLogService = operationLogService;
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	@Autowired
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public void log(String desc){
		OperationLog operationLog =new OperationLog();
		operationLog.setCome_time(new Date());
		operationLog.setDesc(desc);
		User user = (User) request.getSession().getAttribute("loginUser");
		operationLog.setUser(user);
		operationLogService.add(operationLog);
	}
}

package com.tledu.aaa.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tledu.aaa.auth.Admin;
import com.tledu.aaa.auth.RoleType;
import com.tledu.aaa.model.OperationLog;
import com.tledu.aaa.service.IOperationLogService;
import com.tledu.aaa.util.Pager;

@Controller
@Admin(RoleType.Admin)
@RequestMapping("/operationLog")
public class OperationLogController extends CommonController{
private IOperationLogService operationLogService;

	public IOperationLogService getOperationLogService() {
	return operationLogService;
}
@Autowired
public void setOperationLogService(IOperationLogService operationLogService) {
	super.setOperationLogService(operationLogService);
	this.operationLogService = operationLogService;
}

	@RequestMapping("/list")
	@Admin(RoleType.Login)
	public String list() {
		return "operationLog/list";
	}

	/**
	 * 分页会自动向url发送两条数据 page 第几页 limit 每页显示多少条
	 * 
	 * 但是还要模糊查询,所以 需要提供一个额外参数
	 * 
	 * 单独创建一个Pager类 用于分页操作
	*/
	@RequestMapping("/pager")
	@ResponseBody
	@Admin(RoleType.Login)
	public Pager<OperationLog> pager(int page, int limit, String search)
			throws UnsupportedEncodingException {
		// 如果不需要模糊查询,设置为空字符串,这样不会影响SQL中的like模糊查询
		if (search == null || search.trim().equals("")) {
			search = "";
		} else {
			// GET请求,汉字会乱码,需要转码
			search = search.trim();
			search = new String(search.getBytes("ISO-8859-1"), "UTF-8");
			log("搜索操作 : { "+search+" }");
		}
		return operationLogService.find(search, page, limit);
	}
	
	
}

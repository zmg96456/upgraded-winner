package com.tledu.aaa.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tledu.aaa.auth.Admin;
import com.tledu.aaa.auth.RoleType;
import com.tledu.aaa.model.User;
import com.tledu.aaa.service.IUserService;
import com.tledu.aaa.util.AjaxObj;
import com.tledu.aaa.util.Pager;

@Controller
@Admin(RoleType.Admin)
@RequestMapping("/role")
public class RoleController extends CommonController {

	private IUserService userService;

	public IUserService getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/list")
	@Admin(RoleType.Login)
	public String list() {
		return "role/list";
	}

	/**
	 * 分页会自动向url发送两条数据 page 第几页 limit 每页显示多少条
	 * 
	 * 但是还要模糊查询,所以 需要提供一个额外参数
	 * 
	 * 单独创建一个Pager类 用于分页操作
	*/
	@Admin(RoleType.Login)
	@RequestMapping("/pager")
	@ResponseBody
	public Pager<User> pager(int page, int limit, String search) {	
		if (search!=null&&!search.trim().equals("")) {
			log("搜索员工角色 : { "+search+" }");
		}
		return userService.find(search, page, limit);
	}
	@RequestMapping(value="/updateRole",method=RequestMethod.POST)
	@ResponseBody
	public AjaxObj updateRole(int id , int role_id){
		log("更改员工角色 : { ID="+id+" , role_id="+role_id+" }");
		return userService.updateRole(id, role_id);
	}
}

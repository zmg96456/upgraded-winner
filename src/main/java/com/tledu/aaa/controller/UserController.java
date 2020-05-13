package com.tledu.aaa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.tledu.aaa.auth.Admin;
import com.tledu.aaa.auth.RoleType;
import com.tledu.aaa.dto.UserDto;
import com.tledu.aaa.model.User;
import com.tledu.aaa.service.IDeptService;
import com.tledu.aaa.service.IRoleService;
import com.tledu.aaa.service.IUserService;
import com.tledu.aaa.util.AjaxObj;
import com.tledu.aaa.util.Pager;
@Admin(RoleType.Admin)
@Controller
@RequestMapping("/user")
public class UserController extends IsRoleController{
	private IUserService userService;
	private IRoleService roleService;
	private IDeptService deptService;
	public IRoleService getRoleService() {
		return roleService;
	}
	@Autowired
	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}
	public IDeptService getDeptService() {
		return deptService;
	}
	@Autowired
	public void setDeptService(IDeptService deptService) {
		this.deptService = deptService;
	}
	public IUserService getUserService() {
		return userService;
	}
	@Autowired
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	@RequestMapping("/list")
	@Admin(RoleType.Login)
	public String list(){
		return "user/list";
	}
	@RequestMapping("pager")
	@ResponseBody
	@Admin(RoleType.Login)
	public Pager<User> pager(String search,int page,int limit){
		if (search!=null&&!search.trim().equals("")) {
			log("搜索员工角色 : { "+search+" }");
		}
		return userService.find(search, page, limit);
	}
	
	@RequestMapping(value="/updateUser",method=RequestMethod.POST)
	@ResponseBody
	@Admin(RoleType.Admin)
	public AjaxObj updateRole(int id , int status){
		log("更改员工状态 : { ID="+id+" , status="+status+" }");
		return userService.updateUser(id, status);
	}
	
	@RequestMapping("/add")
	public String add(Model model){
		model.addAttribute("roles", roleService.list());
		model.addAttribute("depts", deptService.list());
		return "user/add";
		
	}
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public AjaxObj add(User user){
		try {
			userService.add(user);
			log("添加员工 : "+ JSON.toJSONString(user,SerializerFeature.WriteMapNullValue));
			return new AjaxObj(1, "添加成功");
		} catch (Exception e) {
			return new AjaxObj(0, "添加失败");
		}
	}
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public AjaxObj delete(int id){
		try {
			userService.delete(id);
			log("删除员工 : { ID : " + id + "}");
			return new AjaxObj(1, "删除成功");
		} catch (Exception e) {
			return new AjaxObj(0,e.getMessage());
		}
	}
	@RequestMapping(value="/deleteAll",method=RequestMethod.POST)
	@ResponseBody
	public AjaxObj deleteAll(@RequestParam("ids[]") int[] ids){
		try {
			String idsString="";
			for (int id : ids) {
				userService.delete(id);
				idsString+="," + id;
			}
			log("删除员工 : { Ids : " + idsString.substring(1) + "}");
			return new AjaxObj(1, "删除成功");
		} catch (Exception e) {
			return new AjaxObj(0, e.getMessage());
		}
	}
	@RequestMapping("/update")
	public String update(Model model,int id){
		User user = userService.load(id);
		model.addAttribute("user", user);
		model.addAttribute("depts", deptService.list());
		return "user/update";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public AjaxObj update(User user){
		try {
			userService.update(user);
			log("更新员工 : "+ JSON.toJSONString(user,SerializerFeature.WriteMapNullValue));
			return new AjaxObj(1, "更新成功");
		} catch (Exception e) {
			return new AjaxObj(0, "更新失败");
		}
	}
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public AjaxObj edit(UserDto userDto){
		try {
			User user =userDto.getUser();
			userService.edit(user);
			log("edit更新员工 : "+ JSON.toJSONString(user,SerializerFeature.WriteMapNullValue));
			return new AjaxObj(1, "更新成功");
		} catch (Exception e) {
			return new AjaxObj(0, "更新失败");
		}
	}
}

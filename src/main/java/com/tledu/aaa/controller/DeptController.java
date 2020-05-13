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
import com.tledu.aaa.dto.DeptDto;
import com.tledu.aaa.model.Dept;
import com.tledu.aaa.service.IBranchService;
import com.tledu.aaa.service.IDeptService;
import com.tledu.aaa.service.IUserService;
import com.tledu.aaa.util.AjaxObj;
import com.tledu.aaa.util.Pager;
@Controller
@Admin(RoleType.Admin)
@RequestMapping("/dept")
public class DeptController extends IsRoleController{
	private IDeptService deptService;
	private IUserService userService;
	private IBranchService branchService;
	public IUserService getUserService() {
		return userService;
	}
	@Autowired
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	public IBranchService getBranchService() {
		return branchService;
	}
	@Autowired
	public void setBranchService(IBranchService branchService) {
		this.branchService = branchService;
	}
	public IDeptService getDeptService() {
		return deptService;
	}
	@Autowired
	public void setDeptService(IDeptService deptService) {
		this.deptService = deptService;
	}
	
	@RequestMapping("/list")
	@Admin(RoleType.Login)
	public String list(){
		return "dept/list";
	}
	@RequestMapping("/pager")
	@ResponseBody
	@Admin(RoleType.Login)
	public Pager<Dept> pager(String search,int page,int limit){
		if (search!=null&&!search.trim().equals("")) {
			log("搜索员工角色 : { "+search+" }");
		}
		return deptService.find(search, page, limit);
		
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxObj delete(int id){
		try {
			deptService.delete(id);
			log("删除部门 : { ID : " + id + "}");
			return new AjaxObj(1, "删除成功");
		} catch (Exception e) {
			return new AjaxObj(0, e.getMessage());
		}
	}
	@RequestMapping(value="/deleteAll",method=RequestMethod.POST)
	@ResponseBody
	public AjaxObj deleteAll(@RequestParam("ids[]") int[] ids){
		try {
			String idsString="";
			for (int id : ids) {
				deptService.delete(id);
				idsString +=","+id;
			}
			log("删除部门：{ids:"+idsString.substring(1)+"}");
			return new AjaxObj(1, "删除成功");
		} catch (Exception e) {
			return new AjaxObj(0, e.getMessage());
		}
	}
	@RequestMapping("/add")
	public String add(Model model){
		model.addAttribute("users", userService.list());
		model.addAttribute("branchs", branchService.list());
		return "/dept/add";
	}
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public AjaxObj add(Dept dept){
		try {
			if (dept.getUser() == null||dept.getUser().getId()==0) {
				dept.setUser(null);
			}
			if (dept.getBranch()==null||dept.getBranch().getId()==0) {
				dept.setBranch(null);
			}
			deptService.add(dept);
			log("添加部门："+JSON.toJSONString(dept, SerializerFeature.WriteMapNullValue));
			return new AjaxObj(1, "添加成功");
		} catch (Exception e) {
			return new AjaxObj(0, "添加失败");		
		}
	}
	
	@RequestMapping("/update")
	public String update(int id,Model model){
		Dept dept = deptService.load(id);
		model.addAttribute("dept", dept);
		model.addAttribute("users", userService.list());
		model.addAttribute("branchs", branchService.list());
		return "dept/update";
	}
	@RequestMapping(value="/update",method=RequestMethod.POST)
	@ResponseBody
	public AjaxObj update(Dept dept){
		try {
			if (dept.getUser() == null||dept.getUser().getId()==0) {
				dept.setUser(null);
			}
			if (dept.getBranch()==null||dept.getBranch().getId()==0) {
				dept.setBranch(null);
			}
			deptService.update(dept);
			log("更新部门:"+JSON.toJSONString(dept,SerializerFeature.WriteMapNullValue));
			return new AjaxObj(1, "更新成功");
		} catch (Exception e) {
			return new AjaxObj(0, "更新失败");
		}
	}
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public AjaxObj edit(DeptDto deptDto){
		try {
			Dept dept = deptDto.getDept();
			deptService.edit(dept);
			log("更新部门:"+JSON.toJSONString(dept,SerializerFeature.WriteMapNullValue));
			return new AjaxObj(1, "更新成功");
		} catch (Exception e) {
			return new AjaxObj(0, "更新失败");
		}
	}
}

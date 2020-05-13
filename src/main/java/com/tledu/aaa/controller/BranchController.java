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
import com.tledu.aaa.dto.BranchDto;
import com.tledu.aaa.model.Branch;
import com.tledu.aaa.service.IBranchService;
import com.tledu.aaa.util.AjaxObj;
import com.tledu.aaa.util.Pager;
@Controller
@Admin(RoleType.Admin)
@RequestMapping("/branch")
public class BranchController extends IsRoleController{
	 private IBranchService branchService;

	public IBranchService getBranchService() {
		return branchService;
	}
	@Autowired
	public void setBranchService(IBranchService branchService) {
		this.branchService = branchService;
	}
	@RequestMapping("/list")
	@Admin(RoleType.Login)
	public String  list() {
		return "branch/list";
	}
	@RequestMapping("pager")
	@ResponseBody
	@Admin(RoleType.Login)
	public Pager<Branch> pager(String search,int page,int limit){
		if (search!=null&&!search.trim().equals("")) {
			log("搜索员工角色 : { "+search+" }");
		}
		return branchService.find(search, page, limit);
	}
	@RequestMapping("/add")
	public String add(){
		return "branch/add";
	}
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public AjaxObj add(Branch branch){
		try {
			branchService.add(branch);
			// JSON.toJSONString : 把对象转换为JSON {id:1,name:xxx}
			// SerializerFeature.WriteMapNullValue 如果值为null 就不显示该字段
			log("添加机构 : "+ JSON.toJSONString(branch,SerializerFeature.WriteMapNullValue));
			return new AjaxObj(1, "添加成功");
		} catch (Exception e) {
			return new AjaxObj(0, "添加失败");
		}	
	}
	@RequestMapping(value= "/delete",method =RequestMethod.POST)
	@ResponseBody
	public AjaxObj delete(int id){
		try {
			branchService.delete(id);
			log("删除机构 : { ID : " + id + "}");
			return new AjaxObj(1, "删除成功");
		} catch (Exception e) {
			return new AjaxObj(0, "删除失败");
		}
		
	}
	// 如果是数组传递,会在name后面添加一个[]
	// ids[] 来进行映射
	@RequestMapping(value="/deleteAll",method=RequestMethod.POST)
	@ResponseBody
	public AjaxObj deleteAll(@RequestParam("ids[]") int[] ids) {
		try {
			String idsString="";
			for (int id : ids) {
				branchService.delete(id);
				idsString+=","+id;
			}
			log("删除机构 : { Ids : " + idsString.substring(1) + "}");
			return new AjaxObj(1,"删除成功");
		} catch (Exception e) {
			return new AjaxObj(0, "删除失败");
		}
	}
	@RequestMapping("/update")
	public String update(int id,Model model){
		Branch branch = branchService.load(id);
		model.addAttribute("branch", branch);
		return "branch/update";
		
	}
	@RequestMapping(value="/update",method =RequestMethod.POST)
	@ResponseBody
	public AjaxObj update(Branch branch){
		try {
			branchService.update(branch);
			log("更新机构:"+JSON.toJSONString(branch,SerializerFeature.WriteMapNullValue));
			return new AjaxObj(1, "更新成功");
		} catch (Exception e) {
			return new AjaxObj(0, "更新失败");
		}
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public  AjaxObj edit(BranchDto branchDto){
		try {
			Branch branch = branchDto.getBranch();
			branchService.update(branch);
			log("更新机构："+JSON.toJSONString(branch, SerializerFeature.WriteMapNullValue));
			return  new AjaxObj(1,"更新成功");
		} catch (Exception e) {
			return new AjaxObj(0, "更新失败");
		}
	}
}

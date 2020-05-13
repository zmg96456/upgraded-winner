package com.tledu.aaa.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.tledu.aaa.dto.MyScheduleDto;
import com.tledu.aaa.model.MySchedule;
import com.tledu.aaa.model.User;
import com.tledu.aaa.service.IMyScheduleService;
import com.tledu.aaa.util.AjaxObj;
import com.tledu.aaa.util.Pager;


@Controller
@RequestMapping("/mySchedule")
public class MyScheduleController extends CommonController {

	private IMyScheduleService myScheduleService;

	public IMyScheduleService getMyScheduleService() {
		return myScheduleService;
	}

	@Autowired
	public void setMyScheduleService(IMyScheduleService myScheduleService) {
		this.myScheduleService = myScheduleService;
	}

	@RequestMapping("/list")
	public String list() {
		return "mySchedule/list";
	}


	@RequestMapping("/pager")
	@ResponseBody
	public Pager<MySchedule> pager(String search,int page,int limit){
			if (search!=null&&!search.trim().equals("")) {
				log("搜索员工角色 : { "+search+" }");
			}
			return myScheduleService.find(search, page, limit);
		}

	@RequestMapping("/add")
	public String add(Model model, HttpSession session) {
		// 需要把登陆的用户和会议类型传递到页面
		model.addAttribute("meetings", myScheduleService.listMeeting());
		User user = (User) session.getAttribute("loginUser");
		model.addAttribute("user", user);
		return "mySchedule/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public AjaxObj add(MySchedule mySchedule) {

		try {
			// 设置创建时间
			mySchedule.setCreate_date(new Date());
			myScheduleService.add(mySchedule);
			
			log("添加日程: "+ JSON.toJSONString(mySchedule,
					SerializerFeature.WriteMapNullValue));
			return new AjaxObj(1, "添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxObj(0, "添加失败");
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public AjaxObj delete(int id) {
		try {
			myScheduleService.delete(id);
			log("删除日程 : { ID : " + id + "}");
			return new AjaxObj(1, "删除成功");
		} catch (Exception e) {
			return new AjaxObj(0, e.getMessage());
		}
	}

	@RequestMapping(value = "/deleteAll", method = RequestMethod.POST)
	@ResponseBody
	// 如果是数组传递,会在name后面添加一个[]
	// ids[] 来进行映射
	public AjaxObj delete(@RequestParam("ids[]") int[] ids) {
		try {
			String idsString = "";
			for (int id : ids) {
				myScheduleService.delete(id);
				idsString += "," + id;
			}
			log("删除日程 : { Ids : " + idsString.substring(1) + "}");
			return new AjaxObj(1, "删除成功");
		} catch (Exception e) {
			return new AjaxObj(0, e.getMessage());
		}
	}

	@RequestMapping("/update")
	public String update(int id, Model model) {
		MySchedule mySchedule = myScheduleService.load(id);
		model.addAttribute("mySchedule", mySchedule);
		model.addAttribute("meetings", myScheduleService.listMeeting());
		return "mySchedule/update";
	}

	@RequestMapping(value ="/update", method = RequestMethod.POST)
	@ResponseBody
	public AjaxObj update(MySchedule mySchedule) {

		try {
			myScheduleService.update(mySchedule);
			log("更新日程 : "+ JSON.toJSONString(mySchedule,
					SerializerFeature.WriteMapNullValue)	);
			return new AjaxObj(1, "更新成功");
		} catch (Exception e) {
			return new AjaxObj(0, "更新失败");
		}
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public AjaxObj edit(MyScheduleDto myScheduleDto) {

		try {

			MySchedule mySchedule = myScheduleDto.getMySchedule();
			myScheduleService.edit(mySchedule);
			log("edit更新日程 : "
					+ JSON.toJSONString(mySchedule,
							SerializerFeature.WriteMapNullValue));
			return new AjaxObj(1, "更新成功");
		} catch (Exception e) {
			return new AjaxObj(0, "更新失败");
		}
	}
}

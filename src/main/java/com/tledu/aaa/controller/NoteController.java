package com.tledu.aaa.controller;

import java.util.Date;

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
import com.tledu.aaa.dto.NoteDto;
import com.tledu.aaa.model.Note;
import com.tledu.aaa.service.INoteService;
import com.tledu.aaa.service.IUserService;
import com.tledu.aaa.util.AjaxObj;
import com.tledu.aaa.util.Pager;

@Controller
@RequestMapping("/note")
@Admin(RoleType.Login)
public class NoteController extends CommonController {
	private INoteService noteService;
	private IUserService userService;

	public IUserService getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public INoteService getNoteService() {
		return noteService;
	}

	@Autowired
	public void setNoteService(INoteService noteService) {
		this.noteService = noteService;
	}

	@RequestMapping("/list")
	public String list() {
		return "note/list";
	}

	@RequestMapping("/pager")
	@ResponseBody
	public Pager<Note> pager(String search, int page, int limit) {
		if (search != null && !search.trim().equals("")) {
			log("搜索便签 : { " + search + " }");
		}
		return noteService.find(search, page, limit);
	}

	@RequestMapping("/add")
	public String add(Model model) {
		model.addAttribute("user", userService.list());
		return "note/add";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public AjaxObj add(Note note) {
		try {
			note.setCreate_date(new Date());
			noteService.add(note);
			log("添加便签 : "
					+ JSON.toJSONString(note,
							SerializerFeature.WriteMapNullValue));
			return new AjaxObj(1, "添加成功");
		} catch (Exception e) {
			return new AjaxObj(0, "添加失败");
		}
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public AjaxObj delete(int id) {
		try {
			noteService.delete(id);
			log("删除便签 : { ID : " + id + "}");
			return new AjaxObj(1, "删除成功");
		} catch (Exception e) {
			return new AjaxObj(0, "删除失败");
		}
	}

	@RequestMapping(value = "/deleteAll", method = RequestMethod.POST)
	@ResponseBody
	public AjaxObj deleteAll(@RequestParam("ids[]") int[] ids) {
		try {
			String idsString = "";
			for (int id : ids) {
				noteService.delete(id);
				idsString += "," + id;
			}
			log("删除员工 : { Ids : " + idsString.substring(1) + "}");
			return new AjaxObj(1, "删除成功");
		} catch (Exception e) {
			return new AjaxObj(0, "删除失败");
		}
	}

	@RequestMapping("/update")
	public String update(Model model, int id) {
		Note note = noteService.load(id);
		model.addAttribute("user", userService.list());
		model.addAttribute("note", note);
		return "note/update";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public AjaxObj update(Note note) {
		try {
			noteService.update(note);
			log("更新便签 : "
					+ JSON.toJSONString(note,
							SerializerFeature.WriteMapNullValue));
			return new AjaxObj(1, "更新成功");
		} catch (Exception e) {
			return new AjaxObj(0, "更新失败");
		}
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public AjaxObj edit(NoteDto noteDto) {
		try {
			Note note = noteDto.getnNote();
			noteService.edit(note);
			log("edit更新便签 : "
					+ JSON.toJSONString(note,
							SerializerFeature.WriteMapNullValue));
			return new AjaxObj(1, "更新成功");
		} catch (Exception e) {
			return new AjaxObj(0, "更新失败");
		}
	}
}

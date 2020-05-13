package com.tledu.aaa.controller;

import java.util.List;

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
import com.tledu.aaa.dto.MessageDto;
import com.tledu.aaa.model.Message;
import com.tledu.aaa.model.User;
import com.tledu.aaa.service.IMessageService;
import com.tledu.aaa.util.AjaxObj;
import com.tledu.aaa.util.Pager;
@Controller
@RequestMapping("/message")
public class MessageController extends CommonController{
	private IMessageService messageService;
	
	public IMessageService getMessageService() {
		return messageService;
	}
	@Autowired
	public void setMessageService(IMessageService messageService) {
		this.messageService = messageService;
	}
	
	@RequestMapping("/list")
	public String list(){
		return "message/list";
	}
	@RequestMapping("staticList")
	public String staticList(){
		return "message/staticList";
	}
	@RequestMapping("/pager")
	@ResponseBody
	public Pager<Message> pager(String search,int page, int limit){
		if (search!=null&&!search.trim().equals("")) {
			log("搜索消息 : { "+search+" }");
		}
		return messageService.find(search, page, limit);
	}
	@RequestMapping(value="/messages",method=RequestMethod.POST)
	@ResponseBody
	public List<Message> Messages(){
		return messageService.list();
	}
	
	@RequestMapping("/add")
	public String add(Model model,HttpSession session){
		User user = (User) session.getAttribute("loginUser");
		model.addAttribute("user", user);
		model.addAttribute("messageTypes", messageService.listMessageTypes());
		return "message/add";
	}
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@ResponseBody
	public AjaxObj add(Message message){
		try {
			messageService.add(message);
			log("添加消息: "+ JSON.toJSONString(message,
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
			messageService.delete(id);
			log("删除消息 : { ID : " + id + "}");
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
				messageService.delete(id);
				idsString += "," + id;
			}
			log("删除消息 : { Ids : " + idsString.substring(1) + "}");
			return new AjaxObj(1, "删除成功");
		} catch (Exception e) {
			return new AjaxObj(0, e.getMessage());
		}
	}
	@RequestMapping("/update")
	public String update(int id,Model model){
		Message message = messageService.load(id);
		model.addAttribute("message", message);
		model.addAttribute("messageTypes", messageService.listMessageTypes());
		return "message/update";
	}
	@RequestMapping(value ="/update", method = RequestMethod.POST)
	@ResponseBody
	public AjaxObj update(Message message){
		try {
			messageService.update(message);
			log("更新消息 : "+ JSON.toJSONString(message,
					SerializerFeature.WriteMapNullValue)	);
			return new AjaxObj(1, "更新成功");
		} catch (Exception e) {
			return new AjaxObj(0, "更新失败");
		}
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public AjaxObj edit(MessageDto messageDto) {

		try {

			Message message = messageDto.getMessage();
			messageService.edit(message);
			log("edit更新消息 : "
					+ JSON.toJSONString(message,
							SerializerFeature.WriteMapNullValue));
			return new AjaxObj(1, "更新成功");
		} catch (Exception e) {
			return new AjaxObj(0, "更新失败");
		}
	}
}

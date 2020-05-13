package com.tledu.aaa.controller;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tledu.aaa.auth.Admin;
import com.tledu.aaa.auth.RoleType;
import com.tledu.aaa.model.User;
import com.tledu.aaa.model.LoginLog;
import com.tledu.aaa.service.ILoginLogService;
import com.tledu.aaa.util.AjaxObj;
import com.tledu.aaa.util.OAException;
import com.tledu.aaa.util.Pager;

@Controller
public class LoginController extends CommonController {
	private ILoginLogService loginLogService;
	
	public ILoginLogService getLoginLogService() {
		return loginLogService;
	}
	@Autowired
	public void setLoginLogService(ILoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	@Admin(RoleType.NoLogin)
	public AjaxObj  login(User user,HttpSession session,HttpServletRequest request) {
		
		try {
			User loginUser= loginLogService.login(user);
			session.setAttribute("loginUser", loginUser);
			LoginLog loginLog = new LoginLog();
			loginLog.setCome_time(new Date());
			loginLog.setIp(request.getRemoteAddr());
			loginLog.setUser(loginUser);
			loginLogService.add(loginLog);
			return new AjaxObj(1,"登陆成功");
		} catch (OAException e) {
			return new AjaxObj(0, e.getMessage());
		}	
	}
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/login";
		
	}
	@RequestMapping("/loginLog/list")
	public String list() {
		return "loginLog/list";
	}
	
	@RequestMapping("/loginLog/pager")
	@ResponseBody
	public Pager<LoginLog> pager(int page,int limit,String search) throws Exception {
		if (search == null ||search.trim().equals("")) {
			search="";
		}else {
			search=search.trim();
			search= new String(search.getBytes("ISO-8859-1"),"UTF-8");
			log("搜索日志 : { "+search+" }");
		}		
		return loginLogService.find(search, page, limit);
		
	}
	
	
}

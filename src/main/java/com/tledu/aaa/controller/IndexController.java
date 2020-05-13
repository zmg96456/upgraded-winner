package com.tledu.aaa.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tledu.aaa.auth.Admin;
import com.tledu.aaa.auth.RoleType;
import com.tledu.aaa.model.User;
import com.tledu.aaa.service.ISidebarSupService;

@Controller
public class IndexController {
		private ISidebarSupService sidebarSupService;

		public ISidebarSupService getSidebarSupService() {
			return sidebarSupService;
		}
		@Autowired
		public void setSidebarSupService(ISidebarSupService sidebarSupService) {
			this.sidebarSupService = sidebarSupService;
		}
		@RequestMapping({"/","/login"})
		@Admin(RoleType.NoLogin)
		public String login() {
			return "login";
			
		}
		@RequestMapping("/index")
		public String index(Model model,HttpSession session) {
			User loginUser = (User) session.getAttribute("loginUser");
			model.addAttribute("sidebarSups", sidebarSupService.list(loginUser));
			return "index";
			
		}
		@RequestMapping("/welcome")
		public  String welocme() {
			return "welcome";
			
		}
}

package com.tledu.aaa.model;

import java.util.List;

//侧边栏父模块

public class SidebarSup {
	private int id;
	private String name;
	private int isAdmin;

	private List<SidebarSub> sidebarSubs;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	public List<SidebarSub> getSidebarSubs() {
		return sidebarSubs;
	}

	public void setSidebarSubs(List<SidebarSub> sidebarSubs) {
		this.sidebarSubs = sidebarSubs;
	}

}

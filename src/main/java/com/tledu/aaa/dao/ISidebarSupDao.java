package com.tledu.aaa.dao;

import java.util.List;

import com.tledu.aaa.model.SidebarSup;

public interface ISidebarSupDao {
	public List<SidebarSup> list(int isAdmin);
}

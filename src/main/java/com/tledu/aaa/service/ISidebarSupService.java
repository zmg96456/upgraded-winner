package com.tledu.aaa.service;

import java.util.List;

import com.tledu.aaa.model.SidebarSup;
import com.tledu.aaa.model.User;

public interface ISidebarSupService {
	public List<SidebarSup> list(User user);
}

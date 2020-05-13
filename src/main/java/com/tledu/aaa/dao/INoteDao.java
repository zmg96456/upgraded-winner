package com.tledu.aaa.dao;

import java.util.List;

import com.tledu.aaa.model.Note;
import com.tledu.aaa.util.Pager;

public interface INoteDao {
	public Note load(int id);
	
	 public List<Note> list();
	public int find_count(String search);

	public Pager<Note> find(String search, int page, int limit);
	
	//创建时间和创建者 不可以更改
	public void update(Note note);
	
	public void add(Note note);
	
	public void delete(int id);
	
	public void edit(Note note);
	
}

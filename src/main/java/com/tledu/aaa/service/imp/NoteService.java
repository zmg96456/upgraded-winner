package com.tledu.aaa.service.imp;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tledu.aaa.dao.INoteDao;
import com.tledu.aaa.model.Note;
import com.tledu.aaa.service.INoteService;
import com.tledu.aaa.util.Pager;

@Service()
public class NoteService implements INoteService {
	private INoteDao noteDao;
	

	public INoteDao getNoteDao() {
		return noteDao;
	}
	@Autowired
	public void setNoteDao(INoteDao noteDao) {
		this.noteDao = noteDao;
	}

	@Override
	public Note load(int id) {
		return noteDao.load(id);
	}

	@Override
	public List<Note> list() {

		return noteDao.list();
	}

	@Override
	public Pager<Note> find(String search, int page, int limit) {

		try {
			if (search == null || search.trim().equals("")) {
				search = "";
			} else {
				// GET请求,汉字会乱码,需要转码
				search = search.trim();
				search = new String(search.getBytes("ISO-8859-1"), "UTF-8");
			}
			search = "%" + search + "%";
			return noteDao.find(search, page, limit);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(Note note) {
		noteDao.update(note);
	}

	@Override
	public void add(Note note) {
		noteDao.add(note);
	}

	@Override
	public void delete(int id) {
		noteDao.delete(id);

	}

	@Override
	public void edit(Note note) {
		noteDao.edit(note);

	}

}

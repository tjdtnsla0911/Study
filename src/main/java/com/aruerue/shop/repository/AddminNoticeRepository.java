package com.aruerue.shop.repository;

import java.util.List;

import com.aruerue.shop.model.Notice;

public interface AddminNoticeRepository {
	List<Notice> findAllNotice();

	//////////////////////////////////////
	public Notice findSelectnotice(int id);
	public Notice findUpnotice(int id);
	public List<Notice> findDownnotice(int id);
	public void updateSelectNotice(Notice notice);
	////////////////////////////////////////
	public int insertNotice(Notice notice);
	public void deleteNotice(int id);
	/////////////////////////////
	public void update(Notice notice);

}

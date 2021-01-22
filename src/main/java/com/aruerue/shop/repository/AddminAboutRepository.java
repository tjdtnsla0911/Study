package com.aruerue.shop.repository;

import java.util.List;

import com.aruerue.shop.addminDto.AddminAboutDto;

public interface AddminAboutRepository {
	List<AddminAboutDto> AboutList();
	 public int deleteAbout(int id);

}

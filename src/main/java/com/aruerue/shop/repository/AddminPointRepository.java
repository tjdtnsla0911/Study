  
package com.aruerue.shop.repository;

import java.util.List;

import com.aruerue.shop.addminDto.AddminPointDto;

public interface AddminPointRepository {
	List<AddminPointDto> findAllPointList();
	List<AddminPointDto> findbyUsersPoint(int userId);
	public void insertPoint(AddminPointDto addminPointDto);

}

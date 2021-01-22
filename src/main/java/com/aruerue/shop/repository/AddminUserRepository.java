package com.aruerue.shop.repository;

import java.util.List;

import com.aruerue.shop.addminDto.AddminPointDto;
import com.aruerue.shop.model.user.User;

public interface AddminUserRepository {
///////////////////////////////////여긴 Point꺼임 즉 addmin에서씀
void updatePoint(AddminPointDto addminPointDto);
List<User> findAll();
////////////////////////////////////////////////////////////
}

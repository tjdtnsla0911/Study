package com.aruerue.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aruerue.shop.addminDto.AddminPointDto;
import com.aruerue.shop.model.user.User;
import com.aruerue.shop.repository.AddminPointRepository;
import com.aruerue.shop.repository.AddminUserRepository;
import com.aruerue.shop.repository.UserRepository;

@Controller
public class AddminPointController {

	@Value("${file.path}")
	private String fileRealPath;

	@Autowired
	AddminPointRepository addminPointRepository;
	@Autowired
	AddminUserRepository addUserRepository;
	private User user;

	private AddminPointDto addminPointDto;
	@GetMapping("/pointList")
	public String PointList(Model model) {
		System.out.println("pointList에 왔습니다 ^^");
		List<User> pointList = addUserRepository.findAll();
		model.addAttribute("pointList",pointList);
		return "addminPointList";
	}

	@PutMapping("/pointDetail/{id}")
	public @ResponseBody List<AddminPointDto> poinDetail(@PathVariable int id){
		System.out.println("상세포인트 찾으러왔습니다");
		System.out.println("상세포인트의 받은 userId = "+id);

		List<AddminPointDto> findUsersPoint = addminPointRepository.findbyUsersPoint(id);
		for(int i=0;i<=findUsersPoint.size()-1;i++) {
			System.out.println("i는 ? ="+i);
			System.out.println("꺼내온 UserTotalPoint는 ? = "+findUsersPoint.get(i).getUserTotalPoint());
			findUsersPoint.get(i).setUserTotalPoint(findUsersPoint.get(i).getUserTotalPoint());
			System.out.println("저장한 usersPoint = "+findUsersPoint.get(i).getUserTotalPoint());

		}
		System.out.println("꺼내온findUsersPoint ="+findUsersPoint);
		return findUsersPoint;

	}
	@PutMapping("/pointUpdate/{id}")
	public @ResponseBody String pointUpdate(@PathVariable int id,@RequestBody AddminPointDto addminPointDto) {
		System.out.println("pointUpdate에 왔습니다");
		System.out.println("받은 addminPointDto = "+addminPointDto);

		if(addminPointDto.getMinusPoint()>0) {
			addminPointDto.setPoint(addminPointDto.getMinusPoint()*-1);
			System.out.println("마이너스포인트 = "+addminPointDto.getPoint());
			addminPointDto.setTotalPoint(addminPointDto.getTotalPoint()+addminPointDto.getPoint());
			System.out.println("빼고나서 토탈포인트 = "+addminPointDto.getTotalPoint());
			System.out.println("User의 TotalPoint = "+addminPointDto.getTotalPoint());
			addUserRepository.updatePoint(addminPointDto);
			addminPointRepository.insertPoint(addminPointDto);

				System.out.println("인설트무사히마친듯?");
			return "null";
		}
		addminPointDto.setPoint(addminPointDto.getUpPoint());
		addminPointDto.setTotalPoint(addminPointDto.getTotalPoint()+addminPointDto.getPoint());
		addUserRepository.updatePoint(addminPointDto);
		addminPointRepository.insertPoint(addminPointDto);

		return "null";

	}



}
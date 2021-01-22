package com.aruerue.shop.controller;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aruerue.shop.addminDto.AddminAboutDto;
import com.aruerue.shop.repository.AddminAboutRepository;

@Controller
public class AddminAboutController {

	@Autowired
	AddminAboutRepository addminAboutRepository;

	@GetMapping("/addminAboutList")
	public String addminAboutList(Model model) {
		List<AddminAboutDto> aboutList = addminAboutRepository.AboutList();
		model.addAttribute("aboutList",aboutList);
		return "addminAbout";
	}
	@DeleteMapping("/addminAboutDelete/{id}")
	public @ResponseBody String addminAboutDelete(@PathVariable int id) {
		System.out.println("어바웃 델레트에옴");
		addminAboutRepository.deleteAbout(id);
		System.out.println("어바웃 델레트끝");
		return "ok";
	}


}

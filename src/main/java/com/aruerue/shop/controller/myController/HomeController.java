package com.aruerue.shop.controller.myController;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aruerue.shop.controller.dto.home.HomeAdResoDto;
import com.aruerue.shop.controller.dto.home.HomeNoticeRespDto;
import com.aruerue.shop.controller.dto.home.HomeProductRespDto;
import com.aruerue.shop.controller.dto.home.HomeReviewRespDto;
import com.aruerue.shop.controller.dto.responseDto.HomeResponseDto;
import com.aruerue.shop.repository.Repository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class HomeController {
	private final Repository repository;

	@GetMapping({"","/"})
	public HomeResponseDto home() {
		List<HomeAdResoDto> homeAdResoDtos = repository.findHomeAd();
		List<HomeNoticeRespDto> homeNoticeRespDtos = repository.findNotice();
		List<HomeProductRespDto> homeProductRespDtos = repository.findProductsForHome();
		List<HomeReviewRespDto> homeReviewRespDtos = repository.findReviewsForHome();
		
		HomeResponseDto homeResponseDto = HomeResponseDto.builder()
				.homeAdResoDto(homeAdResoDtos)
				.homeNoticeRespDto(homeNoticeRespDtos)
				.homeProductRespDto(homeProductRespDtos)
				.homeReviewRespDto(homeReviewRespDtos)
				.build();
		return homeResponseDto;
	}
}

 package com.aruerue.shop.controller.myController;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.aruerue.shop.controller.dto.ShopRespDto;
import com.aruerue.shop.controller.dto.detail.DetailProductRespDto;
import com.aruerue.shop.controller.dto.detail.DetailQnARespDto;
import com.aruerue.shop.controller.dto.detail.DetailRelatedRespDto;
import com.aruerue.shop.controller.dto.detail.DetailReviewRespDto;
import com.aruerue.shop.controller.dto.responseDto.DetailResponseDto;
import com.aruerue.shop.repository.Repository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ShopController {
	private final Repository repository;

	@GetMapping("/shop")
	public List<ShopRespDto> shop() {
		List<ShopRespDto> ShopRespDtos = repository.findProductsForShop();
		return ShopRespDtos;
	}

	@GetMapping("/shop_view/{productId}")
	public DetailResponseDto shop_view(@PathVariable int productId) {
		DetailProductRespDto detailProductRespDto = repository.findProductById(productId);
		List<DetailReviewRespDto> detailReviewRespDto = repository.findReviewsById(productId);
		List<DetailQnARespDto> detailQnARespDto = repository.findQnAByIdForDetail(productId);
		List<DetailRelatedRespDto> detailRelatedRespDto = repository.findRelatedProductsById(productId);
		DetailResponseDto detailResponseDto = DetailResponseDto.builder().detailProductRespDto(detailProductRespDto)
				.detailReviewRespDto(detailReviewRespDto).detailQnARespDto(detailQnARespDto)
				.detailRelatedRespDto(detailRelatedRespDto).build();
		return detailResponseDto;
	}
 
}

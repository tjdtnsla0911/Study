package com.aruerue.shop.controller.myController.mypage;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aruerue.shop.controller.dto.CommonRespDto;
import com.aruerue.shop.controller.dto.mypage.MypageQnaRespDto;
import com.aruerue.shop.controller.dto.mypage.MypageWishResponseDto;
import com.aruerue.shop.model.qnA.Qna;
import com.aruerue.shop.model.user.WishList;
import com.aruerue.shop.repository.MyPageRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class MypageWishlistController {
	private final MyPageRepository myPageRepository;

	@PostMapping("/shop_mypage/wish_list/{userId}")
	public List<MypageWishResponseDto> mypageWish(@PathVariable int userId) {
		List<MypageWishResponseDto> mypageWishResponseDto = (List<MypageWishResponseDto>) myPageRepository
				.findWishlistsById(userId);
		return mypageWishResponseDto;
	}

	// insert //우선 .. 토큰에서 userId를 꺼낼지 말지
	@PostMapping("/shop_mypage/wish_list/{userId}/{productId}")
	public CommonRespDto<?> saveWishlist(@PathVariable int userId, @PathVariable int productId) {
		System.out.println("saveWishlist-userId : " + userId);
		System.out.println("saveWishlist-productId : " + productId);
		myPageRepository.saveWishlist(userId, productId);
		return new CommonRespDto<String>();
	}

	// delete
	@DeleteMapping("/shop_mypage/wish_list/{userId}/{productId}")
	public CommonRespDto<?> deleteWishlist(@PathVariable int userId, @PathVariable int productId) {
		System.out.println("deleteWishlist-userId : " + userId);
		System.out.println("deleteWishlist-productId : " + productId);
		//myPageRepository.deleteWishlist(userId,productId);
		return new CommonRespDto<String>();
	}
}

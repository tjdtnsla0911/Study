package com.aruerue.shop.controller.myController;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aruerue.shop.controller.dto.CommonRespDto;
import com.aruerue.shop.controller.dto.review.ReviewDetailRespDto;
import com.aruerue.shop.controller.dto.review.ReviewRespDto;
import com.aruerue.shop.model.review.Review;
import com.aruerue.shop.repository.Repository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ReviewController {
	private final Repository repository;

	@GetMapping("/review")
	public List<ReviewRespDto> review() {
		List<ReviewRespDto> reviewRespDto = repository.findReviews();
		return reviewRespDto;
	}

	@GetMapping("/review_detail/{reviewId}")
	public ReviewDetailRespDto reviewDetail(@PathVariable int reviewId) {
		ReviewDetailRespDto reviewDetailRespDto = repository.findReviewById(reviewId);
		return reviewDetailRespDto;
	}

	// insert
	@PostMapping("/review")
	public CommonRespDto<?> saveReview(@RequestBody Review review) {
		System.out.println("review : " + review);
		repository.saveReview(review);
		return new CommonRespDto<String>();
	}

	// update
	@PutMapping("/review")
	public CommonRespDto<?> updateReview(@RequestBody Review review) {
		System.out.println("review : " + review);
		repository.updateReview(review);
		return new CommonRespDto<String>();
	}

	// delete
	@DeleteMapping("/review/{reviewId}")
	public CommonRespDto<?> deleteReview(@PathVariable int reviewId) {
		System.out.println(" id : " + reviewId);
		repository.deleteReview(reviewId);
		return new CommonRespDto<String>();
	}

}

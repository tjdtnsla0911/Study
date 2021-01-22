package com.aruerue.shop.repository;

import java.util.List;

import com.aruerue.shop.addminDto.AddminReviewDto;

public interface AddminReviewRepository {
	List<AddminReviewDto> findAllReviewList();
	List<AddminReviewDto> findAllWaitingReviewList();
	public void insertrecomment(AddminReviewDto addminDto);

}

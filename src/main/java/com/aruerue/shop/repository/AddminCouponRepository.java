package com.aruerue.shop.repository;

import java.util.List;

import com.aruerue.shop.addminDto.AddminCouponDto;

public interface AddminCouponRepository {
	List<AddminCouponDto> findAllCouponList();
	List<AddminCouponDto>findCount();
	public void insertCoupon(AddminCouponDto addminCouponDto);
	List<AddminCouponDto> findNullCoupon(int userId);
	public void updateCoupon(AddminCouponDto updateCoupon);
	List<AddminCouponDto> findByCouponUserAndCoupon(int userId);

}

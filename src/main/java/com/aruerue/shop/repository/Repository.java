package com.aruerue.shop.repository;

import java.util.List;

import com.aruerue.shop.controller.dto.CartRespDto;
import com.aruerue.shop.controller.dto.ShopRespDto;
import com.aruerue.shop.controller.dto.detail.DetailProductRespDto;
import com.aruerue.shop.controller.dto.detail.DetailQnARespDto;
import com.aruerue.shop.controller.dto.detail.DetailRelatedRespDto;
import com.aruerue.shop.controller.dto.detail.DetailReviewRespDto;
import com.aruerue.shop.controller.dto.home.HomeAdResoDto;
import com.aruerue.shop.controller.dto.home.HomeNoticeRespDto;
import com.aruerue.shop.controller.dto.home.HomeProductRespDto;
import com.aruerue.shop.controller.dto.home.HomeReviewRespDto;
import com.aruerue.shop.controller.dto.notice.NoticeDetailsRespDto;
import com.aruerue.shop.controller.dto.notice.NoticeRespDto;
import com.aruerue.shop.controller.dto.qnA.CommentOnQnARespDto;
import com.aruerue.shop.controller.dto.qnA.QnARespDto;
import com.aruerue.shop.controller.dto.review.ReviewDetailRespDto;
import com.aruerue.shop.controller.dto.review.ReviewRespDto;
import com.aruerue.shop.model.qnA.Qna;
import com.aruerue.shop.model.review.Review;
import com.aruerue.shop.model.user.Cart;

public interface Repository {
	List<HomeAdResoDto> findHomeAd();
	List<HomeNoticeRespDto> findNotice();
	List<HomeProductRespDto> findProductsForHome();
	List<HomeReviewRespDto> findReviewsForHome();

	List<ShopRespDto> findProductsForShop();

	DetailProductRespDto findProductById(int id);
	List<DetailReviewRespDto> findReviewsById(int id);
	List<DetailQnARespDto> findQnAByIdForDetail(int id);
	List<DetailRelatedRespDto> findRelatedProductsById(int id);

	List<ReviewRespDto> findReviews();
	ReviewDetailRespDto findReviewById(int id);
	void saveReview(Review review);
	void updateReview(Review review);
	void deleteReview(int id);


	List<NoticeRespDto> findNotices();
	List<NoticeDetailsRespDto> findNoticeById(int id);

	List<QnARespDto> findQnAs();
	CommentOnQnARespDto findQnADetailById(int id);
	void saveQnA(Qna qna);
	void updateQna(Qna qna);
	void deleteQna(int id);
	
	List<CartRespDto> findCartsById(int id);		
	void saveCart(Cart cart);
	void deleteCart(int id);

}

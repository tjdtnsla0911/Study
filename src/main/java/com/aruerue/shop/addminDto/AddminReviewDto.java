package com.aruerue.shop.addminDto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddminReviewDto {
	private String username;
	private String title;
	private String thumb;
	private String img;
	private Boolean exstenceOfImg;
	private int orderId;
	private String howToPay;
	private int star;
	private Date createDate;
	private int id;
	private int productId;
	private String content;
	private String comment;
	private String addCreateDate;

	///////////////치환할꺼
	private String realStar;
	
	
	
	

}

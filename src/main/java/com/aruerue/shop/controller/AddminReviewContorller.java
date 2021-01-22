package com.aruerue.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.aruerue.shop.addminDto.AddminReviewDto;
import com.aruerue.shop.repository.AddminRepository;
import com.aruerue.shop.repository.AddminReviewRepository;

@Controller
public class AddminReviewContorller {
	@Autowired
	AddminReviewRepository addminReviewRepository;
	//여기서 review를 만듭니다
	@GetMapping("/addminReviewList")
	public String AddminReview(Model model){
		String http ="http://localhost:8080";
		List<AddminReviewDto> reviewList = addminReviewRepository.findAllReviewList();
		
		System.out.println("가져온 reviewList = "+reviewList);
		for(int i=0;i<=reviewList.size()-1;i++) {
			
			String http2 = http+reviewList.get(i).getThumb();
			System.out.println("뽑은 사진경로 = "+http2);
			reviewList.get(i).setThumb(http2);
			
			///////////////////////////////////////
			if(reviewList.get(i).getStar()==1) {
				reviewList.get(i).setRealStar("★");
				
			}else if(reviewList.get(i).getStar()==2) {
				reviewList.get(i).setRealStar("★★");
				
			}else if(reviewList.get(i).getStar()==3) {
				reviewList.get(i).setRealStar("★★★");
				
			}else if(reviewList.get(i).getStar()==4) {
				reviewList.get(i).setRealStar("★★★★");
				
			}else {
				reviewList.get(i).setRealStar("★★★★★");
			}
		}
		int size = reviewList.size();
		model.addAttribute("size",size);
		model.addAttribute("reviewList",reviewList);
		return "addminReview";
	}
	@GetMapping("/addminWatingAnswser")
	public String addminWatingAnswser(Model model) {
		List<AddminReviewDto> notReivew = addminReviewRepository.findAllWaitingReviewList();
		String http ="http://localhost:8080";
		
for(int i=0;i<=notReivew.size()-1;i++) {
			
			String http2 = http+notReivew.get(i).getThumb();
			System.out.println("뽑은 사진경로 = "+http2);
			notReivew.get(i).setThumb(http2);
			
			///////////////////////////////////////
			if(notReivew.get(i).getStar()==1) {
				notReivew.get(i).setRealStar("★");
				
			}else if(notReivew.get(i).getStar()==2) {
				notReivew.get(i).setRealStar("★★");
				
			}else if(notReivew.get(i).getStar()==3) {
				notReivew.get(i).setRealStar("★★★");
				
			}else if(notReivew.get(i).getStar()==4) {
				notReivew.get(i).setRealStar("★★★★");
				
			}else {
				notReivew.get(i).setRealStar("★★★★★");
			}
		}
		
		int size = notReivew.size();
		model.addAttribute("size", size);
		model.addAttribute("notReivew", notReivew);
		return "addminWaitingReview";
	}
	
	@PostMapping("//addminReviewAnswer")
	public String addminReviewAnswer(AddminReviewDto addminReviewDto){
		System.out.println("에 왔습니다");
		System.out.println("받은값  ="+addminReviewDto);
		addminReviewRepository.insertrecomment(addminReviewDto);
		System.out.println("끝");
		return "redirect:/addminReviewList";
	}

}

package com.aruerue.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aruerue.shop.addminDto.AddminCouponDto;
import com.aruerue.shop.model.user.User;
import com.aruerue.shop.repository.AddminCouponRepository;
import com.aruerue.shop.repository.AddminUserRepository;




@Controller
public class AddminCouponController {


	@Autowired
	AddminCouponRepository addminCouponRepository;

	@Autowired
	AddminUserRepository addminUserRepository;
	@GetMapping("/AddminCouponList")
	public String addminCoupon(Model model,Model model2,Model model3) {
		int userId=0;

		List<AddminCouponDto> couponUserList = addminCouponRepository.findAllCouponList();//현재 전체유저가 몆개의 쿠폰을 가지고있냐
		List<AddminCouponDto> nowCoupon = addminCouponRepository.findNullCoupon(userId); //클릭한 유저의 현재가진 쿠폰
		int size = nowCoupon.size();
		System.out.println("카운트 개수 = "+size);
		System.out.println("다이렉트로 쿠폰꼽기 = "+addminCouponRepository.findCount());
		System.out.println("뽑아온 user들 ="+couponUserList);


		model.addAttribute("couponUserList",couponUserList);
		model2.addAttribute("nowCoupon",nowCoupon);
		model3.addAttribute("size",size);
		return "addminCouponList";
	}

	//여기서 쿠폰 번호 난수생성
	@GetMapping("/makeNumber")
	public @ResponseBody String addminMakeNumber() {
		System.out.println("addminMakeNumber에 왔습니다 = ");
		int a = (int)(Math.random()*5555)+1234;
		int a2 = (int)(Math.random()*5555)+1234;
		int a3 = (int)(Math.random()*5555)+1234;
		int a4 = (int)(Math.random()*5555)+1234;
		String code = a+"-"+a2+"-"+a3+"-"+a4; //<--이놈이 코드생성기
		System.out.println("생성된 코드 = "+code);
		return code;
	}
	@PostMapping("/uploadCoupon")
	public @ResponseBody String uploadCoupon(@RequestBody AddminCouponDto addminCouponDto) {
		System.out.println("업로드쿠폰에왓쪙");
		System.out.println("업로드 받은값 = "+addminCouponDto);

		addminCouponRepository.insertCoupon(addminCouponDto);
		System.out.println("무사히마침");
		return "null";
	}

	@PutMapping("/giveCoupon/{userId}")
	public @ResponseBody String giveCoupon(@RequestBody AddminCouponDto addminCouponDto) {
		System.out.println("기브 쿠폰에왔습니다.");

		System.out.println("addminCouponDto = "+addminCouponDto);
		addminCouponRepository.updateCoupon(addminCouponDto);
		System.out.println("업데이트끝");


		return null;
	}

	@PutMapping("/findCoupon/{userId}")
	public @ResponseBody List<AddminCouponDto> findCoupon(@PathVariable int userId) {
		System.out.println("findCoupon에 왔습니다 ^^");
		System.out.println("받은 userId = "+userId);
		List<AddminCouponDto> addminCoDtos = addminCouponRepository.findByCouponUserAndCoupon(userId);
		for(int i=0; i<=addminCoDtos.size()-1;i++) {
			if(addminCoDtos.get(i).isAvailability()==true) {
				System.out.println("트루에왔습니다");
				addminCoDtos.get(i).setChangeAvailability("만료된쿠폰");

			}else {
				addminCoDtos.get(i).setChangeAvailability("사용가능한쿠폰");
			}
		}
		System.out.println("받은 내용 = "+addminCoDtos);
		return addminCoDtos;
	}


}

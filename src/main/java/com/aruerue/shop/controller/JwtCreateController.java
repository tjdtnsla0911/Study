package com.aruerue.shop.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aruerue.shop.config.jwt.JwtProperties;
import com.aruerue.shop.config.ouath.provider.CommonUser;
import com.aruerue.shop.config.ouath.provider.GoogleUser;
import com.aruerue.shop.config.ouath.provider.OAuthUserInfo;
import com.aruerue.shop.controller.dto.ProductDto;
import com.aruerue.shop.model.user.User;
import com.aruerue.shop.repository.UserRepository;
import com.aruerue.shop.service.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class JwtCreateController {

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	private final UserRepository userRepository;
	private final UserService userService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;




	@GetMapping("/test/index")
	public List<ProductDto> asdf() {
		System.out.println("asdf에 왔습니다.");
		List<ProductDto> dto = userRepository.productDto();
		return dto;
	}




//일반유저 로그인
	@PostMapping("/oauth/jwt/common")
	public String commonlogin(@RequestBody Map<String, Object> data) {
		System.out.println("controller.JwtCreateController.java의 jwtCreate에 왔습니다 ");
		System.out.println("여긴 데이터 data = "+data);
		CommonUser commonUser =
				new CommonUser((Map<String, Object>)data);
		System.out.println("------------------------------------------------------------");
		System.out.println("googleUser.getProvider() = "+commonUser.getProvider());
		System.out.println("googleUser.getProviderId() = "+commonUser.getProviderId());
		System.out.println("googleUser.패스워드() = "+commonUser.getPassword());
		System.out.println(commonUser);

		System.out.println("여기까진왔나? ");
		System.out.println("commonUser.getName() = "+commonUser.getName());
		System.out.println("commonUser = ");

//
		User userEntity =
				userRepository.findByUsername(commonUser.getName());
	//	User userEntity = userRepository.findByUsernameAndPassword(commonUser.getName(),commonUser.getPassword());
//		System.out.println("controller.JwtCreateController.java의 jwtCreate의 userEntity = "+userEntity);

		if(passwordEncoder.matches(commonUser.getPassword(), userEntity.getPassword())){

			System.out.println("비번일치");
			String jwtToken = JWT.create()
					.withSubject(userEntity.getUsername())
					.withExpiresAt(new Date(System.currentTimeMillis()+JwtProperties.EXPIRATION_TIME))
					.withClaim("id", userEntity.getId())
					.withClaim("username", userEntity.getUsername())
					.sign(Algorithm.HMAC512(JwtProperties.SECRET));
			System.out.println("controller.JwtCreateController.java의 jwtCreate의 jwtToken = "+jwtToken);

			return jwtToken;


		}else {
			System.out.println("비번불일치");
			return "아이디 혹은 패스워드가 일치하지않습니다";
		}

	}






	//구글 로그인
	@PostMapping("/oauth/jwt/google")
	public String jwtCreate(@RequestBody Map<String, Object> data) {
		System.out.println("-----------------");
		System.out.println("controller.JwtCreateController.java의 jwtCreate에 왔습니다 ");
		System.out.println("여긴 데이터 data = "+data);
		System.out.println(data.get("profileObj"));//구글에서 주는양식 .

		String jwtToken = userService.유저찾기(data);

		return jwtToken;
		////////////////



		/*
		 * OAuthUserInfo googleUser = new GoogleUser((Map<String,
		 * Object>)data.get("profileObj")); System.out.println(
		 * "------------------------------------------------------------");
		 * System.out.println("googleUser.getProvider() = "+googleUser.getProvider());
		 * System.out.println("googleUser.getProvider() = "+googleUser.getProviderId());
		 * User userEntity =
		 * userRepository.findByUsername(googleUser.getProvider()+"_"+googleUser.
		 * getProviderId()); System.out.
		 * println("controller.JwtCreateController.java의 jwtCreate의 userEntity = "
		 * +userEntity); if(userEntity == null) { System.out.
		 * println("controller.JwtCreateController.java의 jwtCreate의 if(userEntity == null)에 왔습니다 "
		 * ); User userRequest = User.builder()
		 * .username(googleUser.getProvider()+"_"+googleUser.getProviderId())
		 * .password(bCryptPasswordEncoder.encode("겟인데어")) .email(googleUser.getEmail())
		 * .provider(googleUser.getProvider()) .providerId(googleUser.getProviderId())
		 * .role("ROLE_USER") .build(); userEntity =
		 * userRepository.saveGoogle(userRequest); System.out.
		 * println("controller.JwtCreateController.java의 jwtCreate의 if(userEntity == null)의 userEntity = "
		 * +userEntity); }
		 *
		 * String jwtToken = JWT.create() .withSubject(userEntity.getUsername())
		 * .withExpiresAt(new
		 * Date(System.currentTimeMillis()+JwtProperties.EXPIRATION_TIME))
		 * .withClaim("id", userEntity.getId()) .withClaim("username",
		 * userEntity.getUsername()) .sign(Algorithm.HMAC512(JwtProperties.SECRET));
		 * System.out.
		 * println("의controller.JwtCreateController.java의 jwtCreate의jwtTkoen입니다 ="
		 * +jwtToken); return jwtToken;
		 */
		////////////////////////////
	}


}

package com.example.demo.src.user;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.user.model.PostLoginReq;
import com.example.demo.src.user.model.PostLoginRes;
import com.example.demo.src.user.model.PostUserReq;
import com.example.demo.src.user.model.PostUserRes;
import com.example.demo.utils.JwtService;
import com.example.demo.utils.Util;
import com.example.demo.utils.ValidationRegex;
import lombok.extern.slf4j.Slf4j;
import org.intellij.lang.annotations.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

	@Autowired
	private final UserProvider userProvider;

	@Autowired
	private final UserService userService;

	@Autowired
	private final JwtService jwtService;

	public UserController(UserProvider userProvider, UserService userService, JwtService jwtService) {
		this.userProvider = userProvider;
		this.userService = userService;
		this.jwtService = jwtService;
	}

	@ResponseBody
	@PostMapping("/login")
	public BaseResponse<PostLoginRes> login(@RequestBody PostLoginReq postLoginReq) throws BaseException {

		try {
			// TODO: 로그인 값들에 대한 검증 필요 ( XSS 방지 )
			PostLoginRes postLoginRes = userProvider.login(postLoginReq);
			return new BaseResponse<>(postLoginRes);
		} catch (BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}

//	@ResponseBody
//	@PostMapping("/join")
//	public BaseResponse<PostUserRes> createUser(@RequestBody PostUserReq postUserReq) throws BaseException {
//
//		log.debug("[IN] createUser()={}", postUserReq.toString());
//
//		// 하나라도 입력값 누락
//		if (!Util.isEmpty(postUserReq)) {
//			return new BaseResponse<>(REQUEST_ERROR);
//		}
//
//		// 이메일 검증(정규표현식)
//		if (!isRegexEmail(postUserReq.getEmail())) {
//			return new BaseResponse<>(POST_USERS_INVALID_EMAIL);
//		}
//
//		// TODO: @Pattern 이용하여 휴대전화 검증
////		if (!isRegexPhoneNumber(postUserReq.getPhoneNumber())) {
////			return new BaseResponse<>(POST_USERS_INVALID_PHONE_NUMBER);
////		}
//
//
//		// TODO: 중복된 닉네임 확인
//
//		// TODO: 이미 존재하는 회원인지 검증 (이메일)
//		try {
//			PostUserRes postUserRes = userService.createUser(postUserReq);
//			return new BaseResponse<>(postUserRes);
//		} catch (BaseException exception) {
//			return new BaseResponse<>((exception.getStatus()));
//		}
//		try {
//			PostUserRes postUserRes = userService.createUser(postUserReq);
//			return new BaseResponse<>(postUserRes);
//		} catch (BaseException exception) {
//			return new BaseResponse<>((exception.getStatus()));
//		}

}

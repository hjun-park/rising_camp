package com.example.demo.src.member;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.member.model.MemberDTO;
import com.example.demo.utils.JwtService;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.src.member.model.MemberDTO.*;

@RestController
@RequestMapping("/members")
@Slf4j
public class MemberController {

	@Autowired
	private final MemberProvider memberProvider;

	@Autowired
	private final MemberService memberService;

	@Autowired
	private final JwtService jwtService;

	public MemberController(MemberProvider memberProvider, MemberService memberService, JwtService jwtService) {
		this.memberProvider = memberProvider;
		this.memberService = memberService;
		this.jwtService = jwtService;
	}


	//01
	@ResponseBody
	@PostMapping("/register")
	public BaseResponse<Integer> signUp(@RequestBody MemberDTO memberDTO) {
		// validation
		if (hasNullDataWhenJoin(memberDTO)) {
			return new BaseResponse<>(POST_USERS_INVALID_INFO);
		}

		try {
			Integer memberId = memberService.joinMember(memberDTO);
			return new BaseResponse<>(memberId);
		} catch (BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}

	}

	//02
	@ResponseBody
	@PostMapping("/login")
	public BaseResponse<Integer> login(@RequestBody MemberLoginReq memberLoginReq) {
		// 입력값 누락 확인 (email, password)
		String email = memberLoginReq.getEmail();
		String password = memberLoginReq.getPassword();

		try {
			MemberDTO memberDTO = memberProvider.login(email, password);
			return new BaseResponse<>(memberDTO.getId());
		} catch (BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}

	}


	//05
	@ResponseBody
	@PatchMapping("/{member-id}")
	public BaseResponse<Integer> modifyMemberName(@RequestParam("member-id") int memberId,
												  MemberDTO memberDTO) {
		try {
			Integer resultId = memberService.modifyMemberName(memberId, memberDTO.getName());
			return new BaseResponse<>(resultId);
		} catch (BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}

	//07 JWT 개념 익힌 후 구현
//	@ResponseBody
//	@GetMapping("/info")
//	public BaseResponse<MemberDTO> lookupMember() {
//
//	}

	//08 이메일 수신 동의 API
	@ResponseBody
	@GetMapping("/{member-id}/email")
	public BaseResponse<Integer> modifyAcceptEmail(@RequestParam("member-id") int memberId,
												  MemberDTO memberDTO) {
		String emailStatus = memberDTO.getMailAccept();
		try {
			Integer resultId = memberService.modifyAcceptEmail(memberId, emailStatus);
			return new BaseResponse<>(resultId);
		} catch (BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}

	//09 SMS 수신 동의 API
	@ResponseBody
	@GetMapping("/{member-id}/sms")
	public BaseResponse<Integer> modifyAcceptSms(@RequestParam("member-id") int memberId,
												   MemberDTO memberDTO) {
		String smsStatus = memberDTO.getSmsAccept();
		try {
			Integer resultId = memberService.modifyAcceptSms(memberId, smsStatus);
			return new BaseResponse<>(resultId);
		} catch (BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}



//	// 로그아웃 결과에 따라서 처리
//	@ResponseBody
//	@GetMapping("/logout")
//	public BaseResponse<Integer> logout() {
//
//	}

//	// 패스워드 수정
//	@ResponseBody
//	@PatchMapping("/{user-id}/password")
//	public BaseResponse<Integer>


//	@ResponseBody
//	@PostMapping


//
//	@ResponseBody
//	@PostMapping("/login")
//	public BaseResponse<PostLoginRes> login(@RequestBody PostLoginReq postLoginReq) {
//		System.out.println("######UserController.login");
//		System.out.println("postLoginReq = " + postLoginReq);
//		System.out.println("postLoginReq = " + postLoginReq.getEmail());
//		System.out.println("postLoginReq = " + postLoginReq.getPassword());
//		log.debug("#################1) {}", postLoginReq.getPassword());
//		log.debug("#################1) {}", postLoginReq.getEmail());
//
//		try {
//			// TODO: 로그인 값들에 대한 검증 필요 ( XSS 방지 )
//			PostLoginRes postLoginRes = userProvider.login(postLoginReq);
//			return new BaseResponse<>(postLoginRes);
//		} catch (BaseException exception) {
//			return new BaseResponse<>(exception.getStatus());
//		}
//	}
//
//	@ResponseBody
//	@PatchMapping("/{userId}")
//	public BaseResponse<String> modifyUserName(@PathVariable int userId, @RequestBody User user) {
//		try {
//			PatchUserReq patchUserReq = new PatchUserReq(userId, user.getName());
//			userService.modifyUserName(patchUserReq);
//		} catch (BaseException exception) {
//			return new BaseResponse<>(exception.getStatus());
//		}
//
//		String result = "";
//		return new BaseResponse<>(result);
//
//	}
//
//	@ResponseBody
//	@GetMapping("/{userId}/baskets/{basketId}")
//	public BaseResponse<List<GetUserBasketRes>> getUserBasket(@PathVariable("userId") int userId,
//															  @PathVariable("basketId") int basketId) {
//		try {
//			List<GetUserBasketRes> getUserBasketRes = userProvider.getUserBasket(userId, basketId);
//			return new BaseResponse<>(getUserBasketRes);
//		} catch (BaseException exception) {
//			return new BaseResponse<>(exception.getStatus());
//		}
//	}
//
//	@ResponseBody
//	@PostMapping("/{userId}/baskets")
//	public BaseResponse<Integer> postUserBasket(@PathVariable("userId") int userId,
//												@RequestBody PostUserBasketReq postUserBasketReq) {
//
//		log.debug("##### {}", postUserBasketReq.getMenuName());
//		try {
//			Integer basketId = userService.postUserBasket(userId, postUserBasketReq);
//			return new BaseResponse<>(basketId);
//		} catch (BaseException exception) {
//			return new BaseResponse<>(exception.getStatus());
//		}
//	}
//
//	//7 - 장바구니 수량 변경
//	@ResponseBody
//	@PatchMapping("/{userId}/baskets/{basketId}")
//	public BaseResponse<String> postUserBasket(@PathVariable("userId") int userId,
//											   @PathVariable("basketId") int basketId,
//											   @RequestBody Basket basket) {
//		try {
//			PatchUserBasketReq patchUserBasketReq = new PatchUserBasketReq(userId, basketId, basket.getAmount());
//			userService.modifyAmount(patchUserBasketReq);
//
//			String result = "";
//			return new BaseResponse<String>(result);
//		} catch (BaseException exception) {
//			return new BaseResponse<>(exception.getStatus());
//		}
//	}


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

	// ========== login 객체 ==========//
	@Setter
	@Getter
	private static class MemberLoginReq {
		private String email;
		private String password;
	}
}

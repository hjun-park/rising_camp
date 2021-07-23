package com.example.demo.src.member;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.member.model.MemberDTO;
import com.example.demo.src.member.model.MemberLevelRes;
import com.example.demo.src.member.model.MemberReq;
import com.example.demo.src.member.model.MemberRes;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.INVALID_USER_JWT;
import static com.example.demo.config.BaseResponseStatus.REQUEST_ERROR;

@RestController
@RequestMapping("/members")
@Slf4j
public class MemberController {

	private final MemberProvider memberProvider;
	private final MemberService memberService;
	private final JwtService jwtService;

	public MemberController(MemberProvider memberProvider, MemberService memberService, JwtService jwtService) {
		this.memberProvider = memberProvider;
		this.memberService = memberService;
		this.jwtService = jwtService;
	}


	//01
	@PostMapping("/register")
	public BaseResponse<MemberRes> postJoin(@RequestBody MemberDTO memberDTO) { // ****궁금증_2: 컨트롤러에서 DTO 객체로 받아도 되는지
		// validation
		if (MemberDTO.hasNullDataWhenJoin(memberDTO)) {
			return new BaseResponse<>(REQUEST_ERROR);
		}

		// 이메일 검증

		try {
			MemberRes memberRes = memberService.joinMember(memberDTO);
			return new BaseResponse<>(memberRes);
		} catch (BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}

	}


	//02
	@PostMapping("/login")
	public BaseResponse<MemberRes> postMemberLogin(@RequestBody MemberReq memberReq) {
		// 입력값 누락 확인 (email, password)
		try {
			// TODO: 로그인 값들에 대한 형식적인 validatin 처리해주셔야합니다!
			// TODO: 유저의 status ex) 비활성화된 유저, 탈퇴한 유저 등을 관리해주고 있다면 해당 부분에 대한 validation 처리도 해주셔야합니다.
			MemberRes memberRes = memberProvider.login(memberReq);
			return new BaseResponse<>(memberRes);
		} catch (BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}

	//04 회원정보 - 비밀번호 수정
	@PatchMapping("/{member-id}/password")
	public BaseResponse<Integer> patchMemberPwd(@PathVariable("member-id") int memberId,
												  @RequestBody MemberReq memberReq) {
		try {
			// jwt에서 id값 추출
			int memberIdByJwt = jwtService.getUserIdx();

			// 수정 요청한 id값이 본인이 아니라면 에러
			if(memberId != memberIdByJwt) {
				return new BaseResponse<>(INVALID_USER_JWT);
			}

			// 같다면 유저비밀번호 변경
			Integer result = memberService.modifyMemberPwd(memberId, memberReq);
			return new BaseResponse<>(result);
		} catch (BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}

	//05 회원정보 - 닉네임 수정
	@PatchMapping("/{member-id}")
	public BaseResponse<Integer> patchMemberName(@PathVariable("member-id") int memberId,
												 @RequestBody MemberReq memberReq) {
		try {
			// jwt에서 id값 추출
			int memberIdByJwt = jwtService.getUserIdx();

			// 수정 요청한 id값이 본인이 아니라면 에러
			if(memberId != memberIdByJwt) {
				return new BaseResponse<>(INVALID_USER_JWT);
			}

			// 같다면 유저네임 변경
			Integer result = memberService.modifyMemberName(memberId, memberReq);
			return new BaseResponse<>(result);
		} catch (BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}

	//06 회원 탈퇴 API
	@DeleteMapping("/{member-id}")
	public BaseResponse<Integer> deleteMember(@PathVariable("member-id") int memberId) {
		try {
			//  X-ACCESS-TOKEN으로부터 JWT 얻은 후 ID 추출
			int memberIdByJwt = jwtService.getUserIdx();

			// 수정한 ID 값이 본인이 아니라면 에러
			if(memberId != memberIdByJwt) {
				return new BaseResponse<>(INVALID_USER_JWT);
			}

			Integer result = memberService.deleteMember(memberId);
			return new BaseResponse<>(result);
		} catch (BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}

	//07 회원 정보 조회 API
	@GetMapping("/{member-id}/info")
	public BaseResponse<MemberLevelRes> getMemberInfo(@PathVariable("member-id") int memberId) {
		try {
			int memberIdByJwt = jwtService.getUserIdx();

			if (memberId != memberIdByJwt) {
				return new BaseResponse<>(INVALID_USER_JWT);
			}

			MemberLevelRes memberLevelRes = memberProvider.getMemberInfo(memberId);
			return new BaseResponse<>(memberLevelRes);

		} catch(BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}


	//08 이메일 수신 동의 API
	@PostMapping("/{member-id}/email")
	public BaseResponse<Integer> postAcceptEmail(@PathVariable("member-id") int memberId,
												 @RequestBody MemberDTO memberDTO) {
		String emailStatus = memberDTO.getMailAccept();
		try {
			Integer result = memberService.modifyAcceptEmail(memberId, emailStatus);
			return new BaseResponse<>(result);
		} catch (BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}

	//09 SMS 수신 동의 API
	@ResponseBody
	@PostMapping("/{member-id}/sms")
	public BaseResponse<Integer> postAcceptSms(@PathVariable("member-id") int memberId,
											   @RequestBody MemberDTO MemberDTO) {
		String smsStatus = MemberDTO.getSmsAccept();
		try {
			Integer result = memberService.modifyAcceptSms(memberId, smsStatus);
			return new BaseResponse<>(result);
		} catch (BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}

	//

}

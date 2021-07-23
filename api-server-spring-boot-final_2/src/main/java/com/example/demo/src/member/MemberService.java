package com.example.demo.src.member;

import com.example.demo.config.BaseException;
import com.example.demo.config.secret.Secret;
import com.example.demo.src.member.MemberDAO;
import com.example.demo.src.member.MemberProvider;
import com.example.demo.src.member.model.MemberDTO;
import com.example.demo.src.member.model.MemberReq;
import com.example.demo.src.member.model.MemberRes;
import com.example.demo.src.user.model.PostUserReq;
import com.example.demo.src.user.model.PostUserRes;
import com.example.demo.utils.AES128;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
@Slf4j
public class MemberService {

	private final MemberDAO memberDAO;

	private final MemberProvider memberProvider;	// select

	private final JwtService jwtService;

	public MemberService(MemberDAO memberDAO, MemberProvider memberProvider, JwtService jwtService) {
		this.memberDAO = memberDAO;
		this.memberProvider = memberProvider;
		this.jwtService = jwtService;
	}

	public MemberRes joinMember(MemberDTO memberDTO) throws BaseException {
		// 이메일, 전화번호 중복체크
		if(memberProvider.checkMember(memberDTO.getEmail(), memberDTO.getPhoneNumber()) == 1) {
			throw new BaseException(POST_USERS_EXISTS_USER);
		}

		// 닉네임 중복체크
		if(memberProvider.checkName(memberDTO.getName()) == 1) {
			throw new BaseException(POST_USERS_EXISTS_NICKNAME);
		}

		// 비밀번호 암호화
		String pwd;
		try {
			pwd = new AES128(Secret.USER_INFO_PASSWORD_KEY).encrypt(memberDTO.getPassword());
			memberDTO.setPassword(pwd);
		} catch (Exception ignored) {
			throw new BaseException(PASSWORD_ENCRYPTION_ERROR);
		}

		// 비밀번호 저장
		try {
			int memberId = memberDAO.insertMember(memberDTO);

			// ID를 기준으로 MemberLevel 테이블 생성
			memberDAO.insertMemberLevel(memberId);

			// jwt 발급 (UserId 정보 이용)
			String myJwt = jwtService.createJwt(memberId);

			return MemberRes.builder()
				.jwt(myJwt)
				.id(memberId)
				.build();

		} catch(Exception exception) {
			throw new BaseException(DATABASE_ERROR);
		}
	}


	public Integer modifyMemberName(int memberId, MemberReq memberReq) throws BaseException {
		try {
			return memberDAO.updateMemberName(memberId, memberReq);
		} catch (Exception exception) {
			throw new BaseException(DATABASE_ERROR);
		}
	}


	public Integer modifyMemberPwd(int memberId, MemberReq memberReq) throws BaseException {

		// 비밀번호 암호화
		String pwd;
		try {
			pwd = new AES128(Secret.USER_INFO_PASSWORD_KEY).encrypt(memberReq.getPassword());
			memberReq.setPassword(pwd);
		} catch (Exception ignored) {
			throw new BaseException(PASSWORD_ENCRYPTION_ERROR);
		}

		// 암호화된 비밀번호 저장
		try {
			return memberDAO.updateMemberPwd(memberId, memberReq);
		} catch (Exception exception) {
			throw new BaseException(DATABASE_ERROR);
		}
	}


	public Integer modifyAcceptEmail(int memberId, String emailStatus) throws BaseException {

		// 상태 변환
		if (emailStatus.equals("Y")) {
			emailStatus = "N";
		} else {
			emailStatus = "Y";
		}

		try {
			return memberDAO.updateAcceptEmail(memberId, emailStatus);
		} catch(Exception exception) {
			throw new BaseException(DATABASE_ERROR);
		}

	}

	public Integer modifyAcceptSms(int memberId, String smsStatus) throws BaseException {
		// 상태 변환
		if (smsStatus.equals("Y")) {
			smsStatus = "N";
		} else {
			smsStatus = "Y";
		}

		try {
			return memberDAO.updateAcceptSms(memberId, smsStatus);
		} catch(Exception exception) {
			throw new BaseException(DATABASE_ERROR);
		}

	}


	// 회원 탈퇴 서비스
	public Integer deleteMember(int memberId) throws BaseException {
		try {
			return memberDAO.deleteMember(memberId);
		} catch (Exception exception) {
			throw new BaseException(DATABASE_ERROR);
		}

	}




}

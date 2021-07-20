package com.example.demo.src.member;

import com.example.demo.config.BaseException;
import com.example.demo.src.member.model.Member;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
@Slf4j
public class MemberService {

	@Autowired
	private final MemberDAO memberDAO;

	@Autowired
	private final MemberProvider memberProvider;	// select

	@Autowired
	private final JwtService jwtService;

	public MemberService(MemberDAO memberDAO, MemberProvider memberProvider, JwtService jwtService) {
		this.memberDAO = memberDAO;
		this.memberProvider = memberProvider;
		this.jwtService = jwtService;
	}

	public Integer joinMember(Member member) throws BaseException {
		// 이메일, 전화번호 중복체크
		if(memberProvider.checkMember(member.getEmail(), member.getPhoneNumber()) == 1) {
			throw new BaseException(POST_USERS_EXISTS_USER);
		}

		// 닉네임 중복체크
		if(memberProvider.checkName(member.getName()) == 1) {
			throw new BaseException(POST_USERS_EXISTS_NICKNAME);
		}


		// 닉네임 중복체크

		try {
			Integer memberId = memberDAO.insertMember(member);
			return memberId;
		} catch(Exception exception) {
			throw new BaseException(DATABASE_ERROR);
		}
	}

	public Integer modifyMemberName(int memberId, String name) throws BaseException {

		try {
			return memberDAO.modifyMemberName(memberId, name);
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
			return memberDAO.modifyAcceptEmail(memberId, emailStatus);
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
			return memberDAO.modifyAcceptSms(memberId, smsStatus);
		} catch(Exception exception) {
			throw new BaseException(DATABASE_ERROR);
		}

	}


	// POST
//	public PostUserRes createUser(PostUserReq postUserReq) throws BaseException {
//		// 중복된 값 있는지 체크 (SELECT 역할을 Provider)
//		if(userProvider.checkUser(postUserReq.getEmail(), postUserReq.getPhoneNumber()) == 1) {
//			throw new BaseException(POST_USERS_EXISTS_USER);
//		}
//
//		// 유저 생성
//		int userId = userDao.createUser(postUserReq);
//
//		// JWT 발급 생략
//	}

	//POST
//	public PostUserRes join(PostUserReq postUserReq) throws BaseException {
	//검증
//		userProvider.validateDuplicateUser(postUserReq);
//
	//회원가입
//		userDao.createUser(postUserReq);
//
//
//		return null;
//	}
}

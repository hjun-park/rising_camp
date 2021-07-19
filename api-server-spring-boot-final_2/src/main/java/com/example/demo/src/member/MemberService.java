package com.example.demo.src.member;

import com.example.demo.config.BaseException;
import com.example.demo.src.member.model.MemberDTO;
import com.example.demo.src.user.model.*;
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

	public Integer joinMember(MemberDTO memberDTO) throws BaseException {
		try {
			Integer memberId = memberDAO.insertMember(memberDTO);
			return memberId;
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

package com.example.demo.src.member;

import com.example.demo.config.BaseException;
import com.example.demo.src.user.model.*;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
@Slf4j
public class MemberProvider {

	@Autowired
	private final MemberDAO memberDao;

	@Autowired
	private final JwtService jwtService;

	public MemberProvider(MemberDAO memberDao, JwtService jwtService) {
		this.memberDao = memberDao;
		this.jwtService = jwtService;
	}



//	public GetOrderDetailRes getOrderDetail(int userId, int orderId) throws BaseException{
//		try {
//			userDao.getOrderDetail(userId, orderId);
//		} catch(Exception exception) {
//			throw new BaseException(DATABASE_ERROR);
//		}
//	}


//	public void validateDuplicateUser(PostUserReq postUserReq) throws BaseException{
//		 EXCEPTION
//		Object result = userDao.findByEmail(postUserReq.getEmail(), postUserReq.getPhoneNumber());
//
//		 중복되는 이메일이 있는 경우
//		if (result != null) {
//			throw new BaseException(POST_USERS_EXISTS_USER);
//		}
//	}

}

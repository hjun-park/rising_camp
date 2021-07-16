package com.example.demo.src.user;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.user.model.PatchUserReq;
import com.example.demo.src.user.model.PostUserReq;
import com.example.demo.src.user.model.PostUserRes;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
@Slf4j
public class UserService {

	@Autowired
	private final UserDao userDao;

	@Autowired
	private final UserProvider userProvider;	// select

	@Autowired
	private final JwtService jwtService;

	public UserService(UserDao userDao, UserProvider userProvider, JwtService jwtService) {
		this.userDao = userDao;
		this.userProvider = userProvider;
		this.jwtService = jwtService;
	}

	public void modifyUserName(PatchUserReq patchUserReq) throws BaseException {
		try {
			int result = userDao.modifyUserName(patchUserReq);
			if(result == 0) {
				throw new BaseException(MODIFY_FAIL_USERNAME);
			}
		} catch(BaseException exception) {
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

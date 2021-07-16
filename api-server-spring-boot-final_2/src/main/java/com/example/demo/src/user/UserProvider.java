package com.example.demo.src.user;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.user.model.PostLoginReq;
import com.example.demo.src.user.model.PostLoginRes;
import com.example.demo.src.user.model.PostUserReq;
import com.example.demo.src.user.model.User;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
@Slf4j
public class UserProvider {

	@Autowired
	private final UserDao userDao;

	@Autowired
	private final JwtService jwtService;

	public UserProvider(UserDao userDao, JwtService jwtService) {
		this.userDao = userDao;
		this.jwtService = jwtService;
	}

	public int checkUser(String email, String phoneNumber) throws BaseException {
		try {
			return userDao.checkUser(email, phoneNumber);
		} catch (Exception exception) {
			throw new BaseException(DATABASE_ERROR);
		}
	}

	public PostLoginRes login(PostLoginReq postLoginReq) throws BaseException {
		// 패스워드 가져오기
		User user = userDao.getPwd(postLoginReq);

		if(postLoginReq.getPassword().equals(user.getPassword())) {
			// 로그인 성공
			int userId = userDao.getPwd(postLoginReq).getId();
			return new PostLoginRes(userId);
		} else {
			// 로그인 실패
			throw new BaseException(FAILED_TO_LOGIN);
		}

	}

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

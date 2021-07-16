package com.example.demo.src.user;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public int checkEmail(String email) throws BaseException {
		try {
			return userDao.checkEmail(email);
		} catch (Exception exception) {
			throw new BaseException(DATABASE_ERROR);
		}
	}
}

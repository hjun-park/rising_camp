package com.example.demo.basket;

import com.example.demo.basket.model.BasketDTO;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.member.MemberDAO;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
@Slf4j
public class BasketProvider {

	@Autowired
	private final BasketDAO basketDAO;

	@Autowired
	private final JwtService jwtService;

	public BasketProvider(BasketDAO basketDAO, JwtService jwtService) {
		this.basketDAO = basketDAO;
		this.jwtService = jwtService;
	}

	public BasketDTO findBasket(int basketId) throws BaseException {
		try {
			return basketDAO.findBasketById(basketId);

		} catch (Exception exception) {
			throw new BaseException(DATABASE_ERROR);
		}

	}
}



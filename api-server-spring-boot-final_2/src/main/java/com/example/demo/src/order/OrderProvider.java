package com.example.demo.src.order;

import com.example.demo.config.BaseException;
import com.example.demo.src.user.model.PatchUserBasketReq;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;
import static com.example.demo.config.BaseResponseStatus.RESPONSE_ERROR;

@Service
public class OrderProvider {

	@Autowired
	private final OrderDao orderDao;

	@Autowired
	private final JwtService jwtService;

	public OrderProvider(OrderDao orderDao, JwtService jwtService) {
		this.orderDao = orderDao;
		this.jwtService = jwtService;
	}

}

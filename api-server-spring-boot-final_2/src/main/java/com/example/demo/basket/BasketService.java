package com.example.demo.basket;


import com.example.demo.src.member.MemberDAO;
import com.example.demo.src.member.MemberProvider;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BasketService {

	@Autowired
	private final BasketDAO basketDAO;

	@Autowired
	private final BasketProvider basketProvider;	// select

	@Autowired
	private final JwtService jwtService;

	public BasketService(BasketDAO basketDAO, BasketProvider basketProvider, JwtService jwtService) {
		this.basketDAO = basketDAO;
		this.basketProvider = basketProvider;
		this.jwtService = jwtService;
	}


}

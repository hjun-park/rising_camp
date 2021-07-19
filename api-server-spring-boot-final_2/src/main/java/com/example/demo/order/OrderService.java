package com.example.demo.order;


import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderService {

	@Autowired
	private final OrderDAO orderDAO;

	@Autowired
	private final OrderProvider orderProvider;	// select

	@Autowired
	private final JwtService jwtService;

	public OrderService(OrderDAO orderDAO, OrderProvider orderProvider, JwtService jwtService) {
		this.orderDAO = orderDAO;
		this.orderProvider = orderProvider;
		this.jwtService = jwtService;
	}


}

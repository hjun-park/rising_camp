package com.example.demo.order;

import com.example.demo.order.model.Order;
import com.example.demo.order.model.OrderItem;
import com.example.demo.config.BaseException;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
@Slf4j
public class OrderProvider {

	@Autowired
	private final OrderDAO orderDAO;

	@Autowired
	private final JwtService jwtService;

	public OrderProvider(OrderDAO orderDAO, JwtService jwtService) {
		this.orderDAO = orderDAO;
		this.jwtService = jwtService;
	}



//	public List<OrderItem> findOrderItems(int orderId) throws BaseException {
//		try {
//			return orderDAO.findOrderItems(orderId);
//		} catch (Exception exception) {
//			throw new BaseException(DATABASE_ERROR);
//		}
//
//	}

}



package com.example.demo.src.order;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.order.model.PatchOrderDetailReq;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
public class OrderService {

	@Autowired
	private final OrderDao orderDao;

	@Autowired
	private final OrderProvider orderProvider;

	@Autowired
	private final JwtService jwtService;


	public OrderService(OrderDao orderDao, OrderProvider orderProvider, JwtService jwtService) {
		this.orderDao = orderDao;
		this.orderProvider = orderProvider;
		this.jwtService = jwtService;
	}


	public void deleteOrderDetail(PatchOrderDetailReq patchOrderDetailReq) throws BaseException {
		try {
			int result = orderDao.deleteOrderDetail(patchOrderDetailReq);
			if (result == 0) {
				throw new BaseException(RESPONSE_ERROR);
			}
		} catch (Exception exception) {
			throw new BaseException(DATABASE_ERROR);
		}
	}
}

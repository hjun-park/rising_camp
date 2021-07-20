package com.example.demo.order;

import com.example.demo.order.model.Order;
import com.example.demo.order.model.OrderItem;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderController {
	@Autowired
	private final OrderProvider orderProvider;

	@Autowired
	private final OrderService orderService;

	@Autowired
	private final JwtService jwtService;

	public OrderController(OrderProvider orderProvider, OrderService orderService, JwtService jwtService) {
		this.orderProvider = orderProvider;
		this.orderService = orderService;
		this.jwtService = jwtService;
	}

	//16 주문하기
	@ResponseBody
	@PostMapping("/{member-id}")
	public BaseResponse<Integer> postOrder(@PathVariable("member-id") int memberId,
										@RequestBody Order order) {
		try {
			Integer orderResult = orderService.order(memberId, order);
			return new BaseResponse<>(orderResult);
		} catch (BaseException exception){
			return new BaseResponse<>(exception.getStatus());
		}
	}





}

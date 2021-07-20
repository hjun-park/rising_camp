package com.example.demo.order;

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

	// TODO: 장바구니는 인가된 사용자만 접근이 가능함
//	@ResponseBody
//	@GetMapping("/{order-id}/items")
//	public BaseResponse<List<OrderItem>> findOrderItems(@PathVariable("order-id") int orderId) {
//		try {
//			List<OrderItem> orderItem = orderProvider.findOrderItems(orderId);
//			return new BaseResponse<>(orderItem);
//		} catch (BaseException exception){
//			return new BaseResponse<>(exception.getStatus());
//		}
//	}





}

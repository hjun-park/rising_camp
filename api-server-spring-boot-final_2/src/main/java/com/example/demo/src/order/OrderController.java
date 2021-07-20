package com.example.demo.src.order;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.order.model.OrderRequestDTO;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderController {

	private final OrderProvider orderProvider;
	private final OrderService orderService;
	private final JwtService jwtService;

	public OrderController(OrderProvider orderProvider, OrderService orderService, JwtService jwtService) {
		this.orderProvider = orderProvider;
		this.orderService = orderService;
		this.jwtService = jwtService;
	}

	//ctrl alt l o
	//16 주문하기
	@PostMapping("/{member-id}")
	public BaseResponse<Integer> postOrder(@PathVariable("member-id") int memberId,
										   @RequestBody OrderRequestDTO orderRequestDTO) {
		try {
			Integer orderResult = orderService.order(memberId, orderRequestDTO);
			return new BaseResponse<>(orderResult);
		} catch (BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}

}

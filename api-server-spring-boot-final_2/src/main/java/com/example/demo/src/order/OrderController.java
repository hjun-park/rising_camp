package com.example.demo.src.order;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.order.model.OrderRes;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

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
//	@PostMapping("/{member-id}")
//	public BaseResponse<Integer> postOrder(@PathVariable("member-id") int memberId,
//										   @RequestBody OrderRequestDTO orderRequestDTO) {
//		try {
//			Integer orderResult = orderService.order(memberId, orderRequestDTO);
//			return new BaseResponse<>(orderResult);
//		} catch (BaseException exception) {
//			return new BaseResponse<>(exception.getStatus());
//		}
//	}

	//17 본인 주문 내역 조회 API
	@GetMapping("/{member-id}")
	public BaseResponse<List<OrderRes>> getOrders(@PathVariable("member-id") int memberId) {

		try {
			// JWT 검증
			int memberIdByJwt = jwtService.getUserIdx();
			if (memberId != memberIdByJwt) {
				return new BaseResponse<>(INVALID_USER_JWT);
			}

			List<OrderRes> orderReDTOS = orderProvider.getOrderHistory(memberId);
			return new BaseResponse<>(orderReDTOS);
		} catch (BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}

	}


}

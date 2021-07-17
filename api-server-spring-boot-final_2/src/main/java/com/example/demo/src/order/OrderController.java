package com.example.demo.src.order;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.order.model.Order;
import com.example.demo.src.order.model.PatchOrderDetailReq;
import com.example.demo.src.order.model.PostReviewReq;
import com.example.demo.src.order.model.PostReviewRes;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/users/{userId}/orders")
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

	//9
//	@ResponseBody
//	@GetMapping("/{userId}/orders/{orderId}")
//	public BaseResponse<GetOrderDetailRes> getOrderDetail(@PathVariable("userId") int userId,
//										 @PathVariable("orderId") int orderId) {
//		try {
//			GetOrderDetailRes getOrderDetailRes = userProvider.getOrderDetail(userId, orderId);
//			return new BaseResponse<>(getOrderDetailRes);
//		} catch(BaseException exception) {
//			return new BaseResponse<>(exception.getStatus());
//		}
//
//	}

	//10
	@ResponseBody
	@PatchMapping("/{orderId}")
	public BaseResponse<String> patchOrderDetail(@PathVariable("userId") int userId,
											   @PathVariable("orderId") int orderId,
												Order order) {

		try {
			PatchOrderDetailReq patchOrderDetailReq = new PatchOrderDetailReq(userId, orderId, order.getStatus());
			orderService.deleteOrderDetail(patchOrderDetailReq);

			String result = "";
			return new BaseResponse<>(result);
		} catch(BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}

//	//20
//	@ResponseBody
//	@PostMapping("/users/{userId}/orders/{orderId}/review")
//	public BaseResponse<PostReviewRes> postReview(@RequestBody PostReviewReq postReviewReq){
//		try{
//			PostReviewRes postReviewRes = orderService.postReview(postReviewReq);
//			return new BaseResponse<>(postReviewRes);
//		} catch (BaseException exception){
//			return new BaseResponse<>(exception.getStatus());
//		}
//	}

}

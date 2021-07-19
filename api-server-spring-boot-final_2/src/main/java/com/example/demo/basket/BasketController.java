package com.example.demo.basket;

import com.example.demo.basket.model.BasketDTO;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.member.MemberProvider;
import com.example.demo.src.member.MemberService;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/baskets")
@Slf4j
public class BasketController {
	@Autowired
	private final BasketProvider basketProvider;

	@Autowired
	private final BasketService basketService;

	@Autowired
	private final JwtService jwtService;

	public BasketController(BasketProvider basketProvider, BasketService basketService, JwtService jwtService) {
		this.basketProvider = basketProvider;
		this.basketService = basketService;
		this.jwtService = jwtService;
	}

	// TODO: 장바구니는 인가된 사용자만 접근이 가능함
	@ResponseBody
	@GetMapping("/{basket-id}")
	public BaseResponse<BasketDTO> findBasket(@RequestParam("basket-id") int basketId) {
		try {
			BasketDTO basketDTO = basketProvider.findBasket(basketId);
			return new BaseResponse<>(basketDTO);
		} catch (BaseException exception){
			return new BaseResponse<>(exception.getStatus());
		}
	}



}

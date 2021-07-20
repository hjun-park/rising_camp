package com.example.demo.cart;

import com.example.demo.cart.model.Cart;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.member.MemberProvider;
import com.example.demo.src.member.MemberService;
import com.example.demo.src.member.model.MemberCartDTO;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@Slf4j
public class CartController {

	@Autowired
	private final CartProvider cartProvider;

	@Autowired
	private final CartService cartService;

	@Autowired
	private final JwtService jwtService;

	public CartController(CartProvider cartProvider, CartService cartService, JwtService jwtService) {
		this.cartProvider = cartProvider;
		this.cartService = cartService;
		this.jwtService = jwtService;
	}

	//11 유저 장바구니 조회
	@ResponseBody
	@GetMapping("/{member-id}")
	public BaseResponse<List<MemberCartDTO>> getCart(@PathVariable("member-id") int memberId) {

		try {
			List<MemberCartDTO> carts = cartProvider.findCart(memberId);
			return new BaseResponse<>(carts);
		} catch (BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}

	}


	//12 유저 장바구니 등록
	@ResponseBody
	@PostMapping("/{member-id}")
	public BaseResponse<Integer> postCart(@PathVariable("member-id") int memberId,
										  @RequestBody Cart cart) {
		try {
			Integer cartId = cartService.registerCart(memberId, cart);
			return new BaseResponse<>(cartId);
		} catch (BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}

	}

}


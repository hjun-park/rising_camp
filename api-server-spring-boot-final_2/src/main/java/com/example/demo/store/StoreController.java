package com.example.demo.store;

import com.example.demo.cart.CartProvider;
import com.example.demo.cart.CartService;
import com.example.demo.cart.model.Cart;
import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.member.model.MemberCartDTO;
import com.example.demo.store.model.Store;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/stores")
@Slf4j
public class StoreController {

	@Autowired
	private final StoreProvider storeProvider;

	@Autowired
	private final StoreService storeService;

	@Autowired
	private final JwtService jwtService;

	public StoreController(StoreProvider storeProvider, StoreService storeService, JwtService jwtService) {
		this.storeProvider = storeProvider;
		this.storeService = storeService;
		this.jwtService = jwtService;
	}

	//27 가게 조회
	@ResponseBody
	@GetMapping("/{store-id}")
	public BaseResponse<Store> getStore(@PathVariable("store-id") int storeId) {
		try {
			Store store = storeProvider.findStore(storeId);
			return new BaseResponse<>(store);
		} catch (BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}

	//28 가게 등록
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


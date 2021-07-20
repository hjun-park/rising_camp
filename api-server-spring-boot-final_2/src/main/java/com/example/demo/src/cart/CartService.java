package com.example.demo.src.cart;

import com.example.demo.src.cart.model.Cart;
import com.example.demo.config.BaseException;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

@Slf4j
@Service
public class CartService {

	@Autowired
	private final CartProvider cartProvider;

	@Autowired
	private final CartDAO cartDAO;

	@Autowired
	private final JwtService jwtService;

	public CartService(CartProvider cartProvider, CartDAO cartDAO, JwtService jwtService) {
		this.cartProvider = cartProvider;
		this.cartDAO = cartDAO;
		this.jwtService = jwtService;
	}

	public Integer registerCart(int memberId, Cart cart) throws BaseException {
		try {
			// 카트 생성 전 가게 ID 확인
			Cart myCart = cartProvider.peekCart(memberId);

			// 등록하려는 가게와 이전 가게가 서로 다르다면 에러
			if (cart.getStoreId() != myCart.getStoreId()) {
				throw new BaseException(MISMATCH_STORE_MENU);
			}

			// 검증 되었다면 상품 등록
			Integer cartId = cartDAO.insertCart(memberId, cart);
			return cartId;
		} catch (Exception exception) {
			throw new BaseException(DATABASE_ERROR);
		}
	}

	public int modifyCart(int memberId, int cartId, Cart cart) throws BaseException {
		try {
			int amount = cart.getAmount();
			// 상품 수량 수정
			return cartDAO.modifyCartAmount(memberId, cartId, amount);
		} catch (Exception exception) {
			throw new BaseException(DATABASE_ERROR);
		}
	}


	public int deleteCarts(int memberId) throws BaseException{
		try {
			int result = cartDAO.deleteCarts(memberId);
			if (result == 0) {
				throw new BaseException(NOTHING_TO_DELETE);
			}
			return result;
		} catch (Exception exception) {
			throw new BaseException(DATABASE_ERROR);
		}
	}

	public Integer deleteCart(int memberId, int cartId) throws BaseException{
		try {
			int result = cartDAO.deleteCart(memberId, cartId);
			if (result == 0) {
				throw new BaseException(NOTHING_TO_DELETE);
			}
			return result;
		} catch (Exception exception) {
			throw new BaseException(DATABASE_ERROR);
		}
	}
}

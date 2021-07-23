package com.example.demo.src.cart;

import com.example.demo.src.cart.model.Cart;
import com.example.demo.config.BaseException;
import com.example.demo.src.menu.MenuDAO;
import com.example.demo.src.menu.model.Menu;
import com.example.demo.src.member.model.MemberCart;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Slf4j
@Service
public class CartProvider {

	@Autowired
	private final CartDAO cartDAO;

	@Autowired
	private final MenuDAO menuDAO;

	@Autowired
	private final JwtService jwtService;

	public CartProvider(CartDAO cartDAO, MenuDAO menuDAO, JwtService jwtService) {
		this.cartDAO = cartDAO;
		this.menuDAO = menuDAO;
		this.jwtService = jwtService;
	}

	public List<MemberCart> findCart(int memberId) throws BaseException {
		try {
			List<Cart> carts = cartDAO.findCartByMemberId(memberId);

			// foreach , map -> return type
			return carts.stream()
				.map((cart) -> {
					Menu findMenu = menuDAO.findMenuById(cart.getMenuId());
					return new MemberCart(findMenu.getName(), findMenu.getPrice(), cart.getAmount());
				})
				.collect(Collectors.toList());

//			carts.forEach(cart -> {
//				MemberCartDTO memberCartDTO = new MemberCartDTO();
//				Menu menu = menuDAO.findMenuById(cart.getMenuId());
//				memberCartDTO.setName(menu.getName());
//				memberCartDTO.setPrice(menu.getPrice());
//				memberCartDTO.setAmount(cart.getAmount());
//				memberCarts.add(memberCartDTO);
//			});
//
//			return memberCarts;

		} catch (Exception exception) {
			throw new BaseException(DATABASE_ERROR);
		}
	}

	// 최근 멤버의 카트 정보 추출
	public Cart peekCart(int memberId) throws BaseException {
		try {
			Cart peekCart = cartDAO.peekByMemberId(memberId);
			return peekCart;
		} catch (Exception exception) {
			throw new BaseException(DATABASE_ERROR);
		}
	}
}



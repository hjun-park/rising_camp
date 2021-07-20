package com.example.demo.cart;

import com.example.demo.cart.model.Cart;
import com.example.demo.config.BaseException;
import com.example.demo.menu.MenuDAO;
import com.example.demo.menu.model.Menu;
import com.example.demo.src.member.MemberDAO;
import com.example.demo.src.member.model.Member;
import com.example.demo.src.member.model.MemberCartDTO;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;
import static com.example.demo.config.BaseResponseStatus.POST_USERS_EXISTS_USER;

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

	public List<MemberCartDTO> findCart(int memberId) throws BaseException {
		try {
			// 검증용
			List<MemberCartDTO> memberCarts = new ArrayList<>();

			List<Cart> carts = cartDAO.findCartByMemberId(memberId);
			carts.forEach(cart -> {
				MemberCartDTO memberCartDTO = new MemberCartDTO();
				Menu menu = menuDAO.findMenuById(cart.getMenuId());
				memberCartDTO.setName(menu.getName());
				memberCartDTO.setPrice(menu.getPrice());
				memberCartDTO.setAmount(cart.getAmount());
				memberCarts.add(memberCartDTO);
			});

			return memberCarts;

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



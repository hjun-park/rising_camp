package com.example.demo.location;

import com.example.demo.cart.CartDAO;
import com.example.demo.cart.CartProvider;
import com.example.demo.cart.model.Cart;
import com.example.demo.config.BaseException;
import com.example.demo.location.model.StorePolicy;
import com.example.demo.menu.model.Menu;
import com.example.demo.src.member.model.MemberCartDTO;
import com.example.demo.store.StoreDAO;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Slf4j
@Service
public class LocationProvider {

	@Autowired
	private final LocationDAO locationDAO;

	@Autowired
	private final StoreDAO storeDAO;

	@Autowired
	private final JwtService jwtService;

	public LocationProvider(LocationDAO locationDAO, StoreDAO storeDAO, JwtService jwtService) {
		this.locationDAO = locationDAO;
		this.storeDAO = storeDAO;
		this.jwtService = jwtService;
	}

	public List<StorePolicy> findZones(int storeId) {
		try {
			// 가게 정보 추출


			List<MemberCartDTO> memberCarts = new ArrayList<>();

			List<Cart> carts = storeDAO.findCartByMemberId(memberId);
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
}

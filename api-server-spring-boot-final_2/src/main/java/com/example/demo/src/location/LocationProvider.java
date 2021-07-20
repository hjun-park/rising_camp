package com.example.demo.src.location;

import com.example.demo.src.store.StoreDAO;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

//	public List<StorePolicy> findZones(int storeId) {
//		try {
//			// 가게 정보 추출
//
//
//			List<MemberCartDTO> memberCarts = new ArrayList<>();
//
//			List<Cart> carts = storeDAO.findCartByMemberId(memberId);
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
//
//		} catch (Exception exception) {
//			throw new BaseException(DATABASE_ERROR);
//		}
//	}
}

package com.example.demo.src.order;


import com.example.demo.config.BaseException;
import com.example.demo.src.order.model.OrderRequestDTO;
import com.example.demo.src.member.MemberDAO;
import com.example.demo.src.member.model.Member;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
@Slf4j
public class OrderService {

	private final OrderDAO orderDAO;

	private final MemberDAO memberDAO;

	private final AddressDAO addressDAO;

	private final OrderProvider orderProvider;	// select

	private final JwtService jwtService;

	public OrderService(OrderDAO orderDAO, MemberDAO memberDAO, AddressDAO addressDAO, OrderProvider orderProvider, JwtService jwtService) {
		this.orderDAO = orderDAO;
		this.memberDAO = memberDAO;
		this.addressDAO = addressDAO;
		this.orderProvider = orderProvider;
		this.jwtService = jwtService;
	}

//	public int order(int memberId, OrderRequestDTO orderRequestDTO) throws BaseException {
//		try {
//			// 유저 주소 추출
//			Member member = memberDAO.findMemberById(memberId);
//			orderRequestDTO.setAddressBuildingNum(member.getAddressBuildingNum());
//
//			// 배달료 산정 ( tips )
//			member.getDistrictCode();
//
//
//
//			int orderResult = orderDAO.order(memberId, orderRequestDTO);
//			return orderResult;
//		} catch(Exception exception) {
//			throw new BaseException(DATABASE_ERROR);
//		}
//	}
//
//
//	private int calcTips(int basicTip, int additionalTip, int districtCode) {
//		return
//	}


}

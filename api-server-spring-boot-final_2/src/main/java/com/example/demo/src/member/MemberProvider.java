package com.example.demo.src.member;

import com.example.demo.config.secret.Secret;
import com.example.demo.src.cart.CartDAO;
import com.example.demo.config.BaseException;
import com.example.demo.src.member.model.*;
import com.example.demo.src.menu.MenuDAO;
import com.example.demo.utils.AES128;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
@Slf4j
public class MemberProvider {

	private final MemberDAO memberDAO;
	private final MenuDAO menuDAO;
	private final CartDAO cartDAO;
	private final JwtService jwtService;

	public MemberProvider(MemberDAO memberDAO, MenuDAO menuDAO, CartDAO cartDAO, JwtService jwtService) {
		this.memberDAO = memberDAO;
		this.menuDAO = menuDAO;
		this.cartDAO = cartDAO;
		this.jwtService = jwtService;
	}

	// 사용자 멤버 등급 출력 - jwt 검증 필요
	public MemberLevelRes getMemberInfo(int memberId) throws BaseException {

		// 1. 멤버 ID를 이용하여 이름 가져오기
		log.info("##### 0");
		MemberDTO memberDTO = memberDAO.findMemberById(memberId);
		log.info("##### 1");
		// 2. 멤버 ID를 이용하여 등급 이름 가져오기
		MemberLevelDTO memberLevelDTO = memberDAO.findLevelById(memberId);
		log.info("##### 2");
		// 3. 조합하여 내보내기
		return MemberLevelRes.builder()
			.name(memberDTO.getName())
			.membershipName(memberLevelDTO.getMembershipName())
			.build();
	}


	public Integer checkMember(String email, String phoneNumber) {
		return memberDAO.isDuplicatedMember(email, phoneNumber);
	}

	public Integer checkName(String name) {
		return memberDAO.isDuplicatedName(name);
	}

	public MemberRes login(MemberReq memberReq) throws BaseException {
		// 정보 받아오기
		MemberDTO memberDTO = memberDAO.findMemberBy("email", memberReq);

		String password;
		try { // 복호화
			password = new AES128(Secret.USER_INFO_PASSWORD_KEY).decrypt(memberDTO.getPassword());
		} catch (Exception ignored) {
			throw new BaseException(PASSWORD_DECRYPTION_ERROR);
		}

		// ****궁금증_1: 서비스에서 반환할 때 VO 객체로 반환해야하나? Integer로 반환하면 안 되나
		// 복호화 된 패스워드 확인
		if (memberReq.getPassword().equals(password)) {
			int memberId = memberDTO.getId();
			String myJwt = jwtService.createJwt(memberId);
			return MemberRes.builder()
				.jwt(myJwt)
				.id(memberId)
				.build();

		} else {
			throw new BaseException(FAILED_TO_LOGIN);
		}

	}

//	public List<MemberCartDTO> findMemberCart(int memberId) throws BaseException {
//		try {
//			// 검증용
//			Member member = memberDAO.findMemberById(memberId);
//
//			if (member == null) {
//				throw new BaseException(POST_USERS_EXISTS_USER);
//			}
//			List<MemberCartDTO> memberCarts = new ArrayList<>();
//
//			List<Cart> carts = cartDAO.findCartByMemberId(memberId);
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
//
//	}


//	public GetOrderDetailRes getOrderDetail(int userId, int orderId) throws BaseException{
//		try {
//			userDao.getOrderDetail(userId, orderId);
//		} catch(Exception exception) {
//			throw new BaseException(DATABASE_ERROR);
//		}
//	}


//	public void validateDuplicateUser(PostUserReq postUserReq) throws BaseException{
//		 EXCEPTION
//		Object result = userDao.findByEmail(postUserReq.getEmail(), postUserReq.getPhoneNumber());
//
//		 중복되는 이메일이 있는 경우
//		if (result != null) {
//			throw new BaseException(POST_USERS_EXISTS_USER);
//		}
//	}

}

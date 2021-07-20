package com.example.demo.src.member;

import com.example.demo.src.cart.CartDAO;
import com.example.demo.config.BaseException;
import com.example.demo.src.menu.MenuDAO;
import com.example.demo.src.member.model.Member;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
@Slf4j
public class MemberProvider {

	@Autowired
	private final MemberDAO memberDAO;

	@Autowired
	private final MenuDAO menuDAO;

	@Autowired
	private final CartDAO cartDAO;

	@Autowired
	private final JwtService jwtService;

	public MemberProvider(MemberDAO memberDAO, MenuDAO menuDAO, CartDAO cartDAO, JwtService jwtService) {
		this.memberDAO = memberDAO;
		this.menuDAO = menuDAO;
		this.cartDAO = cartDAO;
		this.jwtService = jwtService;
	}

	public Integer checkMember(String email, String phoneNumber) {
		return memberDAO.isDuplicatedMember(email, phoneNumber);
	}

	public Integer checkName(String name) {
		return memberDAO.isDuplicatedName(name);
	}

	public Member login(String email, String password) throws BaseException {
		//패스워드 암호화 진행

		//id, 패스워드 확인
		try {
			Member member = memberDAO.findByIdPassword(email, password);
			if (member == null) {
				throw new BaseException(POST_USERS_LOGIN_INVALID);
			}
			return member;
		} catch (Exception exception) {
			throw new BaseException(DATABASE_ERROR);
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

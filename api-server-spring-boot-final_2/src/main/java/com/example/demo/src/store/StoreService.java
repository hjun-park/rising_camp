package com.example.demo.src.store;

import com.example.demo.config.BaseException;
import com.example.demo.src.store.model.Store;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Slf4j
@Service
public class StoreService {

	@Autowired
	private final StoreProvider storeProvider;

	@Autowired
	private final StoreDAO storeDAO;

	@Autowired
	private final JwtService jwtService;

	public StoreService(StoreProvider storeProvider, StoreDAO storeDAO, JwtService jwtService) {
		this.storeProvider = storeProvider;
		this.storeDAO = storeDAO;
		this.jwtService = jwtService;
	}

	public Integer registerStore(Store store) throws BaseException {
		try {
			// 카트 생성 전 가게 ID 확인
			// myCart = cartProvider.peekCart(memberId);

			// 등록하려는 가게와 이전 가게가 서로 다르다면 에러
//			if (cart.getStoreId() != myCart.getStoreId()) {
//				throw new BaseException(MISMATCH_STORE_MENU);
//			}

			// 검증 되었다면 상품 등록
			Integer resultId = storeDAO.insertStore(store);
			return resultId;
		} catch (Exception exception) {
			throw new BaseException(DATABASE_ERROR);
		}
	}
}

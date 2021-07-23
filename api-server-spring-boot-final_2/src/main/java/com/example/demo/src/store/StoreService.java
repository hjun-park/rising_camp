package com.example.demo.src.store;

import com.example.demo.config.BaseException;
import com.example.demo.src.store.model.Store;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;
import static com.example.demo.config.BaseResponseStatus.NOTHING_TO_DELETE;

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
		// 1. 카테고리 메뉴 이름 유효성 검사
		try {
			// 검증 되었다면 상품 등록
			Integer resultId = storeDAO.insertStore(store);

			// STORE_HOURS를 통해서 파싱해서 넣어줘야함
			return resultId;
		} catch (Exception exception) {
			throw new BaseException(DATABASE_ERROR);
		}
	}

	public Integer deleteStore(int storeId) throws BaseException {
		try {
			int result = storeDAO.deleteStoreById(storeId);
			if (result == 0) {
				throw new BaseException(NOTHING_TO_DELETE);
			}
			return result;
		} catch (Exception exception) {
			throw new BaseException(DATABASE_ERROR);
		}
	}

}

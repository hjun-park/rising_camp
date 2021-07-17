package com.example.demo.src.store;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.store.model.GetMenuRes;
import com.example.demo.src.store.model.GetStoreInfoRes;
import com.example.demo.src.store.model.GetStoreRes;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
@Slf4j
public class StoreProvider {

	@Autowired
	private final StoreDao storeDao;

	@Autowired
	private final JwtService jwtService;

	public StoreProvider(StoreDao storeDao, JwtService jwtService) {
		this.storeDao = storeDao;
		this.jwtService = jwtService;
	}


	public GetStoreRes getStore(int storeId) throws BaseException {
		try {
			GetStoreRes getStoreRes = storeDao.getStore(storeId);
			return getStoreRes;
		} catch (Exception exception) {
			throw new BaseException(DATABASE_ERROR);
		}

	}

	public GetStoreInfoRes getStoreInfo(int storeId) throws BaseException {
		try {
			GetStoreInfoRes getStoreInfoRes = storeDao.getStoreInfo(storeId);
			return getStoreInfoRes;
		} catch(Exception exception) {
			throw new BaseException(DATABASE_ERROR);
		}
	}

	public GetMenuRes getMenu(int storeId, int menuId) throws BaseException{
		try {
			GetMenuRes getMenuRes = storeDao.getMenu(storeId, menuId);
			return getMenuRes;
		} catch(Exception exception) {
			throw new BaseException(DATABASE_ERROR);
		}
	}
}

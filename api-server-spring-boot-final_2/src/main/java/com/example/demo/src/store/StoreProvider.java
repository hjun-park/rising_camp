package com.example.demo.src.store;

import com.example.demo.config.BaseException;
import com.example.demo.src.store.model.StoreDTO;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

@Slf4j
@Service
public class StoreProvider {

	@Autowired
	private final StoreDAO storeDAO;

	@Autowired
	private final JwtService jwtService;

	public StoreProvider(StoreDAO storeDAO, JwtService jwtService) {
		this.storeDAO = storeDAO;
		this.jwtService = jwtService;
	}

	public StoreDTO findStore(int storeId) throws BaseException {
		try {
			return storeDAO.findStoreById(storeId);
		} catch (Exception exception) {
			throw new BaseException(DATABASE_ERROR);
		}
	}
}

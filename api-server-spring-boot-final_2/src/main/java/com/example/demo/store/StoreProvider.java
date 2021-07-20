package com.example.demo.store;


import com.example.demo.cart.CartDAO;
import com.example.demo.cart.CartProvider;
import com.example.demo.config.BaseException;
import com.example.demo.store.model.Store;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


	public Store findStore(int storeId) throws BaseException {
		
	}
}

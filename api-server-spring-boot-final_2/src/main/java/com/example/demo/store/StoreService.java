package com.example.demo.store;

import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

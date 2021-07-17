package com.example.demo.src.store;

import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StoreService {

	@Autowired
	private final StoreDao storeDao;

	@Autowired
	private final StoreProvider storeProvider;

	@Autowired
	private final JwtService jwtService;

	public StoreService(StoreDao storeDao, StoreProvider storeProvider, JwtService jwtService) {
		this.storeDao = storeDao;
		this.storeProvider = storeProvider;
		this.jwtService = jwtService;
	}



}

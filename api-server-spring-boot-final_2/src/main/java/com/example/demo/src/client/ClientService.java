package com.example.demo.src.client;

import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class ClientService {

	private final ClientDAO clientDAO;
	private final JwtService jwtService;

	public ClientService(ClientDAO clientDAO, JwtService jwtService) {
		this.clientDAO = clientDAO;
		this.jwtService = jwtService;
	}



}

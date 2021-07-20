package com.example.demo.src.menu;

import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MenuService {

	@Autowired
	private final MenuProvider menuProvider;

	@Autowired
	private final MenuDAO menuDAO;

	@Autowired
	private final JwtService jwtService;

	public MenuService(MenuProvider menuProvider, MenuDAO menuDAO, JwtService jwtService) {
		this.menuProvider = menuProvider;
		this.menuDAO = menuDAO;
		this.jwtService = jwtService;
	}
}

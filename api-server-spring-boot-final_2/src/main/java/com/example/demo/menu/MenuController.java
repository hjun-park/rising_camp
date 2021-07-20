package com.example.demo.menu;

import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/stores/{store-id}")
public class MenuController {

	@Autowired
	private final MenuProvider menuProvider;
	@Autowired
	private final MenuService menuService;
	@Autowired
	private final JwtService jwtService;

	public MenuController(MenuProvider menuProvider, MenuService menuService, JwtService jwtService) {
		this.menuProvider = menuProvider;
		this.menuService = menuService;
		this.jwtService = jwtService;
	}
}


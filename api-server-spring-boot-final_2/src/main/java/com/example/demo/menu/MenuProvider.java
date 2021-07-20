package com.example.demo.menu;

import com.example.demo.src.review.ReviewDao;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MenuProvider {

	@Autowired
	private final MenuDAO menuDAO;

	@Autowired
	private final JwtService jwtService;

	public MenuProvider(MenuDAO menuDAO, JwtService jwtService) {
		this.menuDAO = menuDAO;
		this.jwtService = jwtService;
	}
}

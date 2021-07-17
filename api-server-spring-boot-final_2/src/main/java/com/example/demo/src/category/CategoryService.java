package com.example.demo.src.category;

import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CategoryService {

	@Autowired
	private final CategoryDao categoryDao;

	@Autowired
	private final CategoryProvider categoryProvider;

	@Autowired
	private final JwtService jwtService;

	public CategoryService(CategoryDao categoryDao, CategoryProvider categoryProvider, JwtService jwtService) {
		this.categoryDao = categoryDao;
		this.categoryProvider = categoryProvider;
		this.jwtService = jwtService;
	}
}

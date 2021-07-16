package com.example.demo.src.review;

import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ReviewProvider {

	@Autowired
	private final ReviewDao reviewDao;

	@Autowired
	private final JwtService jwtService;

	public ReviewProvider(ReviewDao reviewDao, JwtService jwtService) {
		this.reviewDao = reviewDao;
		this.jwtService = jwtService;
	}
}

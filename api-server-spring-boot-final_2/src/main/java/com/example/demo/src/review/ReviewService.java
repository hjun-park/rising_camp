package com.example.demo.src.review;

import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;

public class ReviewService {

	@Autowired
	private final ReviewDao reviewDao;

	@Autowired
	private final ReviewProvider reviewProvider;

	@Autowired
	private final JwtService jwtService;

	public ReviewService(ReviewDao reviewDao, ReviewProvider reviewProvider, JwtService jwtService) {
		this.reviewDao = reviewDao;
		this.reviewProvider = reviewProvider;
		this.jwtService = jwtService;
	}
}

package com.example.demo.src.review;

import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/users/{userId}")
public class ReviewController {

	@Autowired
	private final ReviewProvider reviewProvider;

	@Autowired
	private final ReviewService reviewServicee;

	@Autowired
	private final JwtService jwtService;

	public ReviewController(ReviewProvider reviewProvider, ReviewService reviewServicee, JwtService jwtService) {
		this.reviewProvider = reviewProvider;
		this.reviewServicee = reviewServicee;
		this.jwtService = jwtService;
	}

	//11
	


}

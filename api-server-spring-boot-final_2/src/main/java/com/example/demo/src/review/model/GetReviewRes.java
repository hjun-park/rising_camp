package com.example.demo.src.review.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetReviewRes {
	private int reviewId;
	private String content;
	private double rating;
	private String reviewImageUrl;



}

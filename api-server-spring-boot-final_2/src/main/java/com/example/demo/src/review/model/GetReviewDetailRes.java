package com.example.demo.src.review.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class GetReviewDetailRes {
//	id, content, rating, reviewImageUrl
	private int userId;
	private int count;
	private double rating;
	private String reviewImageUrl;
	private String content;

}

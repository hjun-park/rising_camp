package com.example.demo.src.review.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Review {
	private int id;
	private int storeId;
	private int userId;
	private int storeBasketId;

	private double rating;
	private String reviewImageUrl;
	private String content;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	private String status;

}

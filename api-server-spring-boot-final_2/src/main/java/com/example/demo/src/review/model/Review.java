package com.example.demo.src.review.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class Review {
	public enum Status {
		Used, Deleted;
	}

	private int id;
	private int storeId;
	private int memberId;
	private int orderId;

	private String content;
	private double rating;
	private String reviewImageUrl;

	private Status status;


}

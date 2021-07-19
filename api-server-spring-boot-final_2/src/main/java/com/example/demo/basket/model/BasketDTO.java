package com.example.demo.basket.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
@ToString
public class BasketDTO {
	private int id;
	private int memberId;
	private int storeId;
	private int orderItemId;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public BasketDTO() {
	}
}

package com.example.demo.src.order.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {
	private enum Status {
		Used, Deleted;
	}

	private int id;
	private int orderId;
	private int amount;
	private int menuId;
	private Status status;

	@Builder
	public OrderItem(int id, int orderId, int amount, int menuId) {
		this.id = id;
		this.orderId = orderId;
		this.amount = amount;
		this.menuId = menuId;
	}
}

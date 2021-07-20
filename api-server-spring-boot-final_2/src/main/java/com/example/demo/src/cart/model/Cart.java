package com.example.demo.src.cart.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter @ToString
@AllArgsConstructor
public class Cart {
	public enum Status {
		Used, Deleted;
	}

	private int id;
	private int memberId;
	private int menuId;
	private int amount;
	private int storeId;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	private Status status;

	public Cart(int id, int memberId, int menuId, int amount, int storeId) {
		this.id = id;
		this.memberId = memberId;
		this.menuId = menuId;
		this.amount = amount;
		this.storeId = storeId;

	}

	public Cart() {
	}
}

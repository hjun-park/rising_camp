package com.example.demo.src.cart.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "CART")
public class Cart {
	public enum Status {
		Used, Deleted;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long memberId;
	private Long menuId;
	private Long storeId;

	private int amount;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	private Status status;

	public Cart(Long id, Long memberId, Long menuId, int amount, Long storeId) {
		this.id = id;
		this.memberId = memberId;
		this.menuId = menuId;
		this.amount = amount;
		this.storeId = storeId;
	}

	public Cart() {
	}
}

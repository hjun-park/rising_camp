package com.example.demo.order.model;

import com.example.demo.menu.model.MenuDTO;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
@ToString
public class OrderItemDTO {
	private enum Status {
		Used, Deleted;
	}
	private int id;
	private int orderItem;
	private int amount;
	private int menuId;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private Status status;

	// 1:1
	private MenuDTO menuDTO;

	public OrderItemDTO() {
	}

	@Builder
	public OrderItemDTO (
		String name,
		int amount,
		int price
	) {
		this.amount = amount;
		this.menuDTO = MenuDTO.builder()
			.name(name)
			.price(price)
			.build();
	}



}

package com.example.demo.order.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
@ToString
public class OrderItem {
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

	public OrderItem() {
	}




//	@Builder
//	public OrderItemDTO (
//		String name,
//		int amount,
//		int price
//	) {
//		this.amount = amount;
//		this.menuDTO = MenuDTO.builder()
//			.name(name)
//			.price(price)
//			.build();
//	}



}

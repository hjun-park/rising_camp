package com.example.demo.src.order.model;

import jdk.internal.util.Preconditions;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

import static lombok.Lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderRes {

	private LocalDateTime date;
	private String orderStatus;
	private String storeName;
	private String menuName;	// 첫 메뉴 이름만
	private int menuCount;

	@Builder
	public OrderRes(LocalDateTime date, String orderStatus, String storeName, String menuName, int menuCount) {
		this.date = checkNotNull(date, "date");
		this.orderStatus = checkNotNull(orderStatus, "orderStatus");
		this.storeName = checkNotNull(storeName, "storeName");
		this.menuName = checkNotNull(menuName, "menuName");	// 대표 메뉴 이름
		this.menuCount = checkNotNull(menuCount, "menuCount");	// 총 메뉴 주문 갯수
	}
}

package com.example.demo.src.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Order {

	private int id;
	private int storeId;
	private int storeBasketId;
	private int userId;

	private String addressBuildingNum;
	private String addressDetail;
	private int tips;

	private String status;
	private String storeRequest;
	private String riderRequest;

	@Nullable
	private int riderId;
	private LocalDateTime orderTime;
	private LocalDateTime arriveTime;
	private LocalDateTime updatedAt;

}

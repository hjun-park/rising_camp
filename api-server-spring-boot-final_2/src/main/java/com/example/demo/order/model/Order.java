package com.example.demo.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Order {
	private enum Status {
		OrderComplete, DeliveryComplete, Cancel;
	}

	private int id;
	private int storeId;
	private int memberId;
	private String addressBuildingNum;
	private String addressDetail;
	private int tips;
	private Status status;
	private String storeRequest;
	private String riderRequest;
	private int riderId;
	private LocalDateTime orderTime;
	private LocalDateTime updatedAt;
	private LocalDateTime arriveTime;


	public Order() {
	}
}

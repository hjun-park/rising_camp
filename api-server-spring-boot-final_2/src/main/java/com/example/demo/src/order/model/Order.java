package com.example.demo.src.order.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
	public enum Status {
		Order, Delivery, Cancel, Complete;
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

	@Builder
	public Order(int id, int storeId, int memberId, String addressBuildingNum, String addressDetail, int tips, Status status, String storeRequest, String riderRequest, int riderId, LocalDateTime orderTime) {
		this.id = id;
		this.storeId = storeId;
		this.memberId = memberId;
		this.addressBuildingNum = addressBuildingNum;
		this.addressDetail = addressDetail;
		this.tips = tips;
		this.status = status;
		this.storeRequest = storeRequest;
		this.riderRequest = riderRequest;
		this.riderId = riderId;
		this.orderTime = orderTime;
	}
}

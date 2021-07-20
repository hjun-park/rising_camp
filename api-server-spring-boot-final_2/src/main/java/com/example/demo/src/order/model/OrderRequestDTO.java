package com.example.demo.src.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class OrderRequestDTO {
	public enum Status {
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

	public OrderRequestDTO(int id, int storeId, int memberId, String addressBuildingNum, String addressDetail, int tips, Status status, String storeRequest, String riderRequest, int riderId, LocalDateTime orderTime, LocalDateTime updatedAt, LocalDateTime arriveTime) {
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
		this.updatedAt = updatedAt;
		this.arriveTime = arriveTime;
	}
}

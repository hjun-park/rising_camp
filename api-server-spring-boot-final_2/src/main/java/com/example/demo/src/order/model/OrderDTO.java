package com.example.demo.src.order.model;

import java.time.LocalDateTime;

public class OrderDTO {
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
}

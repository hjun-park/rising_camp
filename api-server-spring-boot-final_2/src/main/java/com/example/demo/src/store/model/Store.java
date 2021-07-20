package com.example.demo.src.store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Blob;
import java.time.LocalDateTime;

@Getter @Setter @ToString
@AllArgsConstructor
public class Store {
	private enum Status {
		Ready, Used, Deleted;
	}

	private int id;
	private String storeCategoryName;
	private String name;
	private Blob notice;
	private Blob info;
	private String paymentMethod;
	private String deliveryType;
	private Blob originInform;
	private String address;
	private String phoneNumber;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private Status status;
	private String hours;
	private String addressBuildingNum;
	private String districtCode;
	private String addressDetail;
	private String deliveryTime;
	private String closedDay;
	private String deliveryArea;
	private int minOrderPrice;
	private int tips;
	private Blob storeImageUrl;

	public Store(int id, String storeCategoryName, String name, Blob notice, Blob info, String paymentMethod, String deliveryType, Blob originInform, String address, String phoneNumber, String hours, String addressBuildingNum, String districtCode, String addressDetail, String deliveryTime, String closedDay, String deliveryArea, int minOrderPrice, int tips, Blob storeImageUrl) {
		this.id = id;
		this.storeCategoryName = storeCategoryName;
		this.name = name;
		this.notice = notice;
		this.info = info;
		this.paymentMethod = paymentMethod;
		this.deliveryType = deliveryType;
		this.originInform = originInform;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.hours = hours;
		this.addressBuildingNum = addressBuildingNum;
		this.districtCode = districtCode;
		this.addressDetail = addressDetail;
		this.deliveryTime = deliveryTime;
		this.closedDay = closedDay;
		this.deliveryArea = deliveryArea;
		this.minOrderPrice = minOrderPrice;
		this.tips = tips;
		this.storeImageUrl = storeImageUrl;
	}

	public Store() {
	}
}

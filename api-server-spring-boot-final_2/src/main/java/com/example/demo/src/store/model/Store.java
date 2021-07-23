package com.example.demo.src.store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter @ToString
@AllArgsConstructor
public class Store {
	public enum Status {
		Ready, Used, Deleted;
	}

	private int id;
	private String storeCategoryName;
	private String name;
	private String notice;
	private String info;
	private String paymentMethod;
	private String deliveryType;
	private String originInform;
	private String address;
	private String phoneNumber;
	private String hours;
	private String addressBuildingNum;
	private String districtCode;
	private String addressDetail;
	private String deliveryTime;
	private String closedDay;
	private String deliveryArea;
	private int minOrderPrice;
	private int tips;
	private String storeImageUrl;
	private Status status;

}

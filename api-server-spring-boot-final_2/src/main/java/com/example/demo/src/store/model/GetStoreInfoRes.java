package com.example.demo.src.store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetStoreInfoRes {
	private int storeId;
	private String storeImageUrl;
	private String info;
	private String hours;
	private String closedDay;
	private String phoneNumber;
	private String deliveryArea;
	private String notice;
	private int minOrderPrice;
	private int tips;
	private String address;
}

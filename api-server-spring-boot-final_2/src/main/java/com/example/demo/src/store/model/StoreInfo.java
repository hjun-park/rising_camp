package com.example.demo.src.store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class StoreInfo {

	private int id;
	private String info;
	private String address;
	private String phoneNumber;
	private String hours;
	private String addressDetail;
	private String closedDay;
	private String deliveryArea;
	private int minOrderPrice;
	private int tips;

}

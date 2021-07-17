package com.example.demo.src.store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetStoreRes {
	private int storeId;
	private String storeName;
	private double rating;
	private int reviewCount;
	private int minOrderPrice;
	private String paymentMethod;
	private String deliveryTime;

}

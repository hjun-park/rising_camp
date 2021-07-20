package com.example.demo.src.store.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class StoreCategoryDTO {

	private String storeName;
	private String storeImageUrl;
	private Double rating;
	private Double reviewCount;
	private String deliveryTime;
	private int minOrderPrice;

}

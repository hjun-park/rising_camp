package com.example.demo.src.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter @Setter @ToString
public class Address {
	private int buildingNum;
	private Double longitude;
	private Double latitude;
	private String addressName;
	private int zipcode;

	public Address() {
	}
}

package com.example.demo.src.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@AllArgsConstructor
@Getter @Setter @ToString
@Entity
public class Address {

	@Id
	private String buildingNum;

	private Double longitude;
	private Double latitude;
	private String addressName;
	private int zipcode;

	public Address() {
	}
}

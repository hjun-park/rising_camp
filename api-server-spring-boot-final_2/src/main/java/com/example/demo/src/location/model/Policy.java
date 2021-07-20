package com.example.demo.src.location.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter	@ToString
@AllArgsConstructor
public class Policy {
	public enum Status {
		Used, Deleted;
	}
	private int id;
	private int storeId;
	private String districtCode;
	private int additionalTips;

	private Status status;

	public Policy() {
	}
}

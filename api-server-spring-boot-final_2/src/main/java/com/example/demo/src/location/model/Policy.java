package com.example.demo.src.location.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter	@ToString
@AllArgsConstructor
public class Policy {
	private enum Status {
		Used, Deleted;
	}
	private int id;
	private int storeId;
	private String districtCode;
	private int additionalTips;

	private Status status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public Policy() {
	}
}

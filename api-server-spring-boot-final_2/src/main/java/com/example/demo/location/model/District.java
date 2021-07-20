package com.example.demo.location.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter @ToString
@AllArgsConstructor
public class District {

	private String code;
	private String region1Depth;
	private String region2Depth;
	private String region3Depth;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;


	public District() {

	}
}

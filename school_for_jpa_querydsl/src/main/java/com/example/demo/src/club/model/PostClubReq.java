package com.example.demo.src.club.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostClubReq {

	private String name;

	@Builder
	public PostClubReq(String name) {
		this.name = name;
	}
}

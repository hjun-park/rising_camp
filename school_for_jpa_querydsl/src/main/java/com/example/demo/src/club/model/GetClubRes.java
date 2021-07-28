package com.example.demo.src.club.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetClubRes {

	private Long id;
	private String name;
	private String teacherName;

	@Builder
	public GetClubRes(Long id, String name, String teacherName) {
		this.id = id;
		this.name = name;
		this.teacherName = teacherName;
	}
}

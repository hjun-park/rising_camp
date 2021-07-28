package com.example.demo.src.club.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PatchClubReq {

	private String name;
	private Long teacherId;

//	@Builder
//	public PatchClubReq(String name, int teacherId) {
//		this.name = name;
//		this.teacherId = teacherId;
//	}
}

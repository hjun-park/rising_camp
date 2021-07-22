package com.example.demo.src.member.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberRes {

	private String jwt;
	private int id;

	@Builder
	public MemberRes(String jwt, int id) {
		this.jwt = jwt;
		this.id = id;
	}
}

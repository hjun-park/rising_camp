package com.example.demo.src.member.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MembershipDTO {
	public enum Status {
		Used, Deleted;
	}

	private String name;
	private Status status;

	@Builder
	public MembershipDTO(String name) {
		this.name = name;
	}
}

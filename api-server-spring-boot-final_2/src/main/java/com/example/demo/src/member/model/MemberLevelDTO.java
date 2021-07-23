package com.example.demo.src.member.model;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberLevelDTO {
	public enum Status {
		Used, Deleted
	}

	private int memberId;
	private String membershipName;
	private Status status;
	private int orderCount;

	@Builder
	public MemberLevelDTO(String membershipName, int orderCount) {
		this.membershipName = membershipName;
		this.orderCount = orderCount;
	}
}

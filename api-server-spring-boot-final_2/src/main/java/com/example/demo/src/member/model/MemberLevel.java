package com.example.demo.src.member.model;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberLevel {
	public enum Status {
		Used, Deleted
	}

	private int memberId;
	private String membershipName;
	private Status status;
	private int orderCount;

	@Builder
	public MemberLevel(String membershipName, int orderCount) {
		this.membershipName = membershipName;
		this.orderCount = orderCount;
	}
}

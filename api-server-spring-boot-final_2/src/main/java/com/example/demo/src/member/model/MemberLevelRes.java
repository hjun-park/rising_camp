package com.example.demo.src.member.model;

import com.sun.istack.NotNull;
import jdk.internal.util.Preconditions;
import lombok.*;

import static lombok.Lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberLevelRes {

	private String name;
	private String membershipName;

	@Builder
	public MemberLevelRes(String name, String membershipName) {
		this.name = checkNotNull(name, "name");
		this.membershipName = checkNotNull(membershipName, "membershipName");
	}
}

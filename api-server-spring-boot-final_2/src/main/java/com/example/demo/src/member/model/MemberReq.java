package com.example.demo.src.member.model;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberReq {

	private String name;
	private String email;
	private String password;

	public void setPassword(String password) {
		this.password = password;
	}
}

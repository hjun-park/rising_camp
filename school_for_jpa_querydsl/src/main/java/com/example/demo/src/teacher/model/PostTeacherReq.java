package com.example.demo.src.teacher.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostTeacherReq {

	private String name;
	private String phoneNumber;
	private String subject;

	@Builder
	public PostTeacherReq(String name, String phoneNumber, String subject) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.subject = subject;
	}
}

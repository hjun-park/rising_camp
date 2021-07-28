package com.example.demo.src.student.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostStudentReq {

	private String name;
	private int grade;
	private int studentClass;
	private String phoneNumber;

	@Builder
	public PostStudentReq(String name, int grade, int studentClass, String phoneNumber) {
		this.name = name;
		this.grade = grade;
		this.studentClass = studentClass;
		this.phoneNumber = phoneNumber;
	}
}

package com.example.demo.src.student.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetStudentRes {

	private Long id;
	private String name;
	private int grade;
	private int studentClass;
	private String phoneNumber;

	@Builder
	public GetStudentRes(Long id, String name, int grade, int studentClass, String phoneNumber) {
		this.id = id;
		this.name = name;
		this.grade = grade;
		this.studentClass = studentClass;
		this.phoneNumber = phoneNumber;
	}
}

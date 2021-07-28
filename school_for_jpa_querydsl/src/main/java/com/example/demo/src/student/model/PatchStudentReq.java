package com.example.demo.src.student.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PatchStudentReq {

	private String name;
	private int grade;
	private int studentClass;
	private String phoneNumber;
	private Long clubId;
	private Long teacherId;

	@Builder
	public PatchStudentReq(String name, int grade, int studentClass, String phoneNumber, Long clubId, Long teacherId) {
		this.name = name;
		this.grade = grade;
		this.studentClass = studentClass;
		this.phoneNumber = phoneNumber;
		this.clubId = clubId;
		this.teacherId = teacherId;
	}
}

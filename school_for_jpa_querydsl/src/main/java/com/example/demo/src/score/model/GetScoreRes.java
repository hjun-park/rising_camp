package com.example.demo.src.score.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GetScoreRes {

	private Long id;
	private int korean;
	private int math;
	private int english;
	private String studentName;

	@Builder
	public GetScoreRes(Long id, int korean, int math, int english, String studentName) {
		this.id = id;
		this.korean = korean;
		this.math = math;
		this.english = english;
		this.studentName = studentName;
	}
}

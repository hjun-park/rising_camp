package com.example.demo.src.score.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostScoreReq {

	private int korean;
	private int math;
	private int english;
	private Long studentId;

	@Builder
	public PostScoreReq(int korean, int math, int english, Long studentId) {
		this.korean = korean;
		this.math = math;
		this.english = english;
		this.studentId = studentId;
	}
}

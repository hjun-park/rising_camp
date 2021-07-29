package com.example.demo.src.score.model;

import com.example.demo.config.BaseEntity;
import com.example.demo.src.Status;
import com.example.demo.src.student.model.Student;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Entity
@Table(name = "SCORE")
public class Score extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Range(min = 0, max = 100)
	private int korean;

	@NotNull
	@Range(min = 0, max = 100)
	private int math;

	@NotNull
	@Range(min = 0, max = 100)
	private int english;

	@Column(insertable = false)
	@Enumerated(EnumType.STRING)
	private Status status;

	@OneToOne
	@JoinColumn(name = "studentId")
	private Student student;

	public Score() {

	}

	@Builder
	public Score(int korean, int math, int english, Status status, Student student) {
		this.korean = korean;
		this.math = math;
		this.english = english;
		this.status = status;
		this.student = student;
	}
}

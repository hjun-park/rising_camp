package com.example.demo.src.score.model;

import com.example.demo.src.student.model.Student;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "SCORE")
public class Score {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int korean;
	private int math;
	private int english;

	@OneToOne
	@JoinColumn(name = "student_id")
	private Student student;


	public Score() {
	}
}

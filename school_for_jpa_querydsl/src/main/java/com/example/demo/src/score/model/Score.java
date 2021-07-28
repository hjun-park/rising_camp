package com.example.demo.src.score.model;

import com.example.demo.config.BaseEntity;
import com.example.demo.src.student.model.Student;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "SCORE")
public class Score extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "SCORE_ID")
	private Long id;
	private int korean;
	private int math;
	private int english;

	@OneToOne
	@JoinColumn(name = "studentId")
	private Student student;


	public Score() {
	}
}

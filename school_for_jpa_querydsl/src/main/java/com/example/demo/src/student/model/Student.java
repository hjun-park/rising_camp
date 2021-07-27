package com.example.demo.src.student.model;

import com.example.demo.config.BaseEntity;
import com.example.demo.src.club.model.Club;
import com.example.demo.src.teacher.model.Teacher;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "STUDENT")
public class Student extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private int grade;

	@Column(name = "class")
	private int studentClass;
	private String phoneNumber;

	@ManyToOne
	@JoinColumn(name = "club_id")
	private Club club;

	@ManyToOne
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;


	public Student() {

	}

}

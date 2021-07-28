package com.example.demo.src.student.model;

import com.example.demo.config.BaseEntity;
import com.example.demo.src.Status;
import com.example.demo.src.club.model.Club;
import com.example.demo.src.teacher.model.Teacher;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "STUDENT")
public class Student extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "i")
	private Long id;

	private String name;
	private int grade;

//	@Column(name = "class")
	private int studentClass;
	private String phoneNumber;

	@ManyToOne
	@JoinColumn(name = "clubId")
	private Club club;

	@ManyToOne
	@JoinColumn(name = "teacherId")
	private Teacher teacher;

	@Column(insertable = false)
	@Enumerated(EnumType.STRING)
	private Status status;

	public Student() {

	}

	@Builder
	public Student(String name, int grade, int studentClass, String phoneNumber, Club club, Teacher teacher, Status status) {
		this.name = name;
		this.grade = grade;
		this.studentClass = studentClass;
		this.phoneNumber = phoneNumber;
		this.club = club;
		this.teacher = teacher;
		this.status = status;
	}


}

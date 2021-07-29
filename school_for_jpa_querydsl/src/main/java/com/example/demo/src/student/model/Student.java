package com.example.demo.src.student.model;

import com.example.demo.config.BaseEntity;
import com.example.demo.src.Status;
import com.example.demo.src.club.model.Club;
import com.example.demo.src.teacher.model.Teacher;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Table(name = "STUDENT")
public class Student extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank()
	private String name;

	@NotNull()
	private int grade;

	@NotNull()
	private int studentClass;

	// saveCheck : 등록 시에도 체크
	// updateCheck : 수정 시에도 체크
	@NotBlank()
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


	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public void setClub(Club club) {
		this.club = club;
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

	public void updateStudent(String name, int grade, int studentClass, String phoneNumber, Club club, Teacher teacher) {
		this.name = name;
		this.grade = grade;
		this.studentClass = studentClass;
		this.phoneNumber = phoneNumber;
		this.club = club;
		this.teacher = teacher;
	}


}

package com.example.demo.src.teacher.model;

import com.example.demo.config.BaseEntity;
import com.example.demo.src.Status;
import com.example.demo.src.club.model.Club;
import com.example.demo.src.student.model.Student;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "TEACHER")
public class Teacher extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "TEACHER_ID")
	private Long id;
	private String name;
	private String phoneNumber;
	private String subject;

	@Column(insertable = false)
	@Enumerated(EnumType.STRING)
	private Status status;

	@OneToOne(mappedBy = "teacher", targetEntity = Club.class)
	private Club club;

	@OneToMany(mappedBy = "teacher", targetEntity = Student.class)
	private List<Student> students = new ArrayList<>();

	public Teacher() {

	}

	@Builder
	public Teacher(String name, String phoneNumber, String subject, Status status) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.subject = subject;
		this.status = status;
	}
}

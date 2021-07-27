package com.example.demo.src.teacher.model;

import com.example.demo.src.club.model.Club;
import com.example.demo.src.student.model.Student;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "TEACHER")
public class Teacher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String phoneNumber;
	private String subject;

	@OneToOne(mappedBy = "club_id")
	private Club club;

	@OneToMany(mappedBy = "student_id")
	private List<Student> students = new ArrayList<>();


	public Teacher() {
	}
}

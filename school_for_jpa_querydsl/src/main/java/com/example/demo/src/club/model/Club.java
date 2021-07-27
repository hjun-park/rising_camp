package com.example.demo.src.club.model;

import com.example.demo.config.BaseEntity;
import com.example.demo.src.student.model.Student;
import com.example.demo.src.teacher.model.Teacher;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "CLUB")
public class Club extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	@OneToMany(mappedBy = "student_id")
	private List<Student> students = new ArrayList<>();

	@OneToOne
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;



	public Club() {

	}
}

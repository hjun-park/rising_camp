package com.example.demo.src.club.model;

import com.example.demo.config.BaseEntity;
import com.example.demo.src.Status;
import com.example.demo.src.student.model.Student;
import com.example.demo.src.teacher.model.Teacher;
import lombok.Builder;
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

	@Column(insertable = false)
	@Enumerated(EnumType.STRING)
	private Status status;

	@OneToMany(mappedBy = "club")
	private List<Student> students = new ArrayList<>();

	@OneToOne
	@JoinColumn(name = "teacherId")
	private Teacher teacher;

	public Club() {

	}


	@Builder
	public Club(String name, Teacher teacher, Status status) {
		this.name = name;
		this.teacher = teacher;
		this.status = status;
	}

}

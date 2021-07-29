package com.example.demo.src.club.model;

import com.example.demo.config.BaseEntity;
import com.example.demo.src.Status;
import com.example.demo.src.student.model.Student;
import com.example.demo.src.teacher.model.Teacher;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "CLUB")
public class Club extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String name;

	@Column(insertable = false)
	@Enumerated(EnumType.STRING)
	private Status status;

	@OneToMany(mappedBy = "club")
	private List<Student> students = new ArrayList<>();

	@OneToOne
	@JoinColumn(name = "teacherId")
	private Teacher teacher;


	// 연관관계 편의 메소드
	public void addStudents(Student student) {
		students.add(student);
		student.setClub(this);
	}

	public Club() {
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}


	@Builder
	public Club(String name, Teacher teacher, Status status) {
		this.name = name;
		this.teacher = teacher;
		this.status = status;
	}

	public void updateClub(String name, Teacher teacher) {
		this.name = name;
		this.teacher = teacher;
	}

	public void updateStatus(Status status) {
		this.status = status;
	}

}

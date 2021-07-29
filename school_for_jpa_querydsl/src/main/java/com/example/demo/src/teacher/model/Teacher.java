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
	private Long id;
	private String name;
	private String phoneNumber;
	private String subject;

	@Column(insertable = false)
	@Enumerated(EnumType.STRING)
	private Status status;

	@OneToOne(mappedBy = "teacher", targetEntity = Club.class)
	private Club club;

	// 양방향 연관관계는 미리 만들지 말고 실제 개발 시 필요하면 만들기
	@OneToMany(mappedBy = "teacher", targetEntity = Student.class)
	private List<Student> students = new ArrayList<>();

	// 외래키 없는 쪽, mappedBy 연관관계 주인이 아닌 쪽에서는 편의 메소드 만들기
	public void addStudent(Student student) {
		students.add(student);
		student.setTeacher(this);
	}

	public void addClub(Club club) {
		club.setTeacher(this);
	}

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

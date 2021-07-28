package com.example.demo.src.student;

import com.example.demo.config.BaseException;
import com.example.demo.src.student.model.GetStudentRes;
import com.example.demo.src.student.model.Student;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class StudentService {

	private StudentRepository studentRepository;
	private JwtService jwtService;

	public StudentService(StudentRepository studentRepository, JwtService jwtService) {
		this.studentRepository = studentRepository;
		this.jwtService = jwtService;
	}

	public GetStudentRes findStudent(Long studentId) throws BaseException {

		log.info("test");
		Student student = studentRepository.findById(studentId);

		return GetStudentRes.builder()
			.id(student.getId())
			.grade(student.getGrade())
			.name(student.getName())
			.studentClass(student.getStudentClass())
			.phoneNumber(student.getPhoneNumber())
			.build();
	}
}

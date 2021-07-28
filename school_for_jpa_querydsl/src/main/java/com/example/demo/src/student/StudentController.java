package com.example.demo.src.student;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.student.model.GetStudentRes;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/student")
public class StudentController {

	private StudentService studentService;
	private JwtService jwtService;

	public StudentController(StudentService studentService, JwtService jwtService) {
		this.studentService = studentService;
		this.jwtService = jwtService;
	}

	@GetMapping("/{studentId}")
	public BaseResponse<GetStudentRes> getStudent(@PathVariable Long studentId) {
		try {
			log.info(String.valueOf(studentId));

			GetStudentRes getStudentRes = studentService.findStudent(studentId);
			return new BaseResponse<>(getStudentRes);
		} catch(BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}
}

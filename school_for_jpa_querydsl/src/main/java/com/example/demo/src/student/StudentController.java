package com.example.demo.src.student;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.student.model.GetStudentRes;
import com.example.demo.src.student.model.PatchStudentReq;
import com.example.demo.src.student.model.PostStudentReq;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/student")
public class StudentController {

	private final StudentService studentService;
	private final JwtService jwtService;

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

	@PostMapping("/join")
	public BaseResponse<Long> postStudent(@RequestBody PostStudentReq postStudentReq) {
		try {

			Long resultId = studentService.joinStudent(postStudentReq);
			return new BaseResponse<>(resultId);
		} catch(BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}

	@PatchMapping("/{studentId}/edit")
	public BaseResponse<Long> patchStudent(@PathVariable Long studentId,
										   @RequestBody PatchStudentReq patchStudentReq) {
		try {
			Long resultId = studentService.updateStudent(studentId, patchStudentReq);
			return new BaseResponse<>(resultId);
		} catch(BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}

	@PatchMapping("/status")
	public BaseResponse<Long> deleteStudent(@RequestBody PostStudentReq postStudentReq) {
		try {

			Long resultId = studentService.joinStudent(postStudentReq);
			return new BaseResponse<>(resultId);
		} catch(BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}

}

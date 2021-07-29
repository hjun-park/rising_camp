package com.example.demo.src.student;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.student.model.GetStudentRes;
import com.example.demo.src.student.model.PatchStudentReq;
import com.example.demo.src.student.model.PostStudentReq;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.example.demo.config.BaseResponseStatus.*;

@Slf4j
@RequestMapping("/student")
@RestController
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
	public Object postStudent(@Validated @RequestBody PostStudentReq postStudentReq,
										  BindingResult bindingResult) {

		// 만약 Invalid Data가 들어오게 되면 컨트롤러 자체를 실행하지 못하므로
		// 아래 코드는 수행되지 못한다.
//		if (bindingResult.hasErrors()) {
//			log.info("검증 오류 발생 errors = {}", bindingResult);
//			return new BaseResponse<>(bindingResult.getAllErrors());	// object형식, result보여줄거임
//		}

		try {
			Long resultId = studentService.joinStudent(postStudentReq);
			return new BaseResponse<>(resultId);
		} catch(BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}

	@PatchMapping("/{studentId}/edit")
	public BaseResponse<Long> patchStudent(@PathVariable Long studentId,
										   @Validated @RequestBody PatchStudentReq patchStudentReq,
										   BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			log.info("errors={}", bindingResult);
			return new BaseResponse<>(SERVER_ERROR);
		}

		try {
			Long resultId = studentService.updateStudent(studentId, patchStudentReq);
			return new BaseResponse<>(resultId);
		} catch(BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}

	@PatchMapping("/{studentId}/status")
	public BaseResponse<Long> deleteStudent(@PathVariable Long studentId) {
		try {

			Long resultId = studentService.deleteStudent(studentId);
			return new BaseResponse<>(resultId);
		} catch(BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}

}

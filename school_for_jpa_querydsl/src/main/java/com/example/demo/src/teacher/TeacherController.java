package com.example.demo.src.teacher;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.teacher.model.GetTeacherRes;
import com.example.demo.src.teacher.model.PatchTeacherReq;
import com.example.demo.src.teacher.model.PostTeacherReq;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/teacher")
public class TeacherController {

	private final TeacherService teacherService;
	private final JwtService jwtService;

	public TeacherController(TeacherService teacherService, JwtService jwtService) {
		this.teacherService = teacherService;
		this.jwtService = jwtService;
	}

	@GetMapping("/{teacherId}")
	public BaseResponse<GetTeacherRes> getTeacher(@PathVariable Long teacherId) {
		try {
			log.info(String.valueOf(teacherId));

			GetTeacherRes getTeacherRes = teacherService.findTeacher(teacherId);
			return new BaseResponse<>(getTeacherRes);
		} catch(BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}

	@PostMapping("/join")
	public BaseResponse<Long> postTeacher(@RequestBody PostTeacherReq postTeacherReq) {
		try {

			Long resultId = teacherService.joinTeacher(postTeacherReq);
			return new BaseResponse<>(resultId);
		} catch(BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}

	@PatchMapping("/{teacherId}/edit")
	public BaseResponse<Long> patchTeacher(@PathVariable Long teacherId,
										@RequestBody PatchTeacherReq patchTeacherReq) {
		try {
			Long resultId = teacherService.updateTeacher(teacherId, patchTeacherReq);
			return new BaseResponse<>(resultId);
		} catch(BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}

	@PatchMapping("/{teacherId}/status")
	public BaseResponse<Long> deleteTeacher(@PathVariable Long teacherId) {
		try {
			Long resultId = teacherService.deleteTeacher(teacherId);
			return new BaseResponse<>(resultId);
		} catch(BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}
}

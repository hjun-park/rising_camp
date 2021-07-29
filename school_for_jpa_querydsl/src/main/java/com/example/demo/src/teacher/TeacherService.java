package com.example.demo.src.teacher;

import com.example.demo.config.BaseException;
import com.example.demo.src.Status;
import com.example.demo.src.teacher.model.GetTeacherRes;
import com.example.demo.src.teacher.model.PatchTeacherReq;
import com.example.demo.src.teacher.model.PostTeacherReq;
import com.example.demo.src.teacher.model.Teacher;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class TeacherService {

	private TeacherRepository teacherRepository;
	private JwtService jwtService;

	public TeacherService(TeacherRepository teacherRepository, JwtService jwtService) {
		this.teacherRepository = teacherRepository;
		this.jwtService = jwtService;
	}

	public GetTeacherRes findTeacher(Long teacherId) throws BaseException {

		Teacher teacher = teacherRepository.findById(teacherId);

		return GetTeacherRes.builder()
			.id(teacher.getId())
			.name(teacher.getName())
			.phoneNumber(teacher.getPhoneNumber())
			.subject(teacher.getSubject())
			.build();
	}

	@Transactional
	public Long joinTeacher(PostTeacherReq postTeacherReq) throws BaseException {

		Teacher teacher = Teacher.builder()
			.name(postTeacherReq.getName())
			.phoneNumber(postTeacherReq.getPhoneNumber())
			.subject(postTeacherReq.getSubject())
			.build();

		return teacherRepository.join(teacher);
	}

	@Transactional
	public Long updateTeacher(Long teacherId, PatchTeacherReq patchTeacherReq) throws BaseException {

		Teacher findTeacher = teacherRepository.findById(teacherId);
		findTeacher.updateTeacher(
			patchTeacherReq.getName(),
			patchTeacherReq.getPhoneNumber(),
			patchTeacherReq.getSubject()
		);

		return findTeacher.getId();
	}

	@Transactional
	public Long deleteTeacher(Long teacherId) throws BaseException {

		Teacher findTeacher = teacherRepository.findById(teacherId);
//		findTeacher = Teacher.builder()
//			.status(Status.valueOf("Deleted"))
//			.build();
		findTeacher.updateStatus(Status.valueOf("Deleted"));

		return findTeacher.getId();
	}
}

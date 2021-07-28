package com.example.demo.src.student;

import com.example.demo.config.BaseException;
import com.example.demo.src.Status;
import com.example.demo.src.club.ClubRepository;
import com.example.demo.src.club.model.Club;
import com.example.demo.src.student.model.GetStudentRes;
import com.example.demo.src.student.model.PatchStudentReq;
import com.example.demo.src.student.model.PostStudentReq;
import com.example.demo.src.student.model.Student;
import com.example.demo.src.teacher.TeacherRepository;
import com.example.demo.src.teacher.model.Teacher;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sound.midi.Patch;

@Service
@Slf4j
@Transactional(readOnly = true)
public class StudentService {

	private StudentRepository studentRepository;
	private TeacherRepository teacherRepository;
	private ClubRepository clubRepository;
	private JwtService jwtService;

	public StudentService(StudentRepository studentRepository, TeacherRepository teacherRepository, ClubRepository clubRepository, JwtService jwtService) {
		this.studentRepository = studentRepository;
		this.teacherRepository = teacherRepository;
		this.clubRepository = clubRepository;
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

	@Transactional
	public Long joinStudent(PostStudentReq postStudentReq) throws BaseException {

		Student student = Student.builder()
			.grade(postStudentReq.getGrade())
			.name(postStudentReq.getName())
			.phoneNumber(postStudentReq.getPhoneNumber())
			.studentClass(postStudentReq.getStudentClass())
			.build();

		return studentRepository.join(student);
	}

	@Transactional
	public Long updateStudent(Long studentId, PatchStudentReq patchStudentReq) throws BaseException {

		Teacher findTeacher = teacherRepository.findById(patchStudentReq.getTeacherId());
		Club findClub = clubRepository.findById(patchStudentReq.getClubId());

		Student findStudent = studentRepository.findById(studentId);
		findStudent = Student.builder()
			.name(patchStudentReq.getName())
			.grade(patchStudentReq.getGrade())
			.studentClass(patchStudentReq.getStudentClass())
			.phoneNumber(patchStudentReq.getPhoneNumber())
			.club(findClub)
			.teacher(findTeacher)
			.build();

		return findStudent.getId();
	}

	@Transactional
	public Long deleteStudent(Long studentId) throws BaseException {
		Student findStudent = studentRepository.findById(studentId);
		findStudent = Student.builder()
			.status(Status.valueOf("Deleted"))
			.build();

		return findStudent.getId();
	}
}

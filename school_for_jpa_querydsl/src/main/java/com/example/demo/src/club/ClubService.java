package com.example.demo.src.club;

import com.example.demo.config.BaseException;
import com.example.demo.src.Status;
import com.example.demo.src.club.model.Club;
import com.example.demo.src.club.model.GetClubRes;
import com.example.demo.src.club.model.PatchClubReq;
import com.example.demo.src.club.model.PostClubReq;
import com.example.demo.src.student.StudentRepository;
import com.example.demo.src.student.model.GetStudentRes;
import com.example.demo.src.student.model.PatchStudentReq;
import com.example.demo.src.student.model.PostStudentReq;
import com.example.demo.src.student.model.Student;
import com.example.demo.src.teacher.TeacherRepository;
import com.example.demo.src.teacher.TeacherService;
import com.example.demo.src.teacher.model.Teacher;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class ClubService {

	private ClubRepository clubRepository;
	private TeacherRepository teacherRepository;
	private JwtService jwtService;

	public ClubService(ClubRepository clubRepository, TeacherRepository teacherRepository, JwtService jwtService) {
		this.clubRepository = clubRepository;
		this.teacherRepository = teacherRepository;
		this.jwtService = jwtService;
	}

	public GetClubRes findClub(Long clubId) throws BaseException {

		Club club = clubRepository.findById(clubId);

		return GetClubRes.builder()
			.id(club.getId())
			.name(club.getName())
			.teacherName(club.getTeacher().getName())
			.build();
	}

	@Transactional
	public Long joinClub(PostClubReq postClubReq) throws BaseException {

		Club club = Club.builder()
			.name(postClubReq.getName())
			.build();

		return clubRepository.join(club);
	}

	@Transactional
	public Long updateClub(Long clubId, PatchClubReq patchClubReq) throws BaseException {

		Teacher findTeacher = teacherRepository.findById(patchClubReq.getTeacherId());

		Club findClub = clubRepository.findById(clubId);
		findClub.updateClub(
			patchClubReq.getName(),
			findTeacher
		);

		return findClub.getId();
	}

	@Transactional
	public Long deleteClub(Long clubId) throws BaseException {

		Club findClub = clubRepository.findById(clubId);

		findClub.updateStatus(Status.valueOf("Deleted"));

		return findClub.getId();
	}

}

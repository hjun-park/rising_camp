package com.example.demo.src.score;

import com.example.demo.config.BaseException;
import com.example.demo.src.Status;
import com.example.demo.src.club.ClubRepository;
import com.example.demo.src.club.model.Club;
import com.example.demo.src.club.model.GetClubRes;
import com.example.demo.src.club.model.PatchClubReq;
import com.example.demo.src.club.model.PostClubReq;
import com.example.demo.src.score.model.GetScoreRes;
import com.example.demo.src.score.model.PatchScoreReq;
import com.example.demo.src.score.model.PostScoreReq;
import com.example.demo.src.score.model.Score;
import com.example.demo.src.student.StudentRepository;
import com.example.demo.src.student.model.Student;
import com.example.demo.src.teacher.TeacherRepository;
import com.example.demo.src.teacher.model.Teacher;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class ScoreService {

	private ScoreRepository scoreRepository;
	private StudentRepository studentRepository;
	private JwtService jwtService;

	public ScoreService(ScoreRepository scoreRepository, StudentRepository studentRepository, JwtService jwtService) {
		this.scoreRepository = scoreRepository;
		this.studentRepository = studentRepository;
		this.jwtService = jwtService;
	}

	public GetScoreRes findScore(Long scoreId) throws BaseException {

		Score score = scoreRepository.findById(scoreId);

		return GetScoreRes.builder()
			.id(score.getId())
			.korean(score.getKorean())
			.math(score.getMath())
			.english(score.getEnglish())
			.studentName(score.getStudent().getName())
			.build();
	}

	@Transactional
	public Long joinScore(PostScoreReq postScoreReq) throws BaseException {

		Score score = Score.builder()
			.korean(postScoreReq.getKorean())
			.math(postScoreReq.getMath())
			.english(postScoreReq.getEnglish())
			.build();

		return scoreRepository.join(score);
	}

	@Transactional
	public Long updateScore(Long scoreId, PatchScoreReq patchScoreReq) throws BaseException {

		Student findStudent = studentRepository.findById(patchScoreReq.getStudentId());

		Score findScore = scoreRepository.findById(scoreId);
		findScore = Score.builder()
			.korean(patchScoreReq.getKorean())
			.english(patchScoreReq.getEnglish())
			.math(patchScoreReq.getMath())
			.student(findStudent)
			.build();

		return findScore.getId();
	}

	@Transactional
	public Long deleteScore(Long scoreId) throws BaseException {

		Score findScore = scoreRepository.findById(scoreId);
		findScore = Score.builder()
			.status(Status.valueOf("Deleted"))
			.build();

		return findScore.getId();
	}

}

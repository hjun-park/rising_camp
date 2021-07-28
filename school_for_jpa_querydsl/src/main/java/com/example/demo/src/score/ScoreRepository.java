package com.example.demo.src.score;

import com.example.demo.config.BaseException;
import com.example.demo.src.club.model.Club;
import com.example.demo.src.score.model.Score;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Repository
@Slf4j
public class ScoreRepository {

	@PersistenceContext
	private EntityManager em;

	public Score findById(Long scoreId) throws BaseException {
		try {
			return em.find(Score.class, scoreId);
		} catch(Exception exception) {
			exception.printStackTrace();
			throw new BaseException(DATABASE_ERROR);
		}
	}

	public Long join(Score score) throws BaseException {
		try {
			em.persist(score);
			return score.getId();
		} catch(Exception exception) {
			exception.printStackTrace();
			throw new BaseException(DATABASE_ERROR);
		}
	}

	public List<Score> findAll() throws BaseException {
		try {
			return em.createQuery("select s from Score s where s.status = 'Used'", Score.class)
				.getResultList();
		} catch(Exception exception) {
			exception.printStackTrace();
			throw new BaseException(DATABASE_ERROR);
		}
	}
}

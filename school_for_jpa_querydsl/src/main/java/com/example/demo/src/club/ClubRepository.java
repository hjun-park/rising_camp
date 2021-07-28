package com.example.demo.src.club;

import com.example.demo.config.BaseException;
import com.example.demo.src.club.model.Club;
import com.example.demo.src.student.model.Student;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Repository
@Slf4j
public class ClubRepository {

	@PersistenceContext
	private EntityManager em;

	public Club findById(Long clubId) throws BaseException {
		try {
			log.info("############## 3 #################");
			return em.find(Club.class, clubId);
		} catch(Exception exception) {
			exception.printStackTrace();
			throw new BaseException(DATABASE_ERROR);
		}
	}

	public Long join(Club club) throws BaseException {
		try {
			em.persist(club);
			return club.getId();
		} catch(Exception exception) {
			exception.printStackTrace();
			throw new BaseException(DATABASE_ERROR);
		}
	}

	public List<Club> findAll() throws BaseException {
		try {
			return em.createQuery("select c from Club c where c.status = 'Used'", Club.class)
				.getResultList();
		} catch(Exception exception) {
			exception.printStackTrace();
			throw new BaseException(DATABASE_ERROR);
		}
	}


}

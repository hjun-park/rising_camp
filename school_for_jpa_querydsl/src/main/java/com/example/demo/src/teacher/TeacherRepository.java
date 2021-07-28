package com.example.demo.src.teacher;

import com.example.demo.config.BaseException;
import com.example.demo.src.student.model.Student;
import com.example.demo.src.teacher.model.Teacher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Repository
@Slf4j
public class TeacherRepository {

	@PersistenceContext
	private EntityManager em;

	public Teacher findById(Long teacherId) throws BaseException {
		try {
			return em.find(Teacher.class, teacherId);
		} catch(Exception exception) {
			exception.printStackTrace();
			throw new BaseException(DATABASE_ERROR);
		}
	}

	public Long join(Teacher teacher) throws BaseException {
		try {
			em.persist(teacher);
			return teacher.getId();
		} catch(Exception exception) {
			exception.printStackTrace();
			throw new BaseException(DATABASE_ERROR);
		}
	}

	public List<Teacher> findAll() throws BaseException {
		try {
			return em.createQuery("select t from Teacher t where t.status = 'Used'", Teacher.class)
				.getResultList();
		} catch(Exception exception) {
			exception.printStackTrace();
			throw new BaseException(DATABASE_ERROR);
		}
	}


}


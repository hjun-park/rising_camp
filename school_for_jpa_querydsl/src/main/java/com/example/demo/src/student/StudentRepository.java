package com.example.demo.src.student;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.student.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static com.example.demo.config.BaseResponseStatus.*;

@Repository
@Slf4j
public class StudentRepository {

	@PersistenceContext
	private EntityManager em;

	public Student findById(Long studentId) throws BaseException {
		try {
			return em.find(Student.class, studentId);
		} catch(Exception exception) {
			exception.printStackTrace();
			throw new BaseException(DATABASE_ERROR);
		}
	}

}

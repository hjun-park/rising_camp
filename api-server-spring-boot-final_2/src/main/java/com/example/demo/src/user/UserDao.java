package com.example.demo.src.user;

import com.example.demo.src.user.model.PostLoginReq;
import com.example.demo.src.user.model.PostUserReq;
import com.example.demo.src.user.model.PostUserRes;
import com.example.demo.src.user.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.util.List;

@Repository
@Transactional(readOnly = true)
@Slf4j
public class UserDao {

	private JdbcTemplate jdbcTemplate;

	@PersistenceContext
	private EntityManager em;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public int checkUser(String email, String phoneNumber){
//		String checkEmailQuery = "select exists(select email from UserInfo where email = ?)";
		String checkUserQuery = "select exists(select email, phoneNumber from MEMBER WHERE email = ? OR phoneNumber = ?)";
		String checkEmailParams = email;
		String checkphoneNumberParams = phoneNumber;
		return this.jdbcTemplate.queryForObject(checkUserQuery, int.class, checkEmailParams, phoneNumber);
	}

	public User getPwd(PostLoginReq postLoginReq) {
		String getPwdQuery = "select id, email, password from MEMBER where email= ?";
		String getPwdParams = postLoginReq.getEmail();

		return this.jdbcTemplate.queryForObject(getPwdQuery,
			(rs, rowNum) -> new User(
				rs.getInt("id"),
				rs.getString("email"),
				rs.getString("password")
			),
			getPwdParams);
	}

//	public PostUserReq findByEmail(String email, String phoneNumber) {
//		return em.createQuery("select u from u where u.email = :email" +
//								" and u.phoneNumber = :phoneNumber", PostUserReq.class)
//			.getSingleResult();
//	}
//
//	public int createUser(PostUserReq postUserReq) {
//		String createUserQuery = "insert into MEMBER (phoneNumber, email, password, birthDate, name) VALUES (?,?,?,?,?)";
//		Object[] createUserParams = new Object[]{
//			postUserReq.getPhoneNumber(),
//			postUserReq.getEmail(),
//			postUserReq.getPassword(),
//			postUserReq.getBirthDate(),
//			postUserReq.getNickname()
//		};
//		this.jdbcTemplate.update(createUserQuery, createUserParams);
//
//		String lastInsertIdQuery = "select last_insert_id()";
//		return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);
//
//		return id;
//	}



}

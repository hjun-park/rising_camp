package com.example.demo.src.user;

import com.example.demo.src.user.model.PostUserReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
@Slf4j
public class UserDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public int checkEmail(String email){
		String checkEmailQuery = "select exists(select email from UserInfo where email = ?)";
		String checkEmailParams = email;
		return this.jdbcTemplate.queryForObject(checkEmailQuery,
			int.class,
			checkEmailParams);

	}

	public int createUser(PostUserReq postUserReq) {
		String createUserQuery = "insert into Member (phoneNumber, email, password, birthDate, nickName) VALUES (?,?,?,?,?)";
		Object[] createUserParams = new Object[]{
			postUserReq.getPhoneNumber(),
			postUserReq.getEmail(),
			postUserReq.getPassword(),
			postUserReq.getBirthDate(),
			postUserReq.getNickname()
		};
		this.jdbcTemplate.update(createUserQuery, createUserParams);

		String lastInsertIdQuery = "select last_insert_id()";
		return this.jdbcTemplate.queryForObject(lastInsertIdQuery,int.class);

		return id;
	}
}

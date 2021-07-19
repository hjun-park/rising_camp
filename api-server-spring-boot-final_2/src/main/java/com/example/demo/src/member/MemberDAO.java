package com.example.demo.src.member;

import com.example.demo.src.member.model.MemberDTO;
import com.example.demo.src.store.model.Store;
import com.example.demo.src.user.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional(readOnly = true)
@Slf4j
public class MemberDAO {

	private JdbcTemplate jdbcTemplate;

	@PersistenceContext
	private EntityManager em;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Transactional
	public Integer insertMember(MemberDTO memberDTO) {
		String insertMemberQuery = "insert into MEMBER(password, email, name, phoneNumber, profileImageUrl," +
			" addressBuildingNum, districtCode, birthDate)" +
			" VALUES(?, ?, ?, ?, ?, 0, 0, ?)";

		Object[] insertMemberParam = new Object[] {
			memberDTO.getPassword(), memberDTO.getEmail(),memberDTO.getName(),
			memberDTO.getPhoneNumber(), memberDTO.getProfileImageUrl(),
			memberDTO.getBirthDate()
		};

		this.jdbcTemplate.update(insertMemberQuery, insertMemberParam);

		String lastInsertIdQuery = "select last_insert_id()";
		return this.jdbcTemplate.queryForObject(lastInsertIdQuery, Integer.class);

	}
}

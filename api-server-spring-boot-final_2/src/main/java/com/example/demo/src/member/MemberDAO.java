package com.example.demo.src.member;

import com.example.demo.src.member.model.MemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

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

	public Integer isDuplicatedMember(String email, String phoneNumber) {
		String checkQuery = "Select count(id)" +
			" FROM MEMBER" +
			" WHERE email = ? OR phoneNumber = ?";

		Object[] checkParam = new Object[] { email, phoneNumber	};

		return this.jdbcTemplate.queryForObject(checkQuery, int.class, checkParam);

	}

	public Integer isDuplicatedName(String name) {
		// 닉네임 중복 체크
		String checkQuery = "select count(id)" +
			" FROM MEMBER" +
			" WHERE name = ?";

		return this.jdbcTemplate.queryForObject(checkQuery, int.class, name);

	}

	public MemberDTO findByIdPassword(String email, String password) {
		String findQuery = "SELECT *" +
			" FROM MEMBER" +
			" WHERE email = ?" +
			" AND password = ?" +
			" AND status = 'Joined'";
		Object[] findParam = new Object[]{email, password};

		return this.jdbcTemplate.queryForObject(findQuery, MemberDTO.class, findParam);
	}

	public Integer modifyMemberName(int memberId, String name) {
		String modifyQuery = "UPDATE MEMBER SET name = ? WHERE id = ?";
		Object[] modifyParam = new Object[]{memberId, name};

		return this.jdbcTemplate.queryForObject(modifyQuery, int.class, modifyParam);
	}

	public Integer modifyAcceptEmail(int memberId, String emailStatus) {
		String modifyQuery = "UPDATE MEMBER SET mailAccept = ? WHERE id = ?";
		Object[] modifyParam = new Object[]{memberId, emailStatus};

		this.jdbcTemplate.update(modifyQuery, modifyParam);

		return memberId;
	}

	public Integer modifyAcceptSms(int memberId, String smsStatus) {
		String modifyQuery = "UPDATE MEMBER SET smsAccept = ? WHERE id = ?";
		Object[] modifyParam = new Object[]{memberId, smsStatus};

		this.jdbcTemplate.update(modifyQuery, modifyParam);

		return memberId;
	}
}

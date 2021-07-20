package com.example.demo.src.member;

import com.example.demo.src.member.model.Member;
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
	public Integer insertMember(Member member) {
		String insertMemberQuery = "insert into MEMBER(password, email, name, phoneNumber, profileImageUrl," +
			" addressBuildingNum, districtCode, birthDate)" +
			" VALUES(?, ?, ?, ?, ?, 0, 0, ?)";

		Object[] insertMemberParam = new Object[]{
			member.getPassword(), member.getEmail(), member.getName(),
			member.getPhoneNumber(), member.getProfileImageUrl(),
			member.getBirthDate()
		};

		this.jdbcTemplate.update(insertMemberQuery, insertMemberParam);

		String lastInsertIdQuery = "select last_insert_id()";
		return this.jdbcTemplate.queryForObject(lastInsertIdQuery, Integer.class);

	}

	public Integer isDuplicatedMember(String email, String phoneNumber) {
		String checkQuery = "Select count(id)" +
			" FROM MEMBER" +
			" WHERE email = ? OR phoneNumber = ?";

		Object[] checkParam = new Object[]{email, phoneNumber};

		return this.jdbcTemplate.queryForObject(checkQuery, int.class, checkParam);

	}

	public Integer isDuplicatedName(String name) {
		// 닉네임 중복 체크
		String checkQuery = "select count(id)" +
			" FROM MEMBER" +
			" WHERE name = ?";

		return this.jdbcTemplate.queryForObject(checkQuery, int.class, name);

	}

	public Member findByIdPassword(String email, String password) {
		String findQuery = "SELECT *" +
			" FROM MEMBER" +
			" WHERE email = ?" +
			" AND password = ?" +
			" AND status = 'Joined'";
		Object[] findParam = new Object[]{email, password};

		return this.jdbcTemplate.queryForObject(findQuery, Member.class, findParam);
	}

	public Member findMemberById(int memberId) throws Exception {
		log.info("test5");
		String findMemberQuery = "SELECT *" +
			" FROM MEMBER" +
			" WHERE id = ? " +
			" AND status = 'Joined'";
		String findMemberParam = Integer.toString(memberId);
		try {
			return this.jdbcTemplate.queryForObject(findMemberQuery,
				(rs, rowNum) -> new Member(
					rs.getInt("id"),
					rs.getString("email"),
					rs.getString("password"),
					rs.getString("name"),
					rs.getString("phoneNumber"),
					rs.getString("profileImageUrl"),
					rs.getString("mailAccept"),
					rs.getString("smsAccept"),
					rs.getString("birthDate"),
					rs.getString("addressBuildingNum"),
					rs.getString("districtCode"),
					rs.getString("addressDetail"),
					rs.getDouble("latitude"),
					rs.getDouble("longitude"),
					rs.getInt("grade")
				),
				findMemberParam);
		} catch(Exception exception) {
			exception.printStackTrace();
			throw new Exception();

		}
	}

	@Transactional
	public Integer modifyMemberName(int memberId, String name) {
		String modifyQuery = "UPDATE MEMBER SET name = ? WHERE id = ?";
		Object[] modifyParam = new Object[]{memberId, name};

		return this.jdbcTemplate.queryForObject(modifyQuery, int.class, modifyParam);
	}

	@Transactional
	public Integer modifyAcceptEmail(int memberId, String emailStatus) {
		String modifyQuery = "UPDATE MEMBER SET mailAccept = ? WHERE id = ?";
		Object[] modifyParam = new Object[]{memberId, emailStatus};

		this.jdbcTemplate.update(modifyQuery, modifyParam);

		return memberId;
	}

	@Transactional
	public Integer modifyAcceptSms(int memberId, String smsStatus) {
		String modifyQuery = "UPDATE MEMBER SET smsAccept = ? WHERE id = ?";
		Object[] modifyParam = new Object[]{memberId, smsStatus};

		this.jdbcTemplate.update(modifyQuery, modifyParam);

		return memberId;
	}

//	public List<Member> findCartByMemberId(int memberId) {
//		String findCartQuery = "SELECT name, price, amount " +
//			"FROM MEMBER " +
//			"WHERE memberId = ? AND status = 'Used'";
//
//		String findCartParam = Integer.toString(memberId);

//		return jdbcTemplate.query(findCartQuery,
//			(rs, rowNum) -> (
//
//				),
//			findCartParam)
//}
}

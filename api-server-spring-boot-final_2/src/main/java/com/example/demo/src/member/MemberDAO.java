package com.example.demo.src.member;

import com.example.demo.config.BaseException;
import com.example.demo.src.member.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Repository
@Transactional(readOnly = false)
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
	public Integer insertMember(MemberDTO member) throws Exception {
		String insertMemberQuery = "insert into MEMBER(password, email, name, phoneNumber, profileImageUrl," +
			" addressBuildingNum, districtCode, birthDate)" +
			" VALUES(?, ?, ?, ?, ?, 0, 0, ?)";

		String lastInsertIdQuery = "select last_insert_id()";

		Object[] insertMemberParam = new Object[]{
			member.getPassword(), member.getEmail(), member.getName(),
			member.getPhoneNumber(), member.getProfileImageUrl(),
			member.getBirthDate()
		};

		try {
			this.jdbcTemplate.update(insertMemberQuery, insertMemberParam);
			return this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new Exception();
		}

	}

	/**
	 * 멤버 등급 삽입
	 * @param memberId
	 * @return
	 * @throws BaseException
	 */
	@Transactional
	public void insertMemberLevel(int memberId) throws BaseException {
		String insertLevelQuery = "INSERT INTO MEMBER_LEVEL(memberId) " +
			"VALUES (?)";

		String insertLevelParam = Integer.toString(memberId);

		try {
			this.jdbcTemplate.update(insertLevelQuery, insertLevelParam);
		} catch(Exception exception) {
			exception.printStackTrace();
			throw new BaseException(DATABASE_ERROR);
		}

	}

//	public int createUser(PostUserReq postUserReq){
//		String createUserQuery = "insert into UserInfo (userName, ID, password, email) VALUES (?,?,?,?)";
//		Object[] createUserParams = new Object[]{postUserReq.getUserName(), postUserReq.getId(), postUserReq.getPassword(), postUserReq.getEmail()};
//		this.jdbcTemplate.update(createUserQuery, createUserParams);
//
//		String lastInserIdQuery = "select last_insert_id()";
//		return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
//	}

	// TODO: 중복 체크는 여기서 하는거 아님
	public Integer isDuplicatedMember(String email, String phoneNumber) {
		String checkQuery = "Select count(id)" +
			" FROM MEMBER" +
			" WHERE email = ? OR phoneNumber = ?";

		Object[] checkParam = new Object[]{email, phoneNumber};

		return this.jdbcTemplate.queryForObject(checkQuery, int.class, checkParam);

	}

	// TODO: 중복 체크는 여기서 하는거 아님
	public Integer isDuplicatedName(String name) {
		// 닉네임 중복 체크
		String checkQuery = "select count(id)" +
			" FROM MEMBER" +
			" WHERE name = ?";

		return this.jdbcTemplate.queryForObject(checkQuery, int.class, name);
	}


//	public Integer findByIdPassword(String email, String password) throws Exception {
//		String findQuery = "SELECT exists(" +
//			" SELECT id FROM MEMBER WHERE email = ?" +
//			" AND password = ? AND status = 'Joined')";
//		Object[] findParam = new Object[]{email, password};
//
//		try {
//			return this.jdbcTemplate.queryForObject(findQuery, int.class, findParam);
//		} catch (Exception exception) {
//			exception.printStackTrace();
//			throw new Exception(exception);
//		}
//	}

	public MemberDTO findMemberById(int memberId) throws BaseException {

		String findMemberIdQuery = "SELECT * FROM MEMBER " +
			"WHERE id = ? AND status = 'Joined'";

		String findMemberIdParam = Integer.toString(memberId);

		return getMember(findMemberIdQuery, findMemberIdParam);
	}



	public MemberDTO findMemberBy(String field, MemberReq memberReq) throws BaseException {

		String findMemberQuery = "SELECT * FROM MEMBER WHERE "
									+ field + " = ? AND status = 'Joined'";
		String findMemberParam = null;

		if (field.equals("email")) {
			findMemberParam = memberReq.getEmail();
		} else {
			throw new BaseException(DATABASE_ERROR);
		}

		return getMember(findMemberQuery, findMemberParam);
	}

	// 사용자 멤버십 등급 정보 출력
	public MemberLevelDTO findLevelById(int memberId) throws BaseException {
		String findByIdQuery = "SELECT * FROM MEMBER_LEVEL " +
			"WHERE memberId = ? AND status = 'Used'";
		String findByNameParam = Integer.toString(memberId);

		try {
			return this.jdbcTemplate.queryForObject(findByIdQuery,
				(rs, rowNum) -> MemberLevelDTO.builder()
					.membershipName(rs.getString("membershipName"))
					.orderCount(rs.getInt("orderCount"))
					.build()
				, findByNameParam);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new BaseException(DATABASE_ERROR);
		}
	}

	// 활성화 된 멤버십 등급 출력
	public List<MembershipDTO> findReqCountByName(String membershipName) throws BaseException {
		String findReqCountQuery = "SELECT * FROM MEMBERSHIP WHERE status = 'Used'";

		try {
			return this.jdbcTemplate.query(findReqCountQuery,
				(rs, rowNum) -> MembershipDTO.builder()
					.name(rs.getString("name"))
					.build());
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new BaseException(DATABASE_ERROR);
		}
	}


//	public MemberDTO getPwd(MemberReq memberReq) {
//		String getPwdQuery = "select id, email, password, email, name FROM MEMBER" +
//			" WHERE email = ? AND status = 'Joined'";
//		String getPwdParams = memberReq.getEmail();
//
//		// ****POINT_1
//		return this.jdbcTemplate.queryForObject(getPwdQuery,
//			(rs, rowNum) -> MemberDTO.builder()
//				.id(rs.getInt("id"))
//				.email(rs.getString("email"))
//				.password(rs.getString("password"))
//				.email(rs.getString("email"))
//				.build(), getPwdParams);
//	}

	@Transactional
	public Integer updateMemberName(int memberId, MemberReq memberReq) throws Exception {

		String modifyQuery = "UPDATE MEMBER SET name = ? WHERE id = ?";
		Object[] modifyParam = new Object[]{memberReq.getName(), memberId};

		try {
			return this.jdbcTemplate.update(modifyQuery, modifyParam);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new Exception(exception);
		}
	}

	@Transactional
	public Integer updateMemberPwd(int memberId, MemberReq memberReq) {
		String modifyQuery = "UPDATE MEMBER SET password = ? WHERE id = ?";
		Object[] modifyParam = new Object[]{memberReq.getPassword(), memberId};

		return this.jdbcTemplate.update(modifyQuery, modifyParam);

	}

	@Transactional
	public Integer updateAcceptEmail(int memberId, String emailStatus) {
		String modifyQuery = "UPDATE MEMBER SET mailAccept = ? WHERE id = ?";
		Object[] modifyParam = new Object[]{emailStatus, memberId};

		return this.jdbcTemplate.update(modifyQuery, modifyParam);

	}

	@Transactional
	public Integer updateAcceptSms(int memberId, String smsStatus) {
		String modifyQuery = "UPDATE MEMBER SET smsAccept = ? WHERE id = ?";
		Object[] modifyParam = new Object[]{smsStatus, memberId};

		return this.jdbcTemplate.update(modifyQuery, modifyParam);

	}

	@Transactional
	public Integer deleteMember(int memberId) throws BaseException {
		String deleteMemberQuery = "UPDATE MEMBER SET status = 'Deleted' WHERE id = ?";
		String deleteMemberParam = Integer.toString(memberId);

		try {
			return this.jdbcTemplate.update(deleteMemberQuery, deleteMemberParam);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new BaseException(DATABASE_ERROR);
		}
	}


	private MemberDTO getMember(String findMemberIdQuery, String findMemberIdParam) throws BaseException {
		try {
			return this.jdbcTemplate.queryForObject(findMemberIdQuery,
				(rs, rowNum) -> MemberDTO.builder()
					.id(rs.getInt("id"))
					.email(rs.getString("email"))
					.password(rs.getString("password"))
					.name(rs.getString("name"))
					.phoneNumber(rs.getString("phoneNumber"))
					.profileImageUrl(rs.getString("profileImageUrl"))
					.mailAccept(rs.getString("mailAccept"))
					.smsAccept(rs.getString("smsAccept"))
					.birthDate(rs.getString("birthDate"))
					.addressBuildingNum(rs.getString("addressBuildingNum"))
					.districtCode(rs.getString("districtCode"))
					.addressDetail(rs.getString("addressDetail"))
					.latitude(rs.getDouble("latitude"))
					.longitude(rs.getDouble("longitude"))
					.grade(rs.getString("grade"))
					.build(),
				findMemberIdParam);

		} catch (Exception exception) {
			exception.printStackTrace();
			throw new BaseException(DATABASE_ERROR);
		}
	}



//	public List<MemberDTO> findCartByMemberId(int memberId) {
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

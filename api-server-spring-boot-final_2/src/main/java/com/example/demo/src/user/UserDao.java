package com.example.demo.src.user;

import com.example.demo.src.user.model.*;
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

	public int modifyUserName(PatchUserReq patchUserReq) {
		String modifyUserNameQuery = "update MEMBER set userName = ? where id = ?";
		Object[] modifyUserNameParams = new Object[] {patchUserReq.getName(), patchUserReq.getId()};

		return this.jdbcTemplate.update(modifyUserNameQuery, modifyUserNameParams);
	}

	public List<GetUserBasketRes> getUserBasket(int userId, int basketId) {
		String getUserBasketQuery = "SELECT S.name, M.name, SB.amount, (M.price * SB.amount) AS menuPrice FROM STORE_BASKET SB JOIN(SELECT id, name, price FROM MENU) AS M ON M.id = SB.menuId JOIN(SELECT id, name FROM STORE) AS S ON S.id = SB.storeId WHERE status = 'Used' AND memberId = ?";
		String getUserBasketParams = Integer.toString(userId);
		return this.jdbcTemplate.query(getUserBasketQuery,
			(rs, rowNum) -> new GetUserBasketRes(
				rs.getString("S.name"),
				rs.getString("M.name"),
				rs.getInt("amount"),
				rs.getInt("menuPrice")),

			getUserBasketParams);

	}

	public Integer postUserBasket(int userId, PostUserBasketReq postUserBasketReq) {
		String postUserBasketQuery = "insert into STORE_BASKET(memberId, storeId, menuId, amount) " +
			"VALUES (?, ?, ?, ?)";
		Object[] postUserBasketParams = new Object[] {userId, postUserBasketReq.getStoreId(),
															postUserBasketReq.getMenuId(), postUserBasketReq.getAmount() };
		this.jdbcTemplate.update(postUserBasketQuery, postUserBasketParams);

		String lastIntegerIdQuery = "select last_insert_id()";
		return this.jdbcTemplate.queryForObject(lastIntegerIdQuery, Integer.class);
	}

	public int modifyAmount(PatchUserBasketReq patchUserBasketReq) {
		String modifyAmountQuery = "update STORE_BASKET set amount = ? where id = ?";
		Object[] modifyAmountParams = new Object[]{patchUserBasketReq.getAmount(), patchUserBasketReq.getBasketId()};

		return this.jdbcTemplate.update(modifyAmountQuery, modifyAmountParams);
	}


//	public void getOrderDetail(int userId, int orderId) {
//		String getOrderDetailQuery = "SELECT S.name, M.name, SB.amount, (M.price * SB.amount) AS menuPrice FROM STORE_BASKET SB JOIN(SELECT id, name, price FROM MENU) AS M ON M.id = SB.menuId JOIN(SELECT id, name FROM STORE) AS S ON S.id = SB.storeId WHERE status = 'Used' AND memberId = ?";
//		String getOrderDetailParams =
//		return this.jdbcTemplate.query(getUserBasketQuery,
//			(rs, rowNum) -> new GetUserBasketRes(
//				rs.getString("S.name"),
//				rs.getString("M.name"),
//				rs.getInt("amount"),
//				rs.getInt("menuPrice")),
//
//			getUserBasketParams);
//
//	}

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

package com.example.demo.cart;

import com.example.demo.cart.model.Cart;
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
public class CartDAO {

	private JdbcTemplate jdbcTemplate;

	@PersistenceContext
	private EntityManager em;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}


	public List<Cart> findCartByMemberId(int memberId) {
		String findQuery = "SELECT *" +
			" FROM CART" +
			" WHERE memberId = ? " +
			" AND status = 'Used'";

		String findParam = Integer.toString(memberId);

		return this.jdbcTemplate.query(findQuery,
			(rs, rowNum) -> new Cart(
				rs.getInt("id"),
				rs.getInt("memberId"),
				rs.getInt("menuId"),
				rs.getInt("amount"),
				rs.getInt("storeId")
			),
			findParam);
	}


	public Cart peekByMemberId(int memberId) {
		String peekIdQuery = "SELECT *" +
			" FROM CART " +
			" WHERE memberId = ?" +
			" AND status = 'Used'" +
			" LIMIT 1";
		String peekIdParam = Integer.toString(memberId);

		return this.jdbcTemplate.queryForObject(peekIdParam,
			(rs, rowNum) -> new Cart(
				rs.getInt("id"),
				rs.getInt("memberId"),
				rs.getInt("menuId"),
				rs.getInt("amount"),
				rs.getInt("storeId")
			),
			peekIdParam);
	}

	public Integer insertCart(int memberId, Cart cart) {
		String insertCartQuery = "INSERT INTO CART(memberId, menuId, amount, storeId)" +
			" VALUES(?, ?, ?, ?)";
		Object[] insertCartParam = new Object[]{
			memberId,
			cart.getMenuId(),
			cart.getAmount(),
			cart.getStoreId()
		};
		this.jdbcTemplate.update(insertCartQuery, insertCartParam);

		String lastInsertIdQuery = "select last_insert_id()";
		return this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);

	}
}

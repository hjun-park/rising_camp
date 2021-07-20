package com.example.demo.src.cart;

import com.example.demo.src.cart.model.Cart;
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
@Transactional(readOnly = false)
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
		log.info("@@@##{}", memberId);

		String peekIdQuery = "SELECT *" +
			" FROM CART " +
			" WHERE memberId = ?" +
			" AND status = 'Used'" +
			" LIMIT 1";
		String peekIdParam = Integer.toString(memberId);

		return this.jdbcTemplate.queryForObject(peekIdQuery,
			(rs, rowNum) -> new Cart(
				rs.getInt("id"),
				rs.getInt("memberId"),
				rs.getInt("menuId"),
				rs.getInt("amount"),
				rs.getInt("storeId")
			),
			peekIdParam);
	}

	public Integer insertCart(int memberId, Cart cart) throws Exception{
		try {
			String insertCartQuery = "INSERT INTO CART(memberId, menuId, amount, storeId)" +
				" VALUES(?, ?, ?, ?)";
			Object[] insertCartParam = new Object[]{
				memberId,
				cart.getMenuId(),
				cart.getAmount(),
				cart.getStoreId()
			};
			this.jdbcTemplate.update(insertCartQuery, insertCartParam);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new Exception(exception);
		}

		String lastInsertIdQuery = "select last_insert_id()";
		return this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);

	}

	public int modifyCartAmount(int memberId, int cartId, int amount) {
		String modifyCartQuery = "UPDATE CART SET amount = ?" +
			" WHERE memberId = ? AND id = ? AND status = 'Used'";
		Object[] modifyCartParam = new Object[] {
			amount, memberId, cartId
		};

		return this.jdbcTemplate.update(modifyCartQuery, modifyCartParam);

	}

	public int deleteCarts(int memberId) {
		String deleteCartsQuery = "UPDATE CART SET status = 'Deleted'" +
			" WHERE memberId = ?";
		String deleteCartsParam = Integer.toString(memberId);

		return this.jdbcTemplate.update(deleteCartsQuery, deleteCartsParam);
	}

	public int deleteCart(int memberId, int cartId) {
		String deleteCartQuery = "UPDATE CART SET status = 'Deleted'" +
			" WHERE memberId = ? AND id = ?";
		Object[] deleteCartParam = new Object[] {
			memberId, cartId
		};

		return this.jdbcTemplate.update(deleteCartQuery, deleteCartParam);
	}
}

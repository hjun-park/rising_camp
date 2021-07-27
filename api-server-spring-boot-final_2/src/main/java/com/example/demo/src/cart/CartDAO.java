//package com.example.demo.src.cart;
//
//import com.example.demo.config.BaseException;
//import com.example.demo.config.BaseResponseStatus;
//import com.example.demo.src.cart.model.Cart;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.sql.DataSource;
//import java.util.List;
//
//import static com.example.demo.config.BaseResponseStatus.*;
//
//@Repository
//@Transactional(readOnly = true)
//@Slf4j
//public class CartDAO {
//
//	@PersistenceContext
//	private EntityManager em;
//
//	@Transactional
//	public Long insertCart(Cart cart) throws BaseException {
//		try {
//			em.persist(cart);
//			return cart.getId();
//
//		} catch (Exception exception) {
//			exception.printStackTrace();
//			throw new BaseException(DATABASE_ERROR);
//		}
//	}
//
//
//	@Query
//	public List<Cart> findCartByMemberId(int memberId) {
//	}
//
//
//	public Cart peekByMemberId(int memberId) {
//		log.info("@@@##{}", memberId);
//
//		String peekIdQuery = "SELECT *" +
//			" FROM CART " +
//			" WHERE memberId = ?" +
//			" AND status = 'Used'" +
//			" LIMIT 1";
//		String peekIdParam = Integer.toString(memberId);
//
//		return this.jdbcTemplate.queryForObject(peekIdQuery,
//			(rs, rowNum) -> new Cart(
//				rs.getInt("id"),
//				rs.getInt("memberId"),
//				rs.getInt("menuId"),
//				rs.getInt("amount"),
//				rs.getInt("storeId")
//			),
//			peekIdParam);
//	}
//
//
//	public int modifyCartAmount(int memberId, int cartId, int amount) {
//		String modifyCartQuery = "UPDATE CART SET amount = ?" +
//			" WHERE memberId = ? AND id = ? AND status = 'Used'";
//		Object[] modifyCartParam = new Object[] {
//			amount, memberId, cartId
//		};
//
//		return this.jdbcTemplate.update(modifyCartQuery, modifyCartParam);
//
//	}
//
//	public int deleteCarts(int memberId) {
//		String deleteCartsQuery = "UPDATE CART SET status = 'Deleted'" +
//			" WHERE memberId = ?";
//		String deleteCartsParam = Integer.toString(memberId);
//
//		return this.jdbcTemplate.update(deleteCartsQuery, deleteCartsParam);
//	}
//
//	public int deleteCart(int memberId, int cartId) {
//		String deleteCartQuery = "UPDATE CART SET status = 'Deleted'" +
//			" WHERE memberId = ? AND id = ?";
//		Object[] deleteCartParam = new Object[] {
//			memberId, cartId
//		};
//
//		return this.jdbcTemplate.update(deleteCartQuery, deleteCartParam);
//	}
//}

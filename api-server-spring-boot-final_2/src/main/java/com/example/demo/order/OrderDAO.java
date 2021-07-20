package com.example.demo.order;

import com.example.demo.order.model.OrderItem;
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
public class OrderDAO {

	private JdbcTemplate jdbcTemplate;

	@PersistenceContext
	private EntityManager em;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

//	public List<OrderItem> findOrderItems(int orderId) {
//		String findOrderQuery = "SELECT name, amount, price " +
//			"FROM ORDER_ITEM OI " +
//			"INNER JOIN (SELECT id FROM ORDERS) O " +
//			"ON OI.orderId = O.id " +
//			"INNER JOIN (SELECT id, name, price FROM MENU) M " +
//			"ON OI.menuId = M.id " +
//			"WHERE orderId = ? and status = 'Used'";
//		String findOrderParam = Integer.toString(orderId);
//
//		return this.jdbcTemplate.query(findOrderQuery,
//			(rs, rowNum) -> new OrderItem(
//				rs.getString("name"),
//				rs.getInt("amount"),
//				rs.getInt("price")),
//			findOrderParam);
//
//	}

//	private static final class ActorMapper implements RowMapper<OrderItemDTO> {
//
//		public Actor mapRow(ResultSet rs, int rowNum) throws SQLException {
//			Actor actor = new Actor();
//			actor.setFirstName(rs.getString("first_name"));
//			actor.setLastName(rs.getString("last_name"));
//			return actor;
//		}
//	}

}

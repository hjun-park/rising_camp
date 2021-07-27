package com.example.demo.src.order;

import com.example.demo.config.BaseException;
import com.example.demo.src.order.model.Orders;
import com.example.demo.src.order.model.OrderItem;
import com.example.demo.src.order.model.OrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Repository
@Transactional(readOnly = false)
@Slf4j
public class OrderDAO {

	private JdbcTemplate jdbcTemplate;

	@PersistenceContext
	private EntityManager em;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/*
		CREATE ORDER
	 */
	public int createOrder(int memberId, OrderRequest orderRequest) throws Exception {
		try {
			String orderQuery = "INSERT INTO ORDERS(storeId, memberId, addressBuildingNum," +
				" addressDetail, tips, storeRequest, riderRequest)" +
				" VALUES(?, ?, ?, ?, ?, ?, ?)";
			Object[] orderParam = new Object[]{
				orderRequest.getStoreId(),
				memberId,
				orderRequest.getAddressBuildingNum(),
				orderRequest.getAddressDetail(),
				orderRequest.getTips(),
				orderRequest.getStoreRequest(),
				orderRequest.getRiderRequest()
			};

			return this.jdbcTemplate.update(orderQuery, orderParam);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new Exception(exception);
		}
	}


	/*
		SELECT ORDER
	 */

	// 멤버ID -> 주문내역 획득
	public List<Orders> findOrdersById(int memberId) throws BaseException {
		String findOrderQuery = "SELECT * FROM ORDERS " +
			"WHERE memberid = ?";
		String findOrderParam = Integer.toString(memberId);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		try {
			log.info("14");
			return this.jdbcTemplate.query(findOrderQuery,
				(rs, rowNum) -> Orders.builder()
					.id(rs.getInt("id"))
					.storeId(rs.getInt("storeId"))
					.memberId(rs.getInt("memberId"))
					.addressBuildingNum(rs.getString("addressBuildingNum"))
					.addressDetail(rs.getString("addressDetail"))
					.tips(rs.getInt("tips"))
					.status(Orders.Status.valueOf(rs.getString("status")))
					.storeRequest(rs.getString("storeRequest"))
					.riderRequest(rs.getString("riderRequest"))
					.riderId(rs.getInt("riderId"))
					.orderTime(LocalDateTime.parse(rs.getString("orderTime"), formatter))
					.build()
				,findOrderParam
			);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new BaseException(DATABASE_ERROR);
		}

	}

	// 주문ID -> 주문메뉴 리스트 획득
	public List<OrderItem> findItemsByOrderId(int orderId) { // throws BaseException {
		String findItemsQuery = "SELECT * FROM ORDER_ITEM " +
			"WHERE orderId = ? AND status = 'Used'";
		String findItemsParam = Integer.toString(orderId);

//		try {
		return this.jdbcTemplate.query(findItemsQuery,
			(rs, rowNum) -> OrderItem.builder()
			.id(rs.getInt("id"))
			.orderId(rs.getInt("orderId"))
			.amount(rs.getInt("amount"))
			.menuId(rs.getInt("menuId"))
			.build()
			, findItemsParam);

//		} catch (Exception exception) {
//			exception.printStackTrace();
//			throw new BaseException(DATABASE_ERROR);
//		}

	}



	/*
		UPDATE ORDER
	 */



	/*
		DELETE ORDER
	 */

}

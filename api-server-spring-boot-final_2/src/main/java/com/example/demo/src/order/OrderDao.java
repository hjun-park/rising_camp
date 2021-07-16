package com.example.demo.src.order;

import com.example.demo.src.order.model.PatchOrderDetailReq;
import com.example.demo.src.user.model.PatchUserBasketReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
@Slf4j
public class OrderDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}


	public int deleteOrderDetail(PatchOrderDetailReq patchOrderDetailReq) {
		String deleteOrderDetailQuery = "update ORDERS set status = 'Deleted' where id = ?";
		int deleteOrderDetailParam = patchOrderDetailReq.getOrderId();

		return this.jdbcTemplate.update(deleteOrderDetailQuery, deleteOrderDetailParam);

	}
}

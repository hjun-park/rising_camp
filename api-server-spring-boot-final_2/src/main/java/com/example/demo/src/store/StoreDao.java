package com.example.demo.src.store;

import com.example.demo.src.store.model.GetMenuRes;
import com.example.demo.src.store.model.GetStoreInfoRes;
import com.example.demo.src.store.model.GetStoreRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
@Slf4j
public class StoreDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}


	public GetStoreRes getStore(int storeId) {
		String getStoreQuery = "SELECT S.id, (CONCAT('상호명\\t', name)) AS storeName, R.rating AS rating, R.count AS reviewCount, minOrderPrice, paymentMethod, (CONCAT(deliveryTime, '분')) AS deliveryTime FROM STORE S JOIN (select storeId, rating, count(rating) as count from REVIEW) AS R ON R.storeId = S.id WHERE status = 'Used' AND S.id = ?";
		int getStoreParam = storeId;

		return this.jdbcTemplate.queryForObject(getStoreQuery,
			(rs, rowNum) -> new GetStoreRes(
				rs.getInt("S.id"),
				rs.getString("storeName"),
				rs.getDouble("rating"),
				rs.getInt("reviewCount"),
				rs.getInt("minOrderPrice"),
				rs.getString("paymentMethod"),
				rs.getString("deliveryTime")
			),
			getStoreParam);
	}

	public GetStoreInfoRes getStoreInfo(int storeId) {
		String getStoreInfoQuery = "select S.id, storeImageUrl, info, hours, closedDay, phoneNumber, deliveryArea, notice, minOrderPrice, tips, address from STORE S WHERE S.id = 3";
		int getStoreInfoParam = storeId;

		return this.jdbcTemplate.queryForObject(getStoreInfoQuery,
			(rs, rowNum) -> new GetStoreInfoRes(
				rs.getInt("S.id"),
				rs.getString("storeImageUrl"),
				rs.getString("info"),
				rs.getString("hours"),
				rs.getString("closedDay"),
				rs.getString("phoneNumber"),
				rs.getString("deliveryArea"),
				rs.getString("notice"),
				rs.getInt("minOrderPrice"),
				rs.getInt("tips"),
				rs.getString("address")),
			getStoreInfoParam);
	}

	public GetMenuRes getMenu(int storeId, int menuId) {
		String getMenuQuery = "select M.id, name, price, (select amount from STORE_BASKET where menuId=4) AS amount, (select minOrderPrice from STORE where id=?) as minOrderPrice\n" +
			"from MENU M\n" +
			"WHERE M.id = ?;";
		Object[] getMenuParams = new Object[]{storeId, menuId};

		return this.jdbcTemplate.queryForObject(getMenuQuery,
			(rs, rowNum) -> new GetMenuRes(
				rs.getInt("M.id"),
				rs.getString("name"),
				rs.getInt("price"),
				rs.getInt("amount"),
				rs.getInt("minOrderPrice")),
			getMenuParams);
	}

}

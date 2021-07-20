package com.example.demo.src.store;

import com.example.demo.src.store.model.StoreDTO;
import com.example.demo.src.store.model.StoreInfoDTO;
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
@Transactional
@Slf4j
public class StoreDAO {

	private JdbcTemplate jdbcTemplate;

	@PersistenceContext
	private EntityManager em;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public StoreDTO findStoreById(int storeId) throws Exception {
		String findStoreQuery = "SELECT *" +
			" FROM STORE" +
			" WHERE id = ? " +
			" AND status = 'Used'";
		String findStoreParam = Integer.toString(storeId);
		try {
			return this.jdbcTemplate.queryForObject(findStoreQuery,
				(rs, rowNum) -> new StoreDTO(
					rs.getInt("id"),
					rs.getString("storeCategoryName"),
					rs.getString("name"),
					rs.getString("notice"),
					rs.getString("info"),
					rs.getString("paymentMethod"),
					rs.getString("deliveryType"),
					rs.getString("originInform"),
					rs.getString("address"),
					rs.getString("phoneNumber"),
					rs.getString("hours"),
					rs.getString("addressBuildingNum"),
					rs.getString("districtCode"),
					rs.getString("addressDetail"),
					rs.getString("deliveryTime"),
					rs.getString("closedDay"),
					rs.getString("deliveryArea"),
					rs.getInt("minOrderPrice"),
					rs.getInt("tips"),
					rs.getString("storeImageUrl"),
					StoreDTO.Status.valueOf(rs.getString("status"))
				),
				findStoreParam);
		} catch(Exception exception) {
			exception.printStackTrace();
			throw new Exception();

		}
	}

	public Integer insertStore(StoreDTO storeDTO) throws Exception{
		try {
			String insertStoreQuery = "INSERT INTO STORE (storeCategoryName, name, notice, info," +
				" paymentMethod, deliveryType, originInform, address, phoneNumber," +
				" hours, addressBuildingNum, districtCode, addressDetail," +
				" deliveryTime, closedDay, deliveryArea, minOrderPrice, tips, storeImageUrl)" +
				" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			Object[] insertStoreParam = new Object[]{
				storeDTO.getStoreCategoryName(),
				storeDTO.getName(),
				storeDTO.getNotice(),
				storeDTO.getInfo(),
				storeDTO.getPaymentMethod(),
				storeDTO.getDeliveryType(),
				storeDTO.getOriginInform(),
				storeDTO.getAddress(),
				storeDTO.getPhoneNumber(),
				storeDTO.getHours(),
				storeDTO.getAddressBuildingNum(),
				storeDTO.getDistrictCode(),
				storeDTO.getAddressDetail(),
				storeDTO.getDeliveryTime(),
				storeDTO.getClosedDay(),
				storeDTO.getDeliveryArea(),
				storeDTO.getMinOrderPrice(),
				storeDTO.getTips(),
				storeDTO.getStoreImageUrl()
			};
			this.jdbcTemplate.update(insertStoreQuery, insertStoreParam);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new Exception(exception);
		}

		String lastInsertIdQuery = "select last_insert_id()";
		return this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);

	}

	public int deleteStoreById(int storeId) throws Exception{
		try {
			String deleteStoreQuery = "UPDATE STORE SET status = 'Deleted'" +
				" WHERE id = ?";
			String deleteStoreParam = Integer.toString(storeId);

			return this.jdbcTemplate.update(deleteStoreQuery, deleteStoreParam);
		} catch(Exception exception) {
			exception.printStackTrace();
			throw new Exception(exception);
		}
	}

	public List<StoreDTO> findStoreByCategoryName(String categoryName) throws Exception {
		String findStoreQuery = "SELECT * FROM STORE" +
			" WHERE storeCategoryName = ? AND status = 'Used'";
		String findStoreParam = categoryName;
		try {
			return this.jdbcTemplate.query(findStoreQuery,
				(rs, rowNum) -> new StoreDTO(
					rs.getInt("id"),
					rs.getString("storeCategoryName"),
					rs.getString("name"),
					rs.getString("notice"),
					rs.getString("info"),
					rs.getString("paymentMethod"),
					rs.getString("deliveryType"),
					rs.getString("originInform"),
					rs.getString("address"),
					rs.getString("phoneNumber"),
					rs.getString("hours"),
					rs.getString("addressBuildingNum"),
					rs.getString("districtCode"),
					rs.getString("addressDetail"),
					rs.getString("deliveryTime"),
					rs.getString("closedDay"),
					rs.getString("deliveryArea"),
					rs.getInt("minOrderPrice"),
					rs.getInt("tips"),
					rs.getString("storeImageUrl"),
					StoreDTO.Status.valueOf(rs.getString("status"))
				),
				findStoreParam);
		} catch(Exception exception) {
			exception.printStackTrace();
			throw new Exception();

		}
	}

}

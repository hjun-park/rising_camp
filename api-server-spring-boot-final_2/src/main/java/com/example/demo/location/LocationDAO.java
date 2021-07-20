package com.example.demo.location;


import com.example.demo.location.model.Policy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

@Repository
@Transactional(readOnly = false)
@Slf4j
public class LocationDAO {

	private JdbcTemplate jdbcTemplate;

	@PersistenceContext
	private EntityManager em;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Integer insertZone(int storeId, Policy policy) throws Exception{
		try {
			String insertZoneQuery = "INSERT INTO DELIVERY_POLICY(storeId, districtCode, additionalTips)" +
				" VALUES(?, ?, ?)";
			Object[] insertZoneParam = new Object[]{
				storeId,
				policy.getDistrictCode(),
				policy.getAdditionalTips()
			};
			this.jdbcTemplate.update(insertZoneQuery, insertZoneParam);

		} catch (Exception exception) {
			exception.printStackTrace();
			throw new Exception(exception);
		}

		String lastInsertIdQuery = "select last_insert_id()";
		return this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);
	}
}


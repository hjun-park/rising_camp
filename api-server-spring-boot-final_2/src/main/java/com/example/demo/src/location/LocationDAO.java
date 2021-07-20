package com.example.demo.src.location;


import com.example.demo.src.location.model.Policy;
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
public class LocationDAO {

	private JdbcTemplate jdbcTemplate;

	@PersistenceContext
	private EntityManager em;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Integer insertPolicy(int storeId, Policy policy) throws Exception{
		try {
			String insertPolicyQuery = "INSERT INTO DELIVERY_POLICY(storeId, districtCode, additionalTips)" +
				" VALUES(?, ?, ?)";
			Object[] insertPolicyParam = new Object[]{
				storeId,
				policy.getDistrictCode(),
				policy.getAdditionalTips()
			};
			this.jdbcTemplate.update(insertPolicyQuery, insertPolicyParam);

		} catch (Exception exception) {
			exception.printStackTrace();
			throw new Exception(exception);
		}

		String lastInsertIdQuery = "select last_insert_id()";
		return this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);
	}

	public List<Policy> findPolicyByStoreId(int storeId) throws Exception {
		String findPolicyQuery = "SELECT * FROM DELIVERY_POLICY" +
			" WHERE status = 'Used' AND storeId = ?";
		String findPolicyParam = Integer.toString(storeId);

		try {
			return this.jdbcTemplate.query(findPolicyQuery,
				(rs, rowNum) -> new Policy(
					rs.getInt("id"),
					rs.getInt("storeId"),
					rs.getString("districtCode"),
					rs.getInt("additionalTips"),
					Policy.Status.valueOf(rs.getString("status"))
				),
				findPolicyParam);

		} catch (Exception exception) {
			exception.printStackTrace();
			throw new Exception(exception);
		}
	}

	public int modifyTipsById(int storeId, int policyId, int tips) throws Exception{
		String modifyPolicyTipsQuery = "UPDATE DELIVERY_POLICY SET additionalTips = ?" +
			" WHERE storeId = ? AND id = ? AND status = 'Used'";
		Object[] modifyPolicyTipsParam = new Object[] {
			tips, storeId, policyId
		};
		try {
			return this.jdbcTemplate.update(modifyPolicyTipsQuery, modifyPolicyTipsParam);
		} catch(Exception exception) {
			exception.printStackTrace();
			throw new Exception(exception);
		}

	}

	public int deletePolicyById(int storeId, int policyId) throws Exception {
		try {
			String deleteCartQuery = "UPDATE DELIVERY_POLICY SET status = 'Deleted'" +
				" WHERE storeId = ? AND id = ? AND status = 'Used'";
			Object[] deleteCartParam = new Object[]{
				storeId, policyId
			};

			return this.jdbcTemplate.update(deleteCartQuery, deleteCartParam);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new Exception(exception);
		}
	}
}


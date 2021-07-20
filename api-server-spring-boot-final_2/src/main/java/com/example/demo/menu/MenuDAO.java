package com.example.demo.menu;

import com.example.demo.menu.model.Menu;
import com.example.demo.src.member.model.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

@Repository
@Transactional(readOnly = true)
@Slf4j
public class MenuDAO {

	private JdbcTemplate jdbcTemplate;

	@PersistenceContext
	private EntityManager em;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Menu findMenuById(int menuId) {
		String findQuery = "SELECT *" +
			" FROM MENU" +
			" WHERE id = ? " +
			" AND status = 'full'";

		String findParam = Integer.toString(menuId);


		return this.jdbcTemplate.queryForObject(findQuery,
			(rs, rowNum) -> new Menu(
				rs.getInt("id"),
				rs.getString("name"),
				rs.getInt("price"),
				rs.getString("picture"),
				rs.getString("content"),
				rs.getInt("menuGroupId"),
				rs.getInt("isSignature")
			),
			findParam);

	}


}

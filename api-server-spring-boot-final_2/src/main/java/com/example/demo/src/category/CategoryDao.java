package com.example.demo.src.category;

import com.example.demo.src.category.model.GetCategoryRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Slf4j
@Repository
public class CategoryDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<GetCategoryRes> getCategory() {
		String getCategoryQuery = "select name, imageUrl from STORE_CATEGORY where status = 'Used'";
		return this.jdbcTemplate.query(getCategoryQuery,
			(rs,rowNum) -> new GetCategoryRes(
				rs.getString("name"),
				rs.getString("imageUrl"))
		);
	}
}

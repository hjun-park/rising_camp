package com.example.demo.src.category;

import com.example.demo.src.category.model.Category;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Slf4j
@Repository
public class CategoryDAO {

	private JdbcTemplate jdbcTemplate;


	@Autowired
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Category> getCategory() throws Exception{
		String getCategoryQuery = "SELECT * FROM STORE_CATEGORY WHERE status = 'Used'";

		try {
			return this.jdbcTemplate.query(getCategoryQuery,
				(rs, rowNum) -> new Category(
					rs.getString("name"),
					rs.getString("imageUrl"),
					Category.Status.valueOf(rs.getString("status"))
				));
		} catch(Exception exception) {
			exception.printStackTrace();
			throw new Exception(exception);
		}
	}

	public Integer insertCategory(Category category) throws Exception {
		try {
			String insertCategoryQuery = "INSERT INTO STORE_CATEGORY (name, imageUrl)" +
				" VALUES (?, ?)";
			Object[] insertCategoryParam = new Object[]{
				category.getName(),
				category.getImageUrl()
			};

			this.jdbcTemplate.update(insertCategoryQuery, insertCategoryParam);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new Exception(exception);
		}

		String lastInsertIdQuery = "select last_insert_id()";
		return this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);
	}

	public int deleteCategoryByName(String categoryName) throws Exception {

		String deleteCategoryQuery = "UPDATE STORE_CATEGORY SET status = 'Deleted'" +
			" WHERE name = ?";
		String deleteCategoryParam = categoryName;

		try {
			return this.jdbcTemplate.update(deleteCategoryQuery, deleteCategoryParam);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new Exception(exception);
		}
	}

//	public List<CategoryDTO> findStoresByName(String categoryName) {
//
//	}

}

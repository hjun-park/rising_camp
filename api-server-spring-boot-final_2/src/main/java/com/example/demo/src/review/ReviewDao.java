package com.example.demo.src.review;

import com.example.demo.src.review.model.GetReviewDetailRes;
import com.example.demo.src.review.model.GetReviewRes;
import com.example.demo.src.review.model.PatchReviewReq;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Slf4j
@Repository
public class ReviewDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public GetReviewDetailRes getReviewDetail(int userId, int reviewId) {
		String getReviewDetailQuery = "select id, count(rating) as count, rating, reviewImageUrl, content from REVIEW R where R.id = ? and status = 'Used'";
		int getReviewDetailParam = reviewId;
		return this.jdbcTemplate.queryForObject(getReviewDetailQuery,
			(rs, rowNum) -> new GetReviewDetailRes(
				rs.getInt("id"),
				rs.getInt("count"),
				rs.getDouble("rating"),
				rs.getString("reviewImageUrl"),
				rs.getString("content")),
			getReviewDetailParam
			);
	}

	public GetReviewRes getReviews(int userId) {
		String getReviewQuery = "select id, content, rating, reviewImageUrl from REVIEW WHERE REVIEW.memberId = ?";
		int getReviewParam = userId;
		return this.jdbcTemplate.queryForObject(getReviewQuery,
			(rs, rowNum) -> new GetReviewRes(
				rs.getInt("id"),
				rs.getString("content"),
				rs.getDouble("rating"),
				rs.getString("reviewImageUrl")),
			getReviewParam
			);
	}

	public int deleteReviewDetail(PatchReviewReq patchReviewReq) {
		String deleteReviewDetailQuery = "update REVIEW set status = 'Deleted' where id = ?";
		int deleteReviewDetailParam = patchReviewReq.getReviewId();
		return this.jdbcTemplate.update(deleteReviewDetailQuery, deleteReviewDetailParam);

	}
}

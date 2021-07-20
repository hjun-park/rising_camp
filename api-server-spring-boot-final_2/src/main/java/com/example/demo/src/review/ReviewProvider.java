package com.example.demo.src.review;

import com.example.demo.config.BaseException;
import com.example.demo.src.review.model.GetReviewDetailRes;
import com.example.demo.src.review.model.GetReviewRes;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.*;

@Slf4j
@Service
public class ReviewProvider {

	@Autowired
	private final ReviewDao reviewDao;

	@Autowired
	private final JwtService jwtService;

	public ReviewProvider(ReviewDao reviewDao, JwtService jwtService) {
		this.reviewDao = reviewDao;
		this.jwtService = jwtService;
	}

	public GetReviewDetailRes getReviewDetail(int userId, int reviewId) throws BaseException {
		try {
			GetReviewDetailRes getReviewDetailRes = reviewDao.getReviewDetail(userId, reviewId);
			return getReviewDetailRes;
		} catch (Exception exception) {
			throw new BaseException(DATABASE_ERROR);
		}
	}


	public GetReviewRes getReviews(int userId) throws BaseException {
		try {
			GetReviewRes getReviewRes = reviewDao.getReviews(userId);
			return getReviewRes;
		} catch (Exception exception) {
			throw new BaseException(DATABASE_ERROR);
		}
	}
}

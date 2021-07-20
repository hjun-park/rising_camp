package com.example.demo.src.review;

import com.example.demo.config.BaseException;
import com.example.demo.src.review.model.PatchReviewReq;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;
import static com.example.demo.config.BaseResponseStatus.RESPONSE_ERROR;

@Slf4j
@Service
public class ReviewService {

	@Autowired
	private final ReviewDAO reviewDao;

	@Autowired
	private final ReviewProvider reviewProvider;

	@Autowired
	private final JwtService jwtService;

	public ReviewService(ReviewDAO reviewDao, ReviewProvider reviewProvider, JwtService jwtService) {
		this.reviewDao = reviewDao;
		this.reviewProvider = reviewProvider;
		this.jwtService = jwtService;
	}

	public void deleteReviewDetail(PatchReviewReq patchReviewReq) throws BaseException {
		try {
			int result = reviewDao.deleteReviewDetail(patchReviewReq);
			if (result == 0) {
				throw new BaseException(RESPONSE_ERROR);
			}
		} catch (Exception exception) {
			throw new BaseException(DATABASE_ERROR);
		}

	}
}

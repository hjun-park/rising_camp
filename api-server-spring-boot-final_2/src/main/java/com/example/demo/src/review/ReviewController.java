package com.example.demo.src.review;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.review.model.GetReviewDetailRes;
import com.example.demo.src.review.model.GetReviewRes;
import com.example.demo.src.review.model.PatchReviewReq;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/users/{userId}")
public class ReviewController {

	@Autowired
	private final ReviewProvider reviewProvider;

	@Autowired
	private final ReviewService reviewService;

	@Autowired
	private final JwtService jwtService;

	public ReviewController(ReviewProvider reviewProvider, ReviewService reviewServicee, JwtService jwtService) {
		this.reviewProvider = reviewProvider;
		this.reviewService = reviewServicee;
		this.jwtService = jwtService;
	}

	//11
	@ResponseBody
	@GetMapping("/reviews/{reviewId}")
	public BaseResponse<GetReviewDetailRes> getReviewDetail(@PathVariable("userId") int userId,
															@PathVariable("reviewId") int reviewId) {
		try {
			GetReviewDetailRes getReviewDetailRes = reviewProvider.getReviewDetail(userId, reviewId);
			return new BaseResponse<>(getReviewDetailRes);
		} catch (BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}

	//12
	@ResponseBody
	@GetMapping("/reviews")
	public BaseResponse<GetReviewRes> getReviews(@PathVariable("userId") int userId) {
		try {
			GetReviewRes getReviewRes = reviewProvider.getReviews(userId);
			return new BaseResponse<>(getReviewRes);
		} catch (BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}


	//13
	@ResponseBody
	@PatchMapping("/reviews/{reviewId}")
	public BaseResponse<String> deleteReviewDetail(@PathVariable("userId") int userId,
												   @PathVariable("reviewId") int reviewId
												   ) {
		try {
			PatchReviewReq patchReviewReq = new PatchReviewReq(userId, reviewId);
			reviewService.deleteReviewDetail(patchReviewReq);
			String result = "";
			return new BaseResponse<>(result);
		} catch (BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}


}

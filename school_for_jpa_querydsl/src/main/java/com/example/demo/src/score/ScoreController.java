package com.example.demo.src.score;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.club.model.GetClubRes;
import com.example.demo.src.club.model.PatchClubReq;
import com.example.demo.src.club.model.PostClubReq;
import com.example.demo.src.score.model.GetScoreRes;
import com.example.demo.src.score.model.PatchScoreReq;
import com.example.demo.src.score.model.PostScoreReq;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/score")
public class ScoreController {

	private final ScoreService scoreService;
	private final JwtService jwtService;

	public ScoreController(ScoreService scoreService, JwtService jwtService) {
		this.scoreService = scoreService;
		this.jwtService = jwtService;
	}

	@GetMapping("/{scoreId}")
	public BaseResponse<GetScoreRes> getScore(@PathVariable Long scoreId) {
		try {
			log.info(String.valueOf(scoreId));

			GetScoreRes getScoreRes = scoreService.findScore(scoreId);
			return new BaseResponse<>(getScoreRes);
		} catch(BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}

	@PostMapping
	public BaseResponse<Long> postScore(@RequestBody PostScoreReq postScoreReq) {
		try {
			Long resultId = scoreService.registerScore(postScoreReq);
			return new BaseResponse<>(resultId);
		} catch(BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}

	@PatchMapping("/{scoreId}/edit")
	public BaseResponse<Long> patchScore(@PathVariable Long scoreId,
										@RequestBody PatchScoreReq patchScoreReq) {
		try {
			Long resultId = scoreService.updateScore(scoreId, patchScoreReq);
			return new BaseResponse<>(resultId);
		} catch(BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}

	@PatchMapping("/{scoreId}/status")
	public BaseResponse<Long> deleteScore(@PathVariable Long scoreId) {
		try {

			Long resultId = scoreService.deleteScore(scoreId);
			return new BaseResponse<>(resultId);
		} catch(BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}

}

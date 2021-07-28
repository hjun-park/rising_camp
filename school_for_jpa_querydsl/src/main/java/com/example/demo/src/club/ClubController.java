package com.example.demo.src.club;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.club.model.GetClubRes;
import com.example.demo.src.club.model.PatchClubReq;
import com.example.demo.src.club.model.PostClubReq;
import com.example.demo.src.student.model.GetStudentRes;
import com.example.demo.src.student.model.PatchStudentReq;
import com.example.demo.src.student.model.PostStudentReq;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/club")
public class ClubController {

	private final ClubService clubService;
	private final JwtService jwtService;

	public ClubController(ClubService clubService, JwtService jwtService) {
		this.clubService = clubService;
		this.jwtService = jwtService;
	}

	@GetMapping("/{clubId}")
	public BaseResponse<GetClubRes> getClub(@PathVariable Long clubId) {
		try {
			log.info(String.valueOf(clubId));

			GetClubRes getClubRes = clubService.findClub(clubId);
			return new BaseResponse<>(getClubRes);
		} catch(BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}

	@PostMapping("/join")
	public BaseResponse<Long> postClub(@RequestBody PostClubReq postClubReq) {
		try {

			Long resultId = clubService.joinClub(postClubReq);
			return new BaseResponse<>(resultId);
		} catch(BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}

	@PatchMapping("/{clubId}/edit")
	public BaseResponse<Long> patchClub(@PathVariable Long clubId,
										   @RequestBody PatchClubReq patchClubReq) {
		try {
			Long resultId = clubService.updateClub(clubId, patchClubReq);
			return new BaseResponse<>(resultId);
		} catch(BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}

	@PatchMapping("/{clubId}/status")
	public BaseResponse<Long> deleteClub(@PathVariable Long clubId) {
		try {

			Long resultId = clubService.deleteClub(clubId);
			return new BaseResponse<>(resultId);
		} catch(BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}

}

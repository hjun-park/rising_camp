package com.example.demo.src.location;


import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.location.model.Policy;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
@Slf4j
public class LocationController {

	@Autowired
	private final LocationProvider locationProvider;

	@Autowired
	private final LocationService locationService;

	@Autowired
	private final JwtService jwtService;

	public LocationController(LocationProvider locationProvider, LocationService locationService, JwtService jwtService) {
		this.locationProvider = locationProvider;
		this.locationService = locationService;
		this.jwtService = jwtService;
	}

	//41
	@GetMapping("/{store-id}/policy")
	public BaseResponse<List<Policy>> getPolicy(@PathVariable("store-id") int storeId) {
		try {
			List<Policy> storePolicyList = locationProvider.findPolicy(storeId);
			return new BaseResponse<>(storePolicyList);
		} catch (BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}

	}

	//42
	@PostMapping("/{store-id}/policy")
	public BaseResponse<Integer> postPolicy(@PathVariable("store-id") int storeId,
										  @RequestBody Policy policy) {
		try {
			Integer resultId = locationService.registerPolicy(storeId, policy);
			return new BaseResponse<>(resultId);
		} catch (BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}

	//43. 특정 가게 배달 지역 수수료 수정
	@PatchMapping("/{store-id}/policy/{policy-id}")
	public BaseResponse<Integer> patchPolicy(@PathVariable("store-id") int storeId,
										   @PathVariable("policy-id") int policyId,
										   @RequestBody Policy policy) {
		try {
			Integer result = locationService.modifyPolicy(storeId, policyId, policy);
			return new BaseResponse<>(result);
		} catch (BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}

	//44. 특정 가게 배달 지역 정책 삭제
	@DeleteMapping("/{store-id}/policy/{policy-id}")
	public BaseResponse<Integer> deletePolicy(@PathVariable("store-id") int storeId,
											@PathVariable("policy-id") int policyId) {
		try {
			Integer result = locationService.deletePolicy(storeId, policyId);
			return new BaseResponse<>(result);
		} catch (BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}



}


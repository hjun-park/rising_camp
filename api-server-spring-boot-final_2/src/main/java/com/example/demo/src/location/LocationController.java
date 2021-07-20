package com.example.demo.src.location;


import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.location.model.Policy;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


//	@ResponseBody
//	@GetMapping("/{store-id]}/zones")
//	public BaseResponse<List<StorePolicy>> getZone(@PathVariable("store-id") int storeId) {
//		try {
//			List<StorePolicy> storePolicyList = locationProvider.findZones(storeId);
//			return new BaseResponse<>(storePolicyList);
//		} catch (BaseException exception) {
//			return new BaseResponse<>(exception.getStatus());
//		}
//
//	}

	@ResponseBody
	@PostMapping("/{store-id}/zones")
	public BaseResponse<Integer> postZone(@PathVariable("store-id") int storeId,
										  @RequestBody Policy policy) {
		try {
			Integer resultId = locationService.registerZone(storeId, policy);
			return new BaseResponse<>(resultId);
		} catch (BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}

	}
}


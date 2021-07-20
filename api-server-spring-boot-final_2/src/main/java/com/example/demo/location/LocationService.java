package com.example.demo.location;

import com.example.demo.config.BaseException;
import com.example.demo.location.model.Policy;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Slf4j
@Service
public class LocationService {

	@Autowired
	private final LocationProvider locationProvider;

	@Autowired
	private final LocationDAO locationDAO;

	@Autowired
	private final JwtService jwtService;

	public LocationService(LocationProvider locationProvider, LocationDAO locationDAO, JwtService jwtService) {
		this.locationProvider = locationProvider;
		this.locationDAO = locationDAO;
		this.jwtService = jwtService;
	}

	public Integer registerZone(int storeId, Policy policy) throws BaseException {
		try {
			// 중복된 코드인지 체크

			// 등록
			Integer resultId = locationDAO.insertZone(storeId, policy);
			return resultId;
		} catch (Exception exception) {
			throw new BaseException(DATABASE_ERROR);
		}
	}
}

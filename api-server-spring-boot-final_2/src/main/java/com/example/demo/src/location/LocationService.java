package com.example.demo.src.location;

import com.example.demo.config.BaseException;
import com.example.demo.src.location.model.Policy;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;
import static com.example.demo.config.BaseResponseStatus.NOTHING_TO_DELETE;

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

	public Integer registerPolicy(int storeId, Policy policy) throws BaseException {
		try {
			// 중복된 코드인지 체크

			// 등록
			return locationDAO.insertPolicy(storeId, policy);
		} catch (Exception exception) {
			throw new BaseException(DATABASE_ERROR);
		}
	}

	public Integer modifyPolicy(int storeId, int zoneId, Policy policy) throws BaseException{
		try {
			int tips = policy.getAdditionalTips();
			return locationDAO.modifyTipsById(storeId, zoneId, tips);
		} catch (Exception exception) {
			throw new BaseException(DATABASE_ERROR);
		}

	}

	public Integer deletePolicy(int storeId, int policyId) throws BaseException{
		try {
			int result = locationDAO.deletePolicyById(storeId, policyId);
			if (result == 0) {
				throw new BaseException(NOTHING_TO_DELETE);
			}
			return result;
		} catch (Exception exception) {
			throw new BaseException(DATABASE_ERROR);
		}
	}
}

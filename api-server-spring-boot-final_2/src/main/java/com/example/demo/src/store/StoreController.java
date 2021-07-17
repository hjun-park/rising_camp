package com.example.demo.src.store;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.store.model.GetMenuRes;
import com.example.demo.src.store.model.GetStoreInfoRes;
import com.example.demo.src.store.model.GetStoreRes;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/stores")
public class StoreController {

	@Autowired
	private final StoreProvider storeProvider;
	@Autowired
	private final StoreService storeService;
	@Autowired
	private final JwtService jwtService;

	public StoreController(StoreProvider storeProvider, StoreService storeService, JwtService jwtService) {
		this.storeProvider = storeProvider;
		this.storeService = storeService;
		this.jwtService = jwtService;
	}

	//16
	@ResponseBody
	@GetMapping("/{storeId}")
	public BaseResponse<GetStoreRes> getStore(@PathVariable int storeId) {

		try {
			GetStoreRes getStoreRes = storeProvider.getStore(storeId);
			return new BaseResponse<>(getStoreRes);
		} catch (BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}

	//17
	@ResponseBody
	@GetMapping("/{storeId}/info")
	public BaseResponse<GetStoreInfoRes> getStoreInfo(@PathVariable int storeId) {

		try {
			GetStoreInfoRes getStoreInfoRes = storeProvider.getStoreInfo(storeId);
			return new BaseResponse<>(getStoreInfoRes);
		} catch (BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}

	//18 - JSON 방법 찾기
//	@ResponseBody
//	@GetMapping("/{storeId}/info")
//	public BaseResponse<GetStoreInfoRes> getStoreMenu(@PathVariable int storeId) {
//
//		try {
//			GetStoreInfoRes getStoreInfoRes = storeProvider.getStoreInfo(storeId);
//			return new BaseResponse<>(getStoreInfoRes);
//		} catch (BaseException exception) {
//			return new BaseResponse<>(exception.getStatus());
//		}
//	}

	//19
	@ResponseBody
	@GetMapping("/{storeId}/menus/{menuId}")
	public BaseResponse<GetMenuRes> getMenu(@PathVariable int storeId,
											@PathVariable int menuId) {
		try {
			GetMenuRes getMenuRes = storeProvider.getMenu(storeId, menuId);
			return new BaseResponse<>(getMenuRes);
		} catch (BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}








}

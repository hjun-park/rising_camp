package com.example.demo.src.store;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.store.model.Store;
import com.example.demo.src.store.model.StoreInfo;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stores")
@Slf4j
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

	//27 가게 조회
	@ResponseBody
	@GetMapping("/{store-id}")
	public BaseResponse<Store> getStore(@PathVariable("store-id") int storeId) {
		try {
			Store store = storeProvider.findStore(storeId);
			return new BaseResponse<>(store);
		} catch (BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}

	//28 가게 등록
	@ResponseBody
	@PostMapping("")
	public BaseResponse<Integer> postStore(@RequestBody Store store) {
		try {
			Integer resultId = storeService.registerStore(store);
			return new BaseResponse<>(resultId);
		} catch (BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}

	//28 가게 등록
	@ResponseBody
	@DeleteMapping("/{store-id}")
	public BaseResponse<Integer> deleteStore(@PathVariable("store-id") int storeId) {
		try {

			Integer result = storeService.deleteStore(storeId);
			return new BaseResponse<>(result);
		} catch (BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}

	//34 가게 정보 탭 조회
	@GetMapping("/{store-id}/info")
	public BaseResponse<StoreInfo> getStoreInfo(@PathVariable("store-id") int storeId) {
		try {
			StoreInfo storeInfo = storeProvider.getStoreInfo(storeId);
			return new BaseResponse<>(storeInfo);
		} catch (BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}

	//35 가게
}


package com.example.demo.src.store;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.store.model.StoreDTO;
import com.example.demo.src.store.model.StoreInfoDTO;
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
	public BaseResponse<StoreDTO> getStore(@PathVariable("store-id") int storeId) {
		try {
			StoreDTO storeDTO = storeProvider.findStore(storeId);
			return new BaseResponse<>(storeDTO);
		} catch (BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}

	//28 가게 등록
	@ResponseBody
	@PostMapping("")
	public BaseResponse<Integer> postStore(@RequestBody StoreDTO storeDTO) {
		try {
			Integer resultId = storeService.registerStore(storeDTO);
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
	public BaseResponse<StoreInfoDTO> getStoreInfo(@PathVariable("store-id") int storeId) {
		try {
			StoreInfoDTO storeInfoDTO = storeProvider.getStoreInfo(storeId);
			return new BaseResponse<>(storeInfoDTO);
		} catch (BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}

	//35 가게
}


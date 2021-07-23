package com.example.demo.src.category;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.category.model.Category;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private final CategoryProvider categoryProvider;
	@Autowired
	private final CategoryService categoryService;
	@Autowired
	private final JwtService jwtService;

	public CategoryController(CategoryProvider categoryProvider, CategoryService categoryService, JwtService jwtService) {
		this.categoryProvider = categoryProvider;
		this.categoryService = categoryService;
		this.jwtService = jwtService;
	}

	//25 - 카테고리 리스트 조회
	@ResponseBody
	@GetMapping("")
	public BaseResponse<List<Category>> getCategory() {
		try {
			List<Category> getCategoryRes = categoryProvider.getCategory();
			return new BaseResponse<>(getCategoryRes);
		} catch(BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}

	//26 - 카테고리 생성
	@PostMapping("")
	public BaseResponse<Integer> postCategory(@RequestBody Category category) {
		try {
			Integer resultId = categoryService.registerCategory(category);
			return new BaseResponse<>(resultId);
		} catch (BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}

	//27 - 특정 카테고리 가게 조회
//	@ResponseBody
//	@GetMapping("/{name}")
//	public BaseResponse<List<CategoryStoreDTO>> getCategoryStores(@PathVariable("name") String categoryName) {
//		try {
//			List<CategoryStoreDTO> categoryStoreDTOList = categoryProvider.getCategoryStores(categoryName);
//			return new BaseResponse<>(categoryStoreDTOList);
//		} catch(BaseException exception) {
//			return new BaseResponse<>(exception.getStatus());
//		}
//	}

	//28 - 특정 카테고리 삭제
	@DeleteMapping("")
	public BaseResponse<Integer> deleteAllCarts(@RequestParam("name") String param) {

		log.info("########## -> {}", param);
		String categoryName = null;
		try {
			categoryName = URLDecoder.decode(param, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		try {
			log.info("########## -> {}", categoryName);
			Integer result = categoryService.deleteCategory(categoryName);
			return new BaseResponse<>(result);
		} catch (BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}


	}




}

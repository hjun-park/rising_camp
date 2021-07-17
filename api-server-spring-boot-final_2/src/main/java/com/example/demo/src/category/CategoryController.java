package com.example.demo.src.category;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.src.category.model.GetCategoryRes;
import com.example.demo.utils.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

	//14 - Query Parameter 이용하는 방식 ?
	@ResponseBody
	@GetMapping("")
	public BaseResponse<List<GetCategoryRes>> getCategory() {
		try {
			List<GetCategoryRes> getCategoryRes = categoryProvider.getCategory();
			return new BaseResponse<>(getCategoryRes);
		} catch(BaseException exception) {
			return new BaseResponse<>(exception.getStatus());
		}
	}

	//15 - 필요 X

}

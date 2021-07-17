package com.example.demo.src.category;

import com.example.demo.config.BaseException;
import com.example.demo.src.category.model.GetCategoryRes;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
@Slf4j
public class CategoryProvider {

	private final CategoryDao categoryDao;
	private final JwtService jwtService;

	public CategoryProvider(CategoryDao categoryDao, JwtService jwtService) {
		this.categoryDao = categoryDao;
		this.jwtService = jwtService;
	}

	public List<GetCategoryRes> getCategory() throws BaseException {
		try{
			List<GetCategoryRes> getCategoryRes = categoryDao.getCategory();
			return getCategoryRes;
		}
		catch (Exception exception) {
			throw new BaseException(DATABASE_ERROR);
		}
	}
}

package com.example.demo.src.category;

import com.example.demo.config.BaseException;
import com.example.demo.src.category.model.CategoryDTO;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;
import static com.example.demo.config.BaseResponseStatus.NOTHING_TO_DELETE;

@Service
@Slf4j
public class CategoryService {

	private final CategoryDAO categoryDAO;
	private final CategoryProvider categoryProvider;
	private final JwtService jwtService;

	public CategoryService(CategoryDAO categoryDAO, CategoryProvider categoryProvider, JwtService jwtService) {
		this.categoryDAO = categoryDAO;
		this.categoryProvider = categoryProvider;
		this.jwtService = jwtService;
	}

	public Integer registerCategory(CategoryDTO categoryDTO) throws BaseException {
		try {
			// 중복된 이름 검증

			// 등록
			return categoryDAO.insertCategory(categoryDTO);
		} catch(Exception exception) {
			throw new BaseException(DATABASE_ERROR);
		}

	}

	public Integer deleteCategory(String categoryName) throws BaseException{
		try {
			// TODO : 삭제 전 카테고리 안에 store가 있는지 체크해야 함
			int result = categoryDAO.deleteCategoryByName(categoryName);
			if (result == 0) {
				throw new BaseException(NOTHING_TO_DELETE);
			}
			return result;
		} catch (Exception exception) {
			throw new BaseException(DATABASE_ERROR);
		}
	}
}

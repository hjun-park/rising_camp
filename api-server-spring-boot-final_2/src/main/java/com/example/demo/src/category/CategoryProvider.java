package com.example.demo.src.category;

import com.example.demo.config.BaseException;
import com.example.demo.src.category.model.CategoryDTO;
import com.example.demo.src.review.ReviewDAO;
import com.example.demo.src.store.StoreDAO;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

@Service
@Slf4j
public class CategoryProvider {

	private final CategoryDAO categoryDao;
	private final ReviewDAO reviewDAO;
	private final StoreDAO storeDAO;
	private final JwtService jwtService;

	public CategoryProvider(CategoryDAO categoryDao, ReviewDAO reviewDAO, StoreDAO storeDAO, JwtService jwtService) {
		this.categoryDao = categoryDao;
		this.reviewDAO = reviewDAO;
		this.storeDAO = storeDAO;
		this.jwtService = jwtService;
	}

	public List<CategoryDTO> getCategory() throws BaseException {
		try{
			List<CategoryDTO> getCategoryRes = categoryDao.getCategory();
			return getCategoryRes;
		}
		catch (Exception exception) {
			throw new BaseException(DATABASE_ERROR);
		}
	}

//	public List<CategoryStoreDTO> getCategoryStores(String categoryName) throws BaseException{
//		try {
//			// 1) 카테고리 이름과 일치하는게 있는지
////			List<CategoryDTO> categoryDTOList = CategoryDAO.findStoresByName(categoryName);
//
//			// 2) (추후에 유저구역도 구분짓고 넣어야함) 카테고리 이름에 해당하는 스토어
//			List<StoreDTO> getStores = storeDAO.findStoreByCategoryName(categoryName);
//
//			// 카테고리스토어 DTO와 매치시키는 과정
//			return getStores.stream()
//				.map((store) -> {
//					// 하나의 가게에서 모든 리뷰를 가져옴
//					List<ReviewDTO> reviews = reviewDAO.findReviewsByStoreId(store.getId());
//
//					// 하나의 리뷰를 순회하면서
//					return reviews.stream()
//						.map((review) -> {
//								review.getRating();
//							}
//						)
//
////					Double rating = reviews.stream()
////						.filter()
////						.mapToDouble()
////						.average();
//
//					return new CategoryStoreDTO(find)
//				})
//				.collect(Collectors.toList());

//
//		} catch(Exception exception) {
//			throw new BaseException(DATABASE_ERROR);
//		}
//
//	}
}

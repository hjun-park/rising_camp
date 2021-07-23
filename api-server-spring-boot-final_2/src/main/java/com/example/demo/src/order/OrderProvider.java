package com.example.demo.src.order;

import com.example.demo.config.BaseException;
import com.example.demo.src.menu.MenuDAO;
import com.example.demo.src.menu.model.Menu;
import com.example.demo.src.order.model.Order;
import com.example.demo.src.order.model.OrderItem;
import com.example.demo.src.order.model.OrderRes;
import com.example.demo.src.store.StoreDAO;
import com.example.demo.src.store.model.Store;
import com.example.demo.utils.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.demo.config.BaseResponseStatus.*;

@Service
@Slf4j
public class OrderProvider {

	private final OrderDAO orderDAO;

	private final StoreDAO storeDAO;

	private final MenuDAO menuDAO;

	private final JwtService jwtService;

	public OrderProvider(OrderDAO orderDAO, StoreDAO storeDAO, MenuDAO menuDAO, JwtService jwtService) {
		this.orderDAO = orderDAO;
		this.storeDAO = storeDAO;
		this.menuDAO = menuDAO;
		this.jwtService = jwtService;
	}

	public List<OrderRes> getOrderHistory(int memberId) throws BaseException {
		try {
			List<Order> orders = orderDAO.findOrdersById(memberId);

			// 주문 내역들 중 orderId를 뽑아서 메뉴 리스트 생성
			return orders.stream().map((order) -> {
				// 상점 ID -> 주문내역 테이블
				Store store = null;
				try {
					store = storeDAO.findStoreById(order.getStoreId());
				} catch (Exception exception) {
					exception.printStackTrace();
				}
				// 메뉴 ID
				List<OrderItem> orderItems = orderDAO.findItemsByOrderId(order.getId());

				int menuId = orderItems.get(0).getMenuId();
				int menuCount = orderItems.size();

				// 메뉴 이름 가져옴
				Menu menu = menuDAO.findMenuById(menuId);
				String menuName = menu.getName();

				return OrderRes.builder()
					.date(order.getOrderTime())
					.orderStatus(order.getStatus().name())
					.storeName(store.getName())
					.menuName(menuName)
					.menuCount(menuCount)
					.build();
				})
				.collect(Collectors.toList());
		} catch (Exception exception) {
			exception.printStackTrace();
			throw new BaseException(DATABASE_ERROR);
		}
    }


}



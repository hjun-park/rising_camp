package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class PostUserBasketReq {
	private int storeId;
	private int menuId;
	private String storeName;
	private String menuName;
	private int menuPrice;
	private int amount;

	public PostUserBasketReq() {

	}

}

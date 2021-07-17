package com.example.demo.src.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetUserBasketRes {
	private String storeName;
//	private int total;
	private String menuName;
	private int amount;
	private int menuPrice;

//	@JsonProperty("menus")
//	private Menus menus;

//	@Getter
//	@Setter
//	@AllArgsConstructor
//	public static class Menus {
//		private String menuName;
//		private int amount;
//		private int menuPrice;
//	}
}

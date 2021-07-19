package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Menus {
	private String menuName;
	private int amount;
	private int menuPrice;

	public Menus() {
	}

}

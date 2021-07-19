package com.example.demo.src.user.model;

import com.example.demo.src.store.model.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class BasketResult {
	private final Store store;
	private final Menus menus;

}

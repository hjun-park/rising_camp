package com.example.demo.src.user.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Basket {

	private int id;
	private int userId;
	private int storeId;
	private int menuId;
	private int amount;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	private String status;


}

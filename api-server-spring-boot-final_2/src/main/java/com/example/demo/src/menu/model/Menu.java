package com.example.demo.src.menu.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Menu {
	private enum Status {
		Full, Empty;
	}

	private int id;
	private String name;
	private int price;
	private String picture;
	private Status status;

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	private String content;
	private int menuGroupId;
	private int isSignature;

	public Menu() {
	}

	public Menu(int id, String name, int price, String picture, String content, int menuGroupId, int isSignature) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.picture = picture;
		this.content = content;
		this.menuGroupId = menuGroupId;
		this.isSignature = isSignature;
	}
}

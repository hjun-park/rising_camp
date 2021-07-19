package com.example.demo.menu.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@ToString @Builder
public class MenuDTO {
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

	public MenuDTO() {
	}
}

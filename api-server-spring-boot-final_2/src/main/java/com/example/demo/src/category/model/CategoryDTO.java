package com.example.demo.src.category.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class CategoryDTO {
	public enum Status {
		Used, Deleted;
	}

	private String name;
	private String imageUrl;

	private Status status;
}

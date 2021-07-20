package com.example.demo.src.member.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class MemberCartDTO {

	private String name;
	private int price;
	private int amount;

	public MemberCartDTO() {
	}
}

package com.example.demo.src.member.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static lombok.Lombok.checkNotNull;

@Getter
@ToString
public class MemberCart {

	private String name;
	private int price;
	private int amount;

	public MemberCart(String name, int price, int amount) {
		this.name = checkNotNull(name, "name");
		this.price = price;
		this.amount = amount;
	}
}

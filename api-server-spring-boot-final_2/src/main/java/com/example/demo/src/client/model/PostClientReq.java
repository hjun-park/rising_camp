package com.example.demo.src.client.model;

import lombok.Data;

@Data
public class PostClientReq {
	private String email;
	private String password;
	private String name;
	private String phoneNumber;
	private String profileImageUrl;
	private String birthDate;
}

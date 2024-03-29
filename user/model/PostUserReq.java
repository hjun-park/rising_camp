package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostUserReq {
	private String phoneNumber;
	private String email;
	private String password;
	private String birthDate;
	private String nickname;
}

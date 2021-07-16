package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.lang.Nullable;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class User {

	private int id;
	private String email;
	private String password;
	private String name;
	private String phoneNumber;
	private String profileImageUrl;
	private int mailAccept;
	private int smsAccept;
	private Date birthDate;

	private String addressBuildingNum;
	private String districtCode;
	private String addressDetail;
	private double latitude;
	private double longitude;

	private int grade;
	private String status;
	private Date createdAt;
	private Date updatedAt;

	public User(int id, String email, String password) {
		this.id = id;
		this.email = email;
		this.password = password;
	}
}


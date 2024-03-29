package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;

@Getter @Setter
@AllArgsConstructor
@ToString
public class User {
	public enum Status {
		Used, Deleted;
	}

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
	private Status status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public User(int id, String email, String password) {
		this.id = id;
		this.email = email;
		this.password = password;
	}
}


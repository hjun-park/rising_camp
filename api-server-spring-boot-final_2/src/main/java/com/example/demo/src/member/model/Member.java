package com.example.demo.src.member.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor
@ToString
public class Member {
	public enum Status {
		Joined, Deleted, Suspend;
	}


	private int id;

	private String email;
	private String password;
	private String name;
	private String phoneNumber;
	private String profileImageUrl;
	private String mailAccept;
	private String smsAccept;
	private String birthDate;

	private String addressBuildingNum;
	private String districtCode;
	private String addressDetail;
	private double latitude;
	private double longitude;

	private int grade;
	private Status status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public Member(int id, String email, String password) {
		this.id = id;
		this.email = email;
		this.password = password;
	}

	public Member(int id, String email, String password, String name, String phoneNumber, String profileImageUrl, String mailAccept, String smsAccept, String birthDate, String addressBuildingNum, String districtCode, String addressDetail, double latitude, double longitude, int grade) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.profileImageUrl = profileImageUrl;
		this.mailAccept = mailAccept;
		this.smsAccept = smsAccept;
		this.birthDate = birthDate;
		this.addressBuildingNum = addressBuildingNum;
		this.districtCode = districtCode;
		this.addressDetail = addressDetail;
		this.latitude = latitude;
		this.longitude = longitude;
		this.grade = grade;
	}

	public Member() {
	}

	public static boolean hasNullDataWhenJoin(Member m) {
		return m.getEmail() == null || m.getPassword() == null ||
			m.getName() == null || m.getPhoneNumber() == null;
	}
}

